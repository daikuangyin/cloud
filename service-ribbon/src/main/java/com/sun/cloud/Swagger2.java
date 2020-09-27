package com.sun.cloud;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.IOException;

/**
 * Swagger2 配置
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
public class Swagger2 {

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.sun.cloud.controller"))
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("service-ribbon Swagger Restful API")
				.description("service-ribbon接口文档API")
				.license("service-ribbon")
//				.contact(new Contact("ribbon", "https://www.yamimap.com/", "yamidaohang@yamimap.com"))
				.version("1.0")
				.build();
	}

	/**
	 * 打开Swagger 配置
	 */
	@Configuration
	@ConditionalOnExpression("#{systemProperties['os.name'].contains('Windows')}")
	public static class OpenBrowser implements CommandLineRunner {

		@Value("http://localhost:${server.port}/doc.html")
		private String url;

		@Override
		public void run(String... args) {
			try {
				Runtime.getRuntime().exec("cmd /c start " + url);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
