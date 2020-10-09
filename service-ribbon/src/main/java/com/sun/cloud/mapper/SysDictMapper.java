package com.sun.cloud.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sun.cloud.entity.SysDict;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2020-09-28
 */
public interface SysDictMapper extends BaseMapper<SysDict> {

    /**
     * 获取数据字典的值
     *
     * @param dictCode 力度大
     * @param itemCode 力度小
     * @return
     */
    String getDictValue(@Param("dictCode") String dictCode, @Param("itemCode") String itemCode);
}
