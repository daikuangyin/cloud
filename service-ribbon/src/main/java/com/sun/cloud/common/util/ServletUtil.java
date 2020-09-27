package com.sun.cloud.common.util;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 客户端工具类
 *
 * @author ym
 */
public class ServletUtil extends cn.hutool.extra.servlet.ServletUtil {

	/**
	 * 获取request
	 */
	public static HttpServletRequest getRequest() {
		return getRequestAttributes().getRequest();
	}

	/**
	 * 获取response
	 */
	public static HttpServletResponse getResponse() {
		return getRequestAttributes().getResponse();
	}

	/**
	 * 获取session
	 */
	public static HttpSession getSession() {
		return getRequest().getSession();
	}

	public static ServletRequestAttributes getRequestAttributes() {
		RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
		return (ServletRequestAttributes) attributes;
	}

	/**
	 * 获取String参数
	 */
	public static String getParameter(String name) {
		return getRequest().getParameter(name);
	}

	/**
	 * 获取Integer参数
	 */
	public static Integer getParameterToInt(String name) {
		String parameter = getRequest().getParameter(name);
		if (parameter == null) {
			return null;
		}
		return Integer.valueOf(parameter);
	}

	/**
	 * 获取Integer参数
	 */
	public static Integer getParameterToInt(String name, Integer defaultValue) {
		return Integer.valueOf(getRequest().getParameter(name), defaultValue);
	}

	/**
	 * 获取String参数
	 */
	public static String getHeader(String name) {
		return getRequest().getHeader(name);
	}

	/**
	 * 向web输出内容
	 *
	 * @param response
	 * @param obj
	 * @param contentType
	 * @throws Exception
	 */
	public static void write(HttpServletResponse response, Object obj, String contentType) throws Exception {
		response.setContentType(contentType);
		PrintWriter writer = response.getWriter();
		writer.print(JSONObject.toJSONString(obj, SerializerFeature.WriteMapNullValue,
				SerializerFeature.WriteDateUseDateFormat));
		writer.close();
		response.flushBuffer();
	}

	/**
	 * 获取get参数，字符串
	 *
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static String getParamString(HttpServletRequest request) {
		Map<String, String[]> parameterMap = request.getParameterMap();
		Map<String, String[]> sortedMap = parameterMap.entrySet().stream()
				.sorted(Map.Entry.comparingByKey())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
						(oldValue, newValue) -> oldValue, LinkedHashMap::new));
		StringBuilder param = new StringBuilder();
		for (Map.Entry<String, String[]> entry : sortedMap.entrySet()) {
			String key = entry.getKey();
			String[] value = entry.getValue();
			param.append(key + "=");
			if (!ArrayUtils.isEmpty(value) && StringUtils.isNotBlank(value[0])) {
				param.append(value[0]);
			}
			param.append("&");
		}
		if (param.length() > 0) {
			param.deleteCharAt(param.lastIndexOf("&"));
		}
		return param.toString();
	}
}
