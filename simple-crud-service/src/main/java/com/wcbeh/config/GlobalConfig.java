package com.wcbeh.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class GlobalConfig {
	/**
	 * CORS enabled configuration
	 * @return
	 */
	@Bean public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
					.allowedMethods("GET", "POST", "DELETE", "PUT")
					.allowedOrigins("*");
			}
		};
	}
	/**
	 * Thread executor configuration
	 * @return ExecutorService
	 */
	@Bean public ExecutorService TaskExecutor() {
	    ExecutorService executor = Executors.newFixedThreadPool(10,
	    		new CustomizableThreadFactory("CrudServiceThread-"));
	    return executor;
	}
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
	
}
