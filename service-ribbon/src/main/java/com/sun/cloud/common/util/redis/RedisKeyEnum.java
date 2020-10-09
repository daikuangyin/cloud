package com.sun.cloud.common.util.redis;

/**
 * Redis的Key
 *
 * @author: 李涛
 * @version: 2019年05月07日 15:21
 */
public enum RedisKeyEnum {

	//------------------------------锁开始----------------------------------

	/**
	 * 全局唯一锁KEY
	 */
	COMMON_LOCK(RedisKeyUtil.KEY_PREFIX, "Global", "Global", "全局的一个锁"),

	/**
	 * 频繁操作Key
	 */
	FREQUENT_OPERATION_KEY(RedisKeyUtil.KEY_PREFIX, "FREQUENT_OPERATION_KEY", "FREQUENT_OPERATION_KEY", "操作太频繁"),

	/**
	 * 生成编号的KEY
	 */
	ORDER_CODE_LOCK(RedisKeyUtil.KEY_PREFIX, "ORDER_CODE_LOCK", "ORDER_CODE_LOCK", "生成编号使用"),

	/**
	 * 项目处理捐赠情况的KEY
	 */
	PROJECT_HANDLE(RedisKeyUtil.KEY_PREFIX, "PROJECT_HANDLE", "PROJECT_HANDLE", "项目处理捐赠情况的KEY"),

	/**
	 * 支付回调订单锁定
	 */
	PAY_CALL_BACK(RedisKeyUtil.KEY_PREFIX, "PAY", "CALL_BACK", "支付回调订单锁定"),

	/**
	 * 发起支付KEY
	 */
	APPLY_PAY_KEY(RedisKeyUtil.KEY_PREFIX, "APPLY", "PAY", "发起支付KEY"),

	/**
	 * 发起web支付KEY
	 */
	APPLY_WEB_PAY_KEY(RedisKeyUtil.KEY_PREFIX, "APPLY", "WEB_PAY", "发起web支付KEY"),


	/**
	 * 座位信息  key
	 */
	ORDER_SEAT_SELECTION(RedisKeyUtil.KEY_PREFIX, "ORDER_SEAT_SELECTION", "WXAPPLYPAYSIGN_SIGNDATA", "座位被选中"),

	//------------------------------锁结束----------------------------------

	/**
	 * 表达重复提交Key
	 */
	REPEAT_KEY(RedisKeyUtil.KEY_PREFIX, "FREQUENT", "REQ_KEY", "表单重复提交Key"),

	/**
	 * 发送手机号验证码
	 */
	SEND_PHONE_CODE(RedisKeyUtil.KEY_PREFIX, "LOGIN", "sendCode", "发送手机号验证码"),

	/**
	 * 发送手机号验证码次数
	 */
	SEND_PHONE_CODE_TIMES(RedisKeyUtil.KEY_PREFIX, "LOGIN", "sendCode:Times", "发送手机号验证码"),

	/**
	 * 图片验证码
	 */
	VERIFICATION_CODE(RedisKeyUtil.KEY_PREFIX, "LOGIN", "VERIFICATION", "图片验证码"),

	/**
	 * 用户-token关系key
	 */
	SHIRO_TOKEN_USER(RedisKeyUtil.KEY_PREFIX, "shiro", "user", "shiro用户和token关系"),
	/**
	 * 用户token-appKey关系key
	 */
	SHIRO_TOKEN_APPKEY(RedisKeyUtil.KEY_PREFIX, "shiro", "appKey", "shiro用户token和appKey关系"),
	/**
	 * 用户-token关系key
	 */
	SHIRO_TOKEN_MEMBER(RedisKeyUtil.KEY_PREFIX, "shiro", "member", "shiro用户和token关系"),

	/**
	 * 捐赠记录处理集合
	 */
	HANDLE_RECORD_LIST(RedisKeyUtil.KEY_PREFIX, "HANDLE_RECORD_LIST", "HANDLE_RECORD_LIST", "捐赠记录处理集合"),

	/**
	 * 前一天捐赠前100
	 */
	DAY_BEFORE_DOANTE_RANK(RedisKeyUtil.KEY_PREFIX, "DOANTE_RANK", "DAY_BEFORE_DOANTE_RANK", "前一天捐赠前100"),

	/**
	 * 生效记录
	 * redis key 生效时的临时key(EFFECTIVE_VALUE:key:score/expirate:value)
	 */
	EFFECTIVE_VALUE(RedisKeyUtil.KEY_PREFIX, "EFFECTIVE_VALUE", "", "生效记录"),

	/**
	 * 置顶
	 */
	SET_TOP_TABLE(RedisKeyUtil.KEY_PREFIX, "SET_TOP_TABLE", "TABLE", "置顶"),

	/**
	 * 用户转换  key
	 */
	SYSTEM_USER_COVER(RedisKeyUtil.KEY_PREFIX, "SYSTEM_USER", "SYSTEM_USER_COVER", "用户转换器"),

	;

	/**
	 * 系统标识
	 */
	private String keyPrefix;
	/**
	 * 模块名称
	 */
	private String module;
	/**
	 * 方法名称
	 */
	private String func;

	/**
	 * 描述
	 */
	private String remark;

	RedisKeyEnum(String keyPrefix, String module, String func, String remark) {
		this.keyPrefix = keyPrefix;
		this.module = module;
		this.func = func;
		this.remark = remark;
	}

	public String getKeyPrefix() {
		return keyPrefix;
	}

	public String getModule() {
		return module;
	}

	public String getFunc() {
		return func;
	}

	public String getRemark() {
		return remark;
	}

	/**
	 * 构建key
	 *
	 * @param objStr
	 * @return
	 */
	public String build(String... objStr) {
		return RedisKeyUtil.keyBuilder(this, objStr);
	}
}
