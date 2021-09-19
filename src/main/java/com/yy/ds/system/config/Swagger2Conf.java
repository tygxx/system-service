package com.yy.ds.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Conf {
    @Bean
    public Docket getUserDocket() {
        //apiInfo： 添加api详情信息，参数为ApiInfo类型的参数，这个参数包含了第二部分的所有信息比如标题、描述、版本之类的，开发中一般都会自定义这些信息
        ApiInfo apiInfo = new ApiInfoBuilder().title("系统管理")// api标题
                .description("system相关接口描述")// api描述
                .version("1.0.0")// 版本号
                .build();
                // 构造函数传入初始化规范，这是swagger2规范
        return new Docket(DocumentationType.SWAGGER_2)// 文档类型（swagger2）
                .apiInfo(apiInfo)// 设置包含在json ResourceListing响应中的api元信息
                .select()// 启动用于api选择的构建器
                .apis(RequestHandlerSelectors.basePackage("com.yy.ds.system"))// 扫描接口的包
                .paths(PathSelectors.any())// 路径过滤器（扫描所有路径）,哪些路径的api会被显示出来
                .build();
    }
}