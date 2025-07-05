package com.my.jjystd.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl("http://localhost:8080");
        devServer.setDescription("开发环境服务器");

        Contact contact = new Contact();
        contact.setName("学生成绩管理系统");
        contact.setEmail("admin@example.com");
        contact.setUrl("https://www.example.com");

        License license = new License()
                .name("MIT License")
                .url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("学生成绩管理系统 API")
                .version("1.0.0")
                .contact(contact)
                .description("这是学生成绩管理系统的API文档，提供了学生、教师、课程、用户和成绩相关的接口。")
                .license(license);

        return new OpenAPI()
                .info(info)
                .servers(List.of(devServer));
    }
} 