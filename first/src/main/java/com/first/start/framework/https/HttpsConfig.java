package com.first.start.framework.https;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: HttpsConfig
 * @Description: http重定向至https
 * @author 忙碌的菠萝
 * @date 2020年12月10日 下午1:47:22
 *
 */
@Configuration
public class HttpsConfig {

	@Value("${server.port}")
	private int httpsPort;// https的端口

	@Value("${server.http-port}")
	private int httpPort;// http的端口

	/*
	 * 配置TomcatServletWebServerFactory， 添加Tomcat中的Connector（监听8080端口）, 将请求转发到8443端口
	 */
	@Bean
	TomcatServletWebServerFactory tomcatServletWebServerFactory() {
		TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory() {
			@Override
			protected void postProcessContext(Context context) {
				SecurityConstraint constraint = new SecurityConstraint();
				constraint.setUserConstraint("CONFIDENTIAL");
				SecurityCollection collection = new SecurityCollection();
				collection.addPattern("/*");
				constraint.addCollection(collection);
				context.addConstraint(constraint);
			}
		};
		factory.addAdditionalTomcatConnectors(createTomcatConnector());
		return factory;
	}

	@Bean
	public Connector createTomcatConnector() {
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		connector.setScheme("http");
		connector.setPort(httpPort);
		connector.setSecure(false);
		connector.setRedirectPort(httpsPort);
		return connector;
	}
}
