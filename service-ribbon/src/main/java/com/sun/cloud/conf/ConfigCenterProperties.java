package com.sun.cloud.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 用户自定义配置
 */
@ConfigurationProperties(prefix = "config-center")
@Component
@Data
public class ConfigCenterProperties {


	/**
	 * 枚举扫描
	 */
	private boolean enumsScan;

}
