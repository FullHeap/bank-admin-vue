package com.first.start.common.config;

import javax.annotation.Resource;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.first.start.common.filter.SignInterceptor;

@Configuration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class WebMvcConfig implements WebMvcConfigurer {

	@Resource
	private SignInterceptor signInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(signInterceptor)
                .addPathPatterns("/**")// 所有请求都需要进行报文签名sign
				.addPathPatterns("/systemLogin")
				.addPathPatterns("/systemLogin")
				.excludePathPatterns("/html/*", "/js/*");
	}
}
