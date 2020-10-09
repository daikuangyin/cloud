package com.sun.cloud.common.util;


import com.sun.cloud.common.constant.Global;
import com.sun.cloud.entity.Member;
import com.sun.cloud.entity.vo.SysUserVo;
import com.sun.cloud.enums.DictEnum;
import com.sun.cloud.enums.LoginTypeEnum;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Author: zhangfengzhou
 * Date: 2019-04-04
 * Time: 16:05
 */
public class ShiroUtil {

	public static Subject getSubject() {
		try {
			return SecurityUtils.getSubject();
		} catch (Exception e) {
			return null;
		}
	}

	public static Session getSession() {
		return SecurityUtils.getSubject().getSession();
	}

	public static String getIp() {
		return getSubject().getSession().getHost();
	}

	public static String getSessionId() {
		return String.valueOf(getSubject().getSession().getId());
	}

	public static void logout() {
		getSubject().logout();
	}

	public static SysUserVo getCurrentSysUser(boolean... catchException) {
		try {
			SysUserVo sysUserVo = getSubject().getPrincipals().oneByType(SysUserVo.class);
			return sysUserVo;
		} catch (Exception e) {
			if (catchException.length > 0 && catchException[0]) {
				return null;
			} else {
				throw new ExpiredCredentialsException();
			}
		}
	}

	public static Member getCurrentAppUser(boolean... catchException) {
		try {
			Member member = getSubject().getPrincipals().oneByType(Member.class);
			return member;
		} catch (Exception e) {
			if (catchException.length > 0 && catchException[0]) {
				return null;
			} else {
				throw new ExpiredCredentialsException();
			}
		}
	}

	/**
	 * 获取登录人的ID
	 *
	 * @return
	 */
	public static Long getLoginId(boolean... catchException) {
		Long id = null;
		try {
			Object loginTypeStr = ShiroUtil.getSession().getAttribute(String.valueOf(Global.LOGIN_TYPE));
			if (loginTypeStr == null) {
				throw new ExpiredCredentialsException();
			}
			// 表示已经登录
			LoginTypeEnum loginTypeEnum = DictEnum.valueOfEnum(LoginTypeEnum.class, String.valueOf(loginTypeStr));
			if (loginTypeEnum == LoginTypeEnum.MEMBER) {
				id = getCurrentAppUser().getId();
			}
		} catch (Exception e) {
			if (catchException.length > 0 && catchException[0]) {
				return null;
			} else {
				throw new ExpiredCredentialsException();
			}
		}
		return id;
	}

	/**
	 * 获取登录人的手机号码
	 *
	 * @return
	 */
	public static String getLoginPhone(boolean... catchException) {
		String phone = null;
		try {
			Object loginTypeStr = ShiroUtil.getSession().getAttribute(String.valueOf(Global.LOGIN_TYPE));
			if (loginTypeStr == null) {
				throw new ExpiredCredentialsException();
			}
			// 表示已经登录
			LoginTypeEnum loginTypeEnum = DictEnum.valueOfEnum(LoginTypeEnum.class, String.valueOf(loginTypeStr));
			if (loginTypeEnum == LoginTypeEnum.MEMBER) {
				phone = getCurrentAppUser().getPhoneNo();
			}
		} catch (Exception e) {
			if (catchException.length > 0 && catchException[0]) {
				return null;
			} else {
				throw new ExpiredCredentialsException();
			}
		}
		return phone;
	}

	public static String genHashPass(String pwd, String salt) {
		//密码加密
//        RandomNumberGenerator saltGen = new SecureRandomNumberGenerator();
//        String salt = saltGen.nextBytes().toBase64();
		String hashedPwd = new Sha256Hash(pwd, salt, 1024).toBase64();
		return hashedPwd;
	}

	public static void main(String[] args) {
		System.out.println(genHashPass("123456", "147258369"));
	}

}
