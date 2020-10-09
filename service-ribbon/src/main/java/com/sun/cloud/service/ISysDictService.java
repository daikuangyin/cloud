package com.sun.cloud.service;


import com.baomidou.mybatisplus.service.IService;
import com.sun.cloud.entity.SysDict;
import com.sun.cloud.enums.DictEnum;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2020-09-28
 */
public interface ISysDictService extends IService<SysDict> {

    /**
     *根据Key获取数据字典的一个值
     * @param enumClass
     * @param value
     * @return
     */
    String getDictValue(Class<? extends DictEnum> enumClass, String value);



    /**
     * 根据Key获取数据字典的一个值
     *
     * @param dictCode 字典的枚举dictCode
     * @param itemCode 字典的值itemCode
     * @return
     */
    String getDictValue(String dictCode, String itemCode);
}
