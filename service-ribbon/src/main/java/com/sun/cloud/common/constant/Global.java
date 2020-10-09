/**
 * Copyright &copy; 2012-2016 #copyright# All rights reserved.
 */
package com.sun.cloud.common.constant;

/**
 * 全局配置类
 *
 * @author Durian
 * @version 2017-06-25
 */
public abstract class Global {


	//---------------------登陆常量----------------------

	/**
	 * 登录类型
	 */
	public static final String LOGIN_TYPE = "loginType";

	/**
	 * 微信ID
	 */
	public static final String OPEN_ID = "openId";
	/**
	 * 系统密匙KEY
	 */
	public static final String APP_KEY = "app_key";
	/**
	 * 系统密匙
	 */
	public static final String APP_SERCET = "app_secret";

	/**
	 * 小程序数据验证Key
	 */
	public static final String SESSION_KEY = "session_key";

	/**
	 * unionId
	 */
	public static final String UNION_ID = "unionId";

	/**
	 * 网页授权token
	 */
	public static final String ACCESS_TOKEN = "accessToken";

	/**
	 * 网页授权刷新token
	 */
	public static final String REFRESH_TOKEN = "refreshToken";
	/**
	 * 用户岗位信息
	 */
	public static final String USER_POSTS = "user_posts";
	/**
	 * 用户机构信息
	 */
	public static final String USER_ORG_CODES = "user_org_codes";
	/**
	 * 用户按钮信息
	 */
	public static final String USER_MENULIST = "menuList";
	//---------------------登陆常量----------------------


	public static final String YM_PAY = "YM_PAY_MANA";    //问诊系统appKey

	//--------------------------- start 模板消息相关------------------------------------------

	/**
	 * 项目主页
	 */
	public static final String PRJ_HOME_PAGE = "/pages/publicWelfare/publicWelfareDetails/publicWelfareDetails?id=";
	/**
	 * 新项目通知模板
	 */
	public static final String TEMPLATE_PRJ_ID = "rYXYOImF6bvQ8_XZIv0QvXuakw_YMgS1yrMgBFpXk18";

	//--------------------------- end 模板消息相关------------------------------------------

	//---------------------菜单常量----------------------

	//转款按钮
	public static final String TRANSFER_MENU = "sys:fundDistribution:page|sys:fundDistribution:handle";

	//资金审核按钮
	public static final String FUND_AUDIT_MENU = "sys:fundApplication:page|sys:fundApplicationList:examine";

	//项目审核按钮
	public static final String PROJECT_AUDIT_MENU = "sys:project:page|sys:project:examine";

	//项目立项申请待办模板
	public static final String PROJECT_APPLY_BACKLOG = "{orgName}{auditName}已{auditStats}您的项目立项申请，请注意查看";
	//资金申请待办模板
	public static final String FUND_APPLY_BACKLOG = "{orgName}{auditName}已{auditStats}您的资金申请，请注意查看";

	//---------------------菜单常量----------------------

}
