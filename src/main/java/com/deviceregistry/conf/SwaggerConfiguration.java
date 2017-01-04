package com.deviceregistry.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Configure the
 * <a href="http://swagger.io/swagger-2-0-tooling-released/">Swagger</a> for
 * this project.<br>
 * The swagger documentation is available here:
 * <ul>
 * <li>http://localhost:8889/v2/api-docs
 * <li>http://localhost:8889/swagger-ui.html
 * </ul>
 *
 * @author Kazim Ulusoy
 */
@Configuration
@EnableSwagger2
@ComponentScan(basePackages = {"com.deviceregistry.controller"})
public class SwaggerConfiguration {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build()
				.pathMapping("/");
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Spring REST Service for Device Registry")
				.description("Device Registry")
				.contact("Kazim ULUSOY")
				.version("2.0")
				.build();
	}
}
