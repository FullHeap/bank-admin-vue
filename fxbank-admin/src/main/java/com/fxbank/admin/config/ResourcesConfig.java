package com.fxbank.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 通用配置
 * 
 * @author ruoyi
 */
@Configuration
public class ResourcesConfig implements WebMvcConfigurer
{
   
    /**
     * 跨域配置
     */
    @Bean
    public CorsFilter corsFilter()
    {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        // 设置访问源地址
        config.addAllowedOrigin("*");
        // 设置访问源请求头
        config.addAllowedHeader("*");
        // 设置访问源请求方法
        config.addAllowedMethod("*");
        // 对接口配置跨域设置
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
    
    /**
     * web跨域访问配置
     */
    @Override
    public void addCorsMappings(CorsRegistry registry)
    {
    	// 设置允许跨域的路径
    	registry.addMapping("/**")
    			// 设置允许跨域请求的域名
    			.allowedOrigins("*")
    			// 是否允许证书
    			.allowCredentials(true)
    			// 设置允许的方法
    			.allowedMethods("GET", "POST", "DELETE", "PUT")
    			// 设置允许的header属性
    			.allowedHeaders("*")
    			// 跨域允许时间
    			.maxAge(3600);
    }
}
