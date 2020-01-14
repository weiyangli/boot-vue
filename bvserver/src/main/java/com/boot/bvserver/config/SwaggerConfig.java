package com.boot.bvserver.config;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Slf4j
@Configuration
@EnableSwagger2
//@EnableSwaggerBootstrapUI
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                //此包路径下的类，才生成接口文档
                .apis(RequestHandlerSelectors.basePackage("com.boot.bvserver.controller"))
                .paths(PathSelectors.any())
                // 加了 ApiOperation 注解的类，才生成接口文档
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .build().apiInfo(apiInfo());
    }

    /**
     * api文档的详细信息函数,注意这里的注解引用的是哪个
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 标题名称
                .title("bv 后台服务API接口文档")
                // 版本号
                .version("1.0")
                // 许可人URL
				.termsOfServiceUrl("NO terms of service")
                // 描述
                .description("后台API接口")
                // 许可人，许可证
                .license("The Apache License, Version 2.0")
                // 许可URL
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .contact(contact())
                .build();
    }

    // 设置联系方式
    private Contact contact() {
        return new Contact("Wilson", "www.babidu.com", "1111@163.com");
    }
}
