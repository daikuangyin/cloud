package com.sun.cloud.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author admin
 * @since 2020-09-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Member extends Model<Member> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户主键
     */
    @TableField("id")
    private Long id;


    /**
     * 用户编号
     */
    @TableField("the_no")
    private String theNo;

    /**
     * 签名ID
     */
    @TableField("sign_id")
    private Long signId;

    /**
     * 用户昵称
     */
    @TableField("nick_name")
    private String nickName;

    /**
     * 真实姓名
     */
    @TableField("real_name")
    private String realName;

    /**
     * 接受反馈手机号
     */
    @TableField("phone_no_extra")
    private String phoneNoExtra;

    /**
     * 手机号
     */
    @TableField("phone_no")
    private String phoneNo;

    /**
     * 登录密码
     */
    @TableField("user_pwd")
    private String userPwd;

    /**
     * 性别 0：未知、1：男、2：女
     */
    private String sex;

    /**
     * 联系地址
     */
    @TableField("contact_addr")
    private String contactAddr;

    /**
     * 身份证	1 港澳台通行证	10部队干部离休证	11工商执照	12单位证明（含公章）	13驾驶证	14学生证	2教师证	3户口本/居住证	4老人证	5组织机构代码证	6军官证	7护照	8虚拟证件	9
     */
    @TableField("card_type")
    private String cardType;

    /**
     * 证件号码
     */
    @TableField("card_no")
    private String cardNo;

    /**
     * 出生日期
     */
    private Date birthday;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 01游客02正常03禁用
     */
    @TableField("user_state")
    private String userState;

    /**
     * 上次登录时间
     */
    @TableField("last_login_time")
    private Date lastLoginTime;

    /**
     * 登录设备
     */
    @TableField("login_device")
    private String loginDevice;

    /**
     * 登录IP
     */
    @TableField("login_ip")
    private String loginIp;

    /**
     * 盐值
     */
    private String salt;

    /**
     * 支付密码
     */
    @TableField("pay_pwd")
    private String payPwd;

    /**
     * 授权信息时间
     */
    @TableField("info_time")
    private Date infoTime;

    /**
     * 授权手机时间
     */
    @TableField("phone_time")
    private Date phoneTime;

    /**
     * 微信头像
     */
    @TableField("head_url")
    private String headUrl;

    /**
     * unionId
     */
    private String unionId;

    /**
     * 小程序openId
     */
    @TableField("open_id")
    private String openId;

    /**
     * 公众号openId
     */
    @TableField("open_id_mp")
    private String openIdMp;

    /**
     * 语言
     */
    private String language;

    /**
     * 城市
     */
    private String city;

    /**
     * 省份
     */
    private String province;

    /**
     * 国家
     */
    private String country;

    /**
     * 区域
     */
    private String area;

    /**
     * 邮编
     */
    @TableField("post_code")
    private String postCode;

    /**
     * 积分
     */
    private Long integral;

    /**
     * 历史积分
     */
    @TableField("his_integral")
    private Long hisIntegral;

    /**
     * 用户等级
     */
    @TableField("user_level")
    private Integer userLevel;

    /**
     * 学校补充
     */
    @TableField("school_replenish")
    private String schoolReplenish;

    /**
     * 工作单位
     */
    @TableField("work_unit")
    private String workUnit;

    /**
     * 删除标志
     */
    private String deleted;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 修改人
     */
    private Long modifier;

    /**
     * 修改时间
     */
    @TableField("modify_time")
    private Date modifyTime;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
