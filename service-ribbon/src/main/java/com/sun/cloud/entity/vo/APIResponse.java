package com.sun.cloud.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 返回的参数封装类
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "通用响应返回对象")
@Data
public class APIResponse<T> {

	@ApiModelProperty(value = "结果代码, 0失败,1成功", position = 0)
	private String code;

	@ApiModelProperty(value = "错误信息", position = 1)
	private String msg = "操作成功";

	@ApiModelProperty(value = "结果数据", position = 2)
	private T data;
}
