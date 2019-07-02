package com.study.boot.mybatis.config;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Xingyu Sun
 * @date 2018/12/24 10:17
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .produces(produces())
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build();
    }

    private Set<String> produces() {
        Set<String> produces = new HashSet<>();
        produces.add("application/json;charset=utf-8");
        return produces;
    }

    @Bean
    public ApiInfo apiInfo() {
        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
        return apiInfoBuilder.title("mybatis boot test")
                .version("2.0")
                .description("mybatis-plus swagger demo")
                .license("apache license")
                .licenseUrl("http://www.apache.org/")
                .termsOfServiceUrl("https://www.baidu.com")
                .contact(new Contact("XingYu Sun", "https://www.baidu.com", "12870621552@qq.com"))
                .build();
    }
}
