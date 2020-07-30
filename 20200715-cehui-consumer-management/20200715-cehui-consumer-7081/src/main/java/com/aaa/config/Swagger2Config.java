package com.aaa.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.aaa.staticproperties.Swagger2.*;

/**
 * @Company AAA软件教育
 * @Author Zhao.Hhuan
 * @Date Create in 2020/7/27 17:50
 * @Description swagger的使用
 **/
@Configuration
@EnableSwagger2
public class Swagger2Config {

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/27 17:51
     * @description:
     *      swagger的构建器
     * @param
     * @return springfox.documentation.spring.web.plugins.Docket
     **/
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()//选择swagger生效的接口是什么
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE_PATH))
                .paths(PathSelectors.any())
                .build();
    }
    /**
     * @author Zhao.Hhuan
     * @date 2020/7/27 17:54
     * @description:
     *      构建项目的描述信息
     * @param
     * @return springfox.documentation.service.ApiInfo
     **/
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title(PROJECT_TITLE)
                .contact(new Contact(CONTACT_INFO_NAME,CONTACT_INFO_URL,CONTACT_INFO_EMAIL))
                .version(PROJECT_VERSION).build();
    }
}
