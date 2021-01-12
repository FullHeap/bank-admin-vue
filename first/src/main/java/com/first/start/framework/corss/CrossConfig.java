package com.first.start.framework.corss;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * cross通用配置模式
 */
//@Configuration
//public class CrossConfig implements WebMvcConfigurer {
//
//	/**
//	 * web跨域访问配置
//	 */
//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//		// 设置允许跨域的路径
//		registry.addMapping("/**")
//				// 设置允许跨域请求的域名
//				.allowedOrigins("http://localhost")
//    			// 是否允许证书
//    			.allowCredentials(true)
//    			// 设置允许的方法
//    			.allowedMethods("GET", "POST", "DELETE", "PUT")
//    			// 设置允许的header属性
//    			.allowedHeaders("*")
//    			// 跨域允许时间
//    			.maxAge(3600);
//	}
//}

// spring bean配置模式
//@Configuration
//public class CrossConfig {
// 
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurerAdapter() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**");
//            }
//        };
//    }
//}

/* spring 过滤器配置模式 */
/**
* @ClassName: CrossConfig
* @Description: 跨域配置
* @author 忙碌的菠萝
* @date 2020年12月11日 下午1:59:29
*
*/
@Configuration
@ConditionalOnProperty(name={"spring.cross.enable"},havingValue = "true")
public class CrossConfig {
	
	@Value("${cross.allowed-header}")
	private String header;// https的端口
	
	@Value("${cross.allowed-method}")
	private String method;// https的端口

	@Value("${cross.allowed-http-origin}")
	private String httpOrigin;// http的端口
	
	@Value("${cross.allowed-https-origin}")
	private String httpsOrigin;// http的端口
 
    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin(httpOrigin);
        config.addAllowedOrigin(httpsOrigin);
        config.addAllowedHeader(header);
        config.addAllowedMethod(method);
//        config.setMaxAge(3600);
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }
}
