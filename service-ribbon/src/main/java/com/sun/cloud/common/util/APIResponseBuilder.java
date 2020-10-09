package com.sun.cloud.common.util;


import com.sun.cloud.entity.vo.APIResponse;
import com.sun.cloud.enums.ResultCode;

public class APIResponseBuilder {

    /**
     * 返回成功信息，不返回数据，返回默认成功代码
     *
     * @return APIResponse
     */
    public static APIResponse<Void> successNoData() {
        APIResponse<Void> result = new APIResponse<>();
        result.setCode(ResultCode.SUCCESS.getCode().toString());
        result.setMsg(ResultCode.SUCCESS.getMsg());
        return result;
    }

    /**
     * 返回成功信息，不返回数据，返回指定成功代码
     *
     * @param msg 成功信息
     * @return APIResponse
     */
    public static APIResponse<Void> successNoDataWithMsg(String msg) {
        APIResponse<Void> result = new APIResponse<>();
        result.setCode(ResultCode.SUCCESS.getCode().toString());
        result.setMsg(msg);
        return result;
    }

    /**
     * 返回成功信息，不返回数据，返回指定成功代码
     *
     * @param code 成功代码
     * @return APIResponse
     */
    public static APIResponse<Void> successNoData(ResultCode code) {
        APIResponse<Void> result = new APIResponse<>();
        result.setCode(code.getCode().toString());
        result.setMsg(code.getMsg());
        return result;
    }

    /**
     * 返回成功信息,返回默认成功代码
     *
     * @param data 返回数据
     * @return APIResponse
     */
    public static <T> APIResponse<T> success(T data) {
        APIResponse<T> result = new APIResponse<T>();
        result.setCode(ResultCode.SUCCESS.getCode().toString());
        result.setMsg(ResultCode.SUCCESS.getMsg());
        result.setData(data);
        return result;
    }

    /**
     * 返回成功信息，不返回数据，返回指定成功代码
     *
     * @param msg  成功信息
     * @param data 返回数据
     * @return APIResponse
     */
    public static <T> APIResponse<T> successWithMsg(String msg, T data) {
        APIResponse<T> result = new APIResponse<>();
        result.setCode(ResultCode.SUCCESS.getCode().toString());
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    /**
     * 返回成功信息，返回指定成功代码
     *
     * @param code 成功代码
     * @param data 返回数据
     * @return APIResponse
     */
    public static <T> APIResponse<T> success(ResultCode code, T data) {
        APIResponse<T> result = new APIResponse<>();
        result.setCode(code.getCode().toString());
        result.setMsg(code.getMsg());
        result.setData(data);
        return result;
    }

    /**
     * 返回默认失败信息，返回默认失败代码
     *
     * @return APIResponse
     */
    public static APIResponse<Void> fail() {
        APIResponse<Void> result = new APIResponse<>();
        result.setCode(ResultCode.FAIL.getCode().toString());
        result.setMsg(ResultCode.FAIL.getMsg());
        return result;
    }

    /**
     * 返回默认失败信息，返回默认失败代码
     *
     * @return APIResponse
     */
    public static APIResponse<Void> failWithMsg(String msg) {
        APIResponse<Void> result = new APIResponse<>();
        result.setCode(ResultCode.FAIL.getCode().toString());
        result.setMsg(msg);
        return result;
    }

    /**
     * 返回指定失败信息,返回指定失败代码
     *
     * @param code 异常代码
     * @return APIResponse
     */
    public static APIResponse<Void> fail(ResultCode code) {
        APIResponse<Void> result = new APIResponse<>();
        result.setCode(code.getCode().toString());
        result.setMsg(code.getMsg());
        return result;
    }
}
