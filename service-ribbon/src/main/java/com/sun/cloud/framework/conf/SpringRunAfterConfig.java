package com.sun.cloud.framework.conf;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.thread.GlobalThreadPool;

import cn.hutool.core.util.StrUtil;
import com.sun.cloud.conf.ConfigCenterProperties;
import com.sun.cloud.entity.SysDict;
import com.sun.cloud.enums.DictEnum;
import com.sun.cloud.enums.EnumDescription;
import com.sun.cloud.service.ISysDictService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * spring启动以后操作
 *
 * @author: 李涛
 * @version: 2019年11月20日 22:13
 */
@Component
public class SpringRunAfterConfig implements ApplicationRunner {

    private static final Logger LOG = LoggerFactory.getLogger(SpringRunAfterConfig.class);

    @Autowired
    private ISysDictService sysDictSV;

    @Autowired
    private ConfigCenterProperties configCenterProperties;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        // 刷新枚举字典
        refreshEnumDictData();

    }

    /**
     * 刷新枚举字典
     */
    public void refreshEnumDictData() {
        if (!configCenterProperties.isEnumsScan()) {
            return;
        }
        GlobalThreadPool.execute(() -> {
            List<Class<DictEnum>> definitionEnum = loadDictEnum();
            LOG.info("刷新枚举字典....");
            SysDict update = null;
            for (Class<DictEnum> DictEnumClass : definitionEnum) {
                try {
                    // 0.基础数据构造
                    DictEnum[] anEnums = DictEnumClass.getEnumConstants();
                    EnumDescription annotation = DictEnumClass.getAnnotation(EnumDescription.class);
                    String dictName = annotation.value();
                    String dictCode = DictEnumClass.getSimpleName();
                    // 1.字典数据构建
                    for (int dictSort = 1; dictSort < anEnums.length + 1; dictSort++) {
                        LOG.info("刷新......");
                        String itemCode = anEnums[dictSort - 1].getKey();
                        String itemName = anEnums[dictSort - 1].getValue();
                        update = new SysDict();
                        update.setDictName(dictName);
                        update.setDictCode(dictCode);
                        update.setItemName(itemName);
                        update.setItemCode(itemCode);
                        update.setItemOrder(dictSort);
                        update.setItemState("01");
                        update.setCreatetime(new Date());
                        update.setCreater(1L);
                        // 1.查询是否存在
                        String dictValue = sysDictSV.getDictValue(dictCode, itemCode);

                        if (StrUtil.isNotBlank(dictValue)) {
                            // 2新增字典数据
                            sysDictSV.insert(update);
                        }
                    }
                } catch (Exception e) {
                    LOG.error(cn.hutool.core.exceptions.ExceptionUtil.getRootCauseMessage(e));
                }
            }
            LOG.info("刷新枚举字典....完毕");
        });
    }

    /**
     * 获取定义的字典类
     *
     * @return
     */
    private List<Class<DictEnum>> loadDictEnum() {
        String DEFAULT_RESOURCE_PATTERN = "**/*.class";
        List<Class<DictEnum>> definitionEnum = new ArrayList<>();
        // 包名声明，可以多个逗号分隔
        String enumsPackage = "com.sun.cloud.enums";
        ResourcePatternResolver resolver = (ResourcePatternResolver) new PathMatchingResourcePatternResolver();
        MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resolver);
        try {
            for (String aliasesPackage : enumsPackage.split(",")) {
                String classPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
                        + ClassUtils.convertClassNameToResourcePath(aliasesPackage.trim()) + "/" + DEFAULT_RESOURCE_PATTERN;
                Resource[] resources = resolver.getResources(classPath);
                if (resources != null && resources.length > 0) {
                    MetadataReader metadataReader = null;
                    for (Resource resource : resources) {
                        if (resource.isReadable()) {
                            metadataReader = metadataReaderFactory.getMetadataReader(resource);
                            Class aClass = Class.forName(metadataReader.getClassMetadata().getClassName());
                            if (aClass.getInterfaces().length > 0 && aClass.getInterfaces()[0].equals(DictEnum.class)) {
                                Class<DictEnum> DictEnumClass = (Class<DictEnum>) aClass;
                                definitionEnum.add(DictEnumClass);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOG.error(ExceptionUtil.getRootCauseMessage(e));
        }
        return definitionEnum;
    }
}
