package com.sun.cloud.entity.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel(value = "系统用户")
public class SysUserVo implements Serializable {
	@ApiModelProperty("系统用户主键")
	private Long id;

	@ApiModelProperty("工号(用户名)")
	private String code;

	@ApiModelProperty("用户名称")
	private String name;

	@ApiModelProperty(value = "密码", hidden = true)
	@JsonIgnore
	private String pwd;

	@ApiModelProperty("电话号码")
	private String phone;

	@ApiModelProperty("身份证号")
	private String idnum;

	@ApiModelProperty("01 男 02女(字典)")
	private String sex;

	@ApiModelProperty("生日")
	private Date birthday;

	@ApiModelProperty("邮箱地址")
	private String mailbox;

	@ApiModelProperty("头像地址")
	private String iconUrl;

	@ApiModelProperty("用户状态 01正常 02停用(字典)")
	private String state;

	@ApiModelProperty(value = "盐值", hidden = true)
	@JsonIgnore
	private String salt;

	@ApiModelProperty("最后一次登录时间")
	private Date loginLastTime;

	@ApiModelProperty("省(字典)")
	private String addrProv;

	@ApiModelProperty("市区(字典)")
	private String addrCity;

	@ApiModelProperty("县(字典)")
	private String addrArea;

	@ApiModelProperty("乡镇(字典)")
	private String addrTown;

	@ApiModelProperty("街道(字典)")
	private String addrVillage;

	@ApiModelProperty("常住地址详细地址")
	private String addrDesc;

	@ApiModelProperty("创建人")
	private Long creator;

	@ApiModelProperty("创建时间")
	private Date createTime;

	@ApiModelProperty("备注")
	private String remarks;

	@ApiModelProperty("机构")
	private String agency;

	private String stateName;

	@ApiModelProperty("appkey")
	private String appKey;

	@ApiModelProperty(value = "密钥", hidden = true)
	private String appSecret;

}
