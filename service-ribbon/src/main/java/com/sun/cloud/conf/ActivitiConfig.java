package com.sun.cloud.conf;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.*;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;

@Slf4j
@Configuration
public class ActivitiConfig {
    @Bean
    public ProcessEngine processEngine(DataSourceTransactionManager transactionManager, DataSource dataSource) throws IOException {
        SpringProcessEngineConfiguration configuration = new SpringProcessEngineConfiguration();
        //自动部署已有的流程文件
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources(ResourceLoader.CLASSPATH_URL_PREFIX + "processes/*.bpmn");
        configuration.setTransactionManager(transactionManager);
        configuration.setDataSource(dataSource);
        configuration.setDatabaseSchemaUpdate("true");
        configuration.setDeploymentResources(resources);
        ProcessEngine processEngine = configuration.buildProcessEngine();
        log.info("processEngine============>>>> {}",processEngine.getName());
        return processEngine;
    }

    @Bean
    public RepositoryService repositoryService(ProcessEngine processEngine) {
        return processEngine.getRepositoryService();
    }

    @Bean
    public RuntimeService runtimeService(ProcessEngine processEngine) {
        return processEngine.getRuntimeService();
    }

    @Bean
    public TaskService taskService(ProcessEngine processEngine) {
        return processEngine.getTaskService();
    }

    @Bean
    public HistoryService historyService(ProcessEngine processEngine) {
        return processEngine.getHistoryService();
    }

    @Bean
    public ManagementService managementService(ProcessEngine processEngine) {
        return processEngine.getManagementService();
    }

    @Bean
    public IdentityService identityService(ProcessEngine processEngine) {
        return processEngine.getIdentityService();
    }
}
