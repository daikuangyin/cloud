//package com.sun.cloud.framework.handler;
//
//import com.ym.bank.common.utils.APIResponseBuilder;
//import com.ym.bank.framework.condition.page.PageDomain;
//import com.ym.bank.framework.enums.ResultCode;
//import com.ym.bank.framework.exception.AuthException;
//import com.ym.bank.framework.exception.BusinessException;
//import com.ym.bank.framework.exception.CodeErrorException;
//import com.ym.bank.framework.model.vo.APIResponse;
//import org.apache.shiro.authc.*;
//import org.apache.shiro.authc.pam.UnsupportedTokenException;
//import org.apache.shiro.authz.UnauthenticatedException;
//import org.apache.shiro.authz.UnauthorizedException;
//import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.validation.BindException;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.FieldError;
//import org.springframework.web.HttpMediaTypeNotSupportedException;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.MissingServletRequestParameterException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.ResponseStatus;
//
//import javax.validation.ConstraintViolation;
//import javax.validation.ConstraintViolationException;
//import javax.validation.UnexpectedTypeException;
//import java.util.List;
//import java.util.Set;
//
///**
// * 统一异常处理
// */
//@ControllerAdvice
//public class
//GlobalExceptionHandler {
//	public static final String SYS_UNKNOW_ERROR = "系统未知异常！";
//	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
//
//	@ResponseBody
//	@ResponseStatus(value = HttpStatus.FORBIDDEN)
//	@ExceptionHandler(value = UnauthenticatedException.class)
//	public APIResponse handleException(UnauthenticatedException e) {
//		logger.error("find exception:e={}", e);
//		PageDomain.clearPage();
//		return APIResponseBuilder.fail(ResultCode.TOKEN_ERROR);
//	}
//
//	@ResponseBody
//	@ResponseStatus(value = HttpStatus.FORBIDDEN)
//	@ExceptionHandler(value = UnauthorizedException.class)
//	public APIResponse handleException(UnauthorizedException e) {
//		logger.error("find exception:e={}", e);
//		PageDomain.clearPage();
//		return APIResponseBuilder.fail(ResultCode.NO_PERMISSION);
//	}
//
//	@ResponseBody
//	@ExceptionHandler(value = AuthenticationException.class)
//	public APIResponse handleException(AuthenticationException e) {
//		logger.error("find exception:e={}", e);
//		PageDomain.clearPage();
//		ResultCode resultCode = ResultCode.FAIL;
//		if (e instanceof UnknownAccountException) {  // 未知账号
//			resultCode = ResultCode.ACCOUNT_DOES_NOT_EXIST;
//		} else if (e instanceof IncorrectCredentialsException) {// 未知凭证
//			resultCode = ResultCode.ACCOUNT_PASSWORD_IS_WRONG;
//		} else if (e instanceof ExpiredCredentialsException) {  // 凭证过期
//			resultCode = ResultCode.NO_LOGIN;
//		} else if (e instanceof ConcurrentAccessException) {  // 并发访问异常
//			resultCode = ResultCode.USER_LOGGING_IN_MULTIPLE_PLACES;
//		} else if (e instanceof ExcessiveAttemptsException) {  // 并发访问异常
//			resultCode = ResultCode.THE_NUMBER_OF_AUTHENTICATION_EXCEEDS_THE_LIMIT;
//		} else if (e instanceof LockedAccountException) {  // 账户被锁定
//			resultCode = ResultCode.ACCOUNT_IS_LOCKED;
//		} else if (e instanceof DisabledAccountException) {  // 禁用账号
//			resultCode = ResultCode.ACCOUNT_HAS_BEEN_DISABLED;
//		} else if (e instanceof UnsupportedTokenException) {//使用了不支持的Token
//			resultCode = ResultCode.TOKEN_ERROR;
//		} else if (e instanceof CodeErrorException) {//验证码错误
//			resultCode = ResultCode.VERIFICATION_CODE_ERROR;
//		}else if (e instanceof AuthException){
//			resultCode = ResultCode.LOGIN_WITHOUT_PERMISSION;
//		}
//		return APIResponseBuilder.fail(resultCode);
//	}
//
//	@ExceptionHandler(value = BusinessException.class)
//	@ResponseBody
//	public APIResponse handleException(BusinessException e) {
//		logger.error("find exception:e={}", e);
//		PageDomain.clearPage();
//		return APIResponseBuilder.failWithMsg(e.getMessage());
//	}
//
//	@ExceptionHandler(value = MethodArgumentNotValidException.class)
//	@ResponseBody
//	public APIResponse handleException(MethodArgumentNotValidException e) {
//		PageDomain.clearPage();
//		BindingResult bindingResult = e.getBindingResult();
//		String errorMessage = "";
//		List<FieldError> fieldErrors = bindingResult.getFieldErrors();
//		for (FieldError fieldError : fieldErrors) {
//			errorMessage += fieldError.getField() + fieldError.getDefaultMessage() + ",";
//		}
//		String finalErrorMessage = errorMessage.substring(0, errorMessage.length() - 1);
//		logger.error("find exception:e={}", finalErrorMessage);
//		return APIResponseBuilder.failWithMsg(finalErrorMessage);
//	}
//
//	@ExceptionHandler(value = BindException.class)
//	@ResponseBody
//	public APIResponse handleException(BindException e) {
//		PageDomain.clearPage();
//		BindingResult bindingResult = e.getBindingResult();
//		String errorMessage = "";
//		List<FieldError> fieldErrors = bindingResult.getFieldErrors();
//		for (FieldError fieldError : fieldErrors) {
//			errorMessage += fieldError.getField() + fieldError.getDefaultMessage() + ",";
//		}
//		String finalErrorMessage = errorMessage.substring(0, errorMessage.length() - 1);
//		logger.error("find exception:e={}", finalErrorMessage);
//		return APIResponseBuilder.failWithMsg(finalErrorMessage);
//	}
//
//	@ExceptionHandler(value = ConstraintViolationException.class)
//	@ResponseBody
//	public APIResponse handleException(ConstraintViolationException e) {
//		PageDomain.clearPage();
//		Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
//		StringBuilder builder = new StringBuilder();
//		constraintViolations.forEach(n -> {
//			ConstraintViolationImpl info = (ConstraintViolationImpl) n;
//			String propertyPath = info.getPropertyPath().toString();
//			builder.append(propertyPath.substring(propertyPath.lastIndexOf(".") + 1, propertyPath.length()) + info.getMessage() + ",");
//		});
//		String msg = builder.toString().substring(0, builder.length() - 1);
//		logger.error("find exception:e={}", msg);
//		return APIResponseBuilder.failWithMsg(msg);
//	}
//
//	@ExceptionHandler(value = UnexpectedTypeException.class)
//	@ResponseBody
//	public APIResponse handleException(UnexpectedTypeException e) {
//		logger.error("find exception:e={}", e);
//		PageDomain.clearPage();
//		return APIResponseBuilder.failWithMsg(e.getMessage());
//	}
//
//
//	@ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
//	@ResponseBody
//	public APIResponse handleException(HttpMediaTypeNotSupportedException e) {
//		logger.error("MediaType Error:e={}", e);
//		PageDomain.clearPage();
//		return APIResponseBuilder.fail(ResultCode.UNSUPPORTED_TYPE);
//	}
//
//	@ExceptionHandler(value = MissingServletRequestParameterException.class)
//	@ResponseBody
//	public APIResponse handleException(MissingServletRequestParameterException e) {
//		logger.error("请求参数不完整 Error:e={}", e);
//		PageDomain.clearPage();
//		return APIResponseBuilder.fail(ResultCode.BAD_REQUEST);
//	}
//
//	@ExceptionHandler(value = Exception.class)
//	@ResponseBody
//	public APIResponse handleException(Exception e) {
//		logger.error("find exception:e={}", e);
//		PageDomain.clearPage();
//		return APIResponseBuilder.failWithMsg(SYS_UNKNOW_ERROR);
//	}
//
//}
