package com.example.createxml;

import io.swagger.annotations.Api;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lixing
 * 生成xml文件
 */
@SpringBootApplication(exclude = {FreeMarkerAutoConfiguration.class},scanBasePackages = "")
@RestController
@Api(tags = "swagger2") //http://127.0.0.1:8080/swagger-ui.html
@EnableCaching
public class CreatexmlApplication {
    public static void main(String[] args) {
        SpringApplication.run(CreatexmlApplication.class, args);
    }

}
