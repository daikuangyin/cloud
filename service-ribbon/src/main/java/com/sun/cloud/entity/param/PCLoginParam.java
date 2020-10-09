package com.sun.cloud.entity.param;

import com.sun.cloud.enums.LoginMethodEnum;
import com.sun.cloud.enums.LoginTypeEnum;
import com.sun.cloud.enums.constraint.CustomConstraint;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author: 张文博
 * @version: 2019年06月20日 10:05
 */
@Data
@ApiModel("登录")
public class PCLoginParam {

	@ApiModelProperty("账号")
	private String account;

	@ApiModelProperty("密码")
	private String pwd;

	@ApiModelProperty("登录类型,01会员02系统")
	@CustomConstraint(value = LoginTypeEnum.class)
	@NotEmpty
	private String loginType;

	@ApiModelProperty("登录方式")
	@CustomConstraint(value = LoginMethodEnum.class)
	@NotEmpty
	private String loginMethod;

	@ApiModelProperty("登录设备")
	private String device;

	@ApiModelProperty("验证码sessionId:验证码")
	private String authCode;

	@ApiModelProperty("备注")
	private String remark;
}
