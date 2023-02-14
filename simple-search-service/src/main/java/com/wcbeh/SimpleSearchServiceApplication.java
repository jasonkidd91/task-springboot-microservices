package com.wcbeh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@EnableDiscoveryClient
@SpringBootApplication
public class SimpleSearchServiceApplication {
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
	/**
	 * Rest template bean to be used communicate with others registered services in
	 * the eureka discovery server
	 * @return
	 */
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SimpleSearchServiceApplication.class, args);
	}

}
