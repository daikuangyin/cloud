package com.sun.cloud.framework.aspect;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.thread.GlobalThreadPool;
import com.alibaba.fastjson.JSONObject;
import com.sun.cloud.common.util.IpUtil;
import com.sun.cloud.common.util.ServletUtil;
import com.sun.cloud.entity.SysOperationLog;
import com.sun.cloud.framework.annotation.Log;
import com.sun.cloud.service.ISysOperationLogService;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

/**
 * 请求的日志处理
 * 目前仅仅前台打印，后期可保存入库
 */
@Aspect
@Order(1)
@Component
public class WebLogAspect {

	@Autowired
	private ISysOperationLogService sysOperationLogSV;

	private static Logger LOGGER = LoggerFactory.getLogger(WebLogAspect.class);

	@Pointcut("@annotation(com.sun.cloud.framework.annotation.Log)")
	public void webLog() {
	}

	@Around("webLog()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		SysOperationLog sysLog = new SysOperationLog();
		long startTime = System.currentTimeMillis();
		// 执行业务代码
		Object result = null;
		try {
			result = joinPoint.proceed();
			sysLog.setSuccess("success");
		} catch (Throwable e) {
			sysLog.setSuccess(ExceptionUtil.getRootCauseMessage(e));
			throw e;
		} finally {
			try {
				// 处理swagger注解上的参数
				ApiOperation apiOperationAnnotation = getAnnotation(joinPoint, ApiOperation.class);
				if (apiOperationAnnotation != null) {
					sysLog.setOperationType(apiOperationAnnotation.value());
				}
				//接收到请求，记录请求内容
				HttpServletRequest request = ServletUtil.getRequest();
				sysLog.setIp(IpUtil.getIpAddr(request));
				sysLog.setMethod(request.getMethod());
				sysLog.setUri(request.getRequestURI());
				// 设置方法名称
				String className = joinPoint.getTarget().getClass().getName();
				String methodName = joinPoint.getSignature().getName();
				sysLog.setController(className + "." + methodName);
				Object loginTypeStr = null; //ShiroUtil.getSession().getAttribute(String.valueOf(Global.LOGIN_TYPE));
				String terminal = "未知";
				String account = "未知";
				Long loginId = null;
				if (loginTypeStr != null) {
					// 表示已经登录
//					LoginTypeEnum loginTypeEnum = DictEnum.valueOfEnum(LoginTypeEnum.class, String.valueOf(loginTypeStr));
//					terminal = loginTypeEnum.getValue();
//					String loginPhone = ShiroUtil.getLoginPhone();
//					account = loginPhone == null ? "未知" : loginPhone;
//					loginId = ShiroUtil.getLoginId();
				}
				sysLog.setTerminal(terminal);
				sysLog.setAccount(account);
				sysLog.setCreator(loginId);
				Log logAnnotation = getAnnotation(joinPoint, Log.class);
				// 输出body内容
				if (logAnnotation.printBody()) {
					byte[] requestBody = ServletUtil.getBodyBytes(request);
					String bodyStr = new String(requestBody);
					sysLog.setBodyParams(bodyStr);
				}
				// 输出query内容
				if (logAnnotation.printQuery()) {
					Map<String, String[]> map = request.getParameterMap();
					String queryStr = JSONObject.toJSONString(map);
					sysLog.setQueryParams(queryStr);
				}
				// 耗时
				long endTime = System.currentTimeMillis() - startTime;
				sysLog.setTime(String.valueOf(endTime));
				printLog(logAnnotation, sysLog);
			} catch (Exception e) {
				LOGGER.error("日志打印异常:{}", ExceptionUtil.stacktraceToString(e));
			}
		}
		return result;
	}

	/**
	 * 打印日志
	 *
	 * @param logAnnotation
	 * @param sysLog
	 */
	private void printLog(Log logAnnotation, SysOperationLog sysLog) {
		// 异步操作打印
		GlobalThreadPool.execute(() -> {
			LOGGER.info("操作类型:{}", sysLog.getOperationType());
			LOGGER.info("{}请求:{} ", sysLog.getMethod(), sysLog.getUri());
			LOGGER.info("IP : {}", sysLog.getIp());
			LOGGER.info("调用方法:{}{}", sysLog.getController(), "()");
			LOGGER.info("账号:{}", sysLog.getAccount());
			LOGGER.info("终端:{}", sysLog.getTerminal());
			if (logAnnotation.printQuery()) {
				LOGGER.info("Query参数:{}", sysLog.getQueryParams());
			}
			if (logAnnotation.printBody()) {
				LOGGER.info("Body 参数:{}", sysLog.getBodyParams());
			}
			LOGGER.info("结果:{}", sysLog.getSuccess());
			LOGGER.info("耗时:{} ", sysLog.getTime());
			if (logAnnotation.dataBase()) {
				sysLog.setCreateTime(new Date());
				sysOperationLogSV.insert(sysLog);
			}
		});
	}

	/**
	 * 是否存在注解，如果存在就获取
	 */
	private <T> T getAnnotation(JoinPoint joinPoint, Class<? extends Annotation> t) {
		Signature signature = joinPoint.getSignature();
		MethodSignature methodSignature = (MethodSignature) signature;
		Method method = methodSignature.getMethod();
		if (method != null) {
			return (T) method.getAnnotation(t);
		}
		return null;
	}

}
