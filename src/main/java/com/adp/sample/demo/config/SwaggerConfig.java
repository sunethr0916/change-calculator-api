package com.adp.sample.demo.config;

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

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.adp.sample.demo")).paths(PathSelectors.any()).build();

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("User change calculator")
                .description("Returns change for bills passed")
                .contact(new Contact("Sunethra Duvvuru", "https://github.com/sunethr0916/change-calculator-api", "duvvuru.work@gmail.com"))
                .build();
    }
}
