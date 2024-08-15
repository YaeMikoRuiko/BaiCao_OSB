package com.wen.common;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
    private static final long Max_AGE = 24*60*60;
    private CorsConfiguration buildConfig(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");//允许所有的域
        corsConfiguration.addAllowedHeader("*");//允许所有头字段
        corsConfiguration.addAllowedMethod("*");//允许所有请求类型
        corsConfiguration.setMaxAge(Max_AGE);
        return corsConfiguration;
    }
    @Bean
    public CorsFilter corsFilter(){//过滤器
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",buildConfig());;//设置映射路径
        return new CorsFilter(source);
    }
}
