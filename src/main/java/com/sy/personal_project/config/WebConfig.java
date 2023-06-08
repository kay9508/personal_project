package com.sy.personal_project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")
                .allowedOrigins("https://example.com")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .maxAge(3600);
        //이 코드는 모든 요청 경로에 대해 https://example.com에서 요청을 허용하고, GET, POST, PUT, DELETE HTTP 메서드를 허용하며, 모든 HTTP 헤더를 허용하고, 요청 캐시 시간을 3600초로 설정합니다.

    }
}
