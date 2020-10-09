package com.sun.cloud.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
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
@TableName("sys_sign")
public class SysSign extends Model<SysSign> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableField("id")
    private Long id;

    /**
     * 机构名称
     */
    @TableField("org_name")
    private String orgName;

    /**
     * 机构编码
     */
    @TableField("org_code")
    private String orgCode;

    /**
     * 应用关键字
     */
    @TableField("app_key")
    private String appKey;

    /**
     * 应用接口秘钥
     */
    @TableField("app_secret")
    private String appSecret;

    /**
     * 生效时间
     */
    @TableField("valid_time")
    private Date validTime;

    /**
     * 创建人
     */
    private Long creator;

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
