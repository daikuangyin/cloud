package com.sun.cloud.service.impl;


import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sun.cloud.entity.SysDict;
import com.sun.cloud.enums.DictEnum;
import com.sun.cloud.mapper.SysDictMapper;
import com.sun.cloud.service.ISysDictService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2020-09-28
 */
@Service
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements ISysDictService {


    @Override
    public String getDictValue(Class<? extends DictEnum> dictCode, String itemCode) {
        // 这样调用，为了走spring代理缓存
        return SpringUtil.getBean(ISysDictService.class).getDictValue(dictCode.getSimpleName(), itemCode);
    }

    @Override
    public String getDictValue(String dictCode, String itemCode) {
        return baseMapper.getDictValue(dictCode, itemCode);
    }

}
