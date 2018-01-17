package com.test.app;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.test.app.Controller.Interceptor;
import com.test.app.Utils.SpringUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.*;


@EnableRedisHttpSession
@SpringBootApplication
@Configuration
public class Application extends WebMvcConfigurerAdapter{
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    @Override
    public  void addCorsMappings (CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .allowedOrigins("*");
    }
    @Bean
    public HttpMessageConverters useConverters () {
        return new HttpMessageConverters(new FastJsonHttpMessageConverter());
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/pics/**").addResourceLocations("file:C:/Users/Administrator/IdeaProjects/zxcWebtest/WEB-INF/pics/");
        super.addResourceHandlers(registry);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new Interceptor()).addPathPatterns("/**").excludePathPatterns("/springboot/login");
        super.addInterceptors(registry);
    }
    @Bean
    public SpringUtil springUtil2(){return new SpringUtil();}
}
