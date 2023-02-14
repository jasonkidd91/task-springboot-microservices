package com.wcbeh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@EnableEurekaServer
@SpringBootApplication
public class SimpleDiscoveryServerApplication {
	/**
	 * Spring MVC provided filter to log request URL, body and other related information
	 * @return
	 */
	@Bean
	public CommonsRequestLoggingFilter requestLoggingFilter() {
	    CommonsRequestLoggingFilter requestLoggingFilter = new CommonsRequestLoggingFilter();
	    requestLoggingFilter.setIncludeClientInfo(true);
	    requestLoggingFilter.setIncludeHeaders(false);
	    requestLoggingFilter.setIncludeQueryString(true);
	    requestLoggingFilter.setIncludePayload(true);
	    return requestLoggingFilter;
	}

	public static void main(String[] args) {
		SpringApplication.run(SimpleDiscoveryServerApplication.class, args);
	}

}
