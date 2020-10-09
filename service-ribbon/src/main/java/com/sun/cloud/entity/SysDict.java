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
@TableName("sys_dict")
public class SysDict extends Model<SysDict> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableField("id")
    private Long id;

    /**
     * 字典名称(中文)
     */
    @TableField("dict_name")
    private String dictName;

    /**
     * 字典编码(英文)
     */
    @TableField("dict_code")
    private String dictCode;

    /**
     * 条目名称(中文)
     */
    @TableField("item_name")
    private String itemName;

    /**
     * 条目编码
     */
    @TableField("item_code")
    private String itemCode;

    /**
     * 条目描述(中文页面展示)
     */
    @TableField("item_desc")
    private String itemDesc;

    /**
     * 条目顺序
     */
    @TableField("item_order")
    private Integer itemOrder;

    /**
     * 条目级别
     */
    @TableField("item_level")
    private Integer itemLevel;

    /**
     * 条目状态
     */
    @TableField("item_state")
    private String itemState;

    /**
     * 父类条目编码
     */
    @TableField("parent_item_code")
    private String parentItemCode;

    /**
     * 创建人
     */
    private Long creater;

    /**
     * 创建时间
     */
    private Date createtime;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
