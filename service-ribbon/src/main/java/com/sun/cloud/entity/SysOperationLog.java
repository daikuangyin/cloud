package com.sun.cloud.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;


import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统操作日志表
 * </p>
 *
 * @author admin
 * @since 2020-09-27
 */
@Data
@Accessors(chain = true)
@TableName("sys_operation_log")
public class SysOperationLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 请求类型
     */
    private String method;

    /**
     * 请求uri
     */
    private String uri;

    /**
     * ip
     */
    private String ip;

    /**
     * 调用方法
     */
    private String controller;

    /**
     * body参数
     */
    @TableField("body_params")
    private String bodyParams;

    /**
     * query参数
     */
    @TableField("query_params")
    private String queryParams;

    /**
     * 操作类型
     */
    @TableField("operation_type")
    private String operationType;

    /**
     * 账号
     */
    private String account;

    /**
     * 终端 患者/医生
     */
    private String terminal;

    /**
     * 结果
     */
    private String success;

    /**
     * 耗时
     */
    private String time;

    /**
     * 创建人
     */
    private Long creator;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;


}
