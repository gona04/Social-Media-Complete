package com.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.WebContentInterceptor;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com." })
public class WebConfig extends WebMvcConfigurerAdapter {

	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver getCommonsMultipartResolver() 
	{
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(20971520); // 20MB
		multipartResolver.setMaxInMemorySize(1048576); // 1MB
		return multipartResolver;
	}

	//THE FOLLOWING METHODS ADDED LATER FOR CHAT MODULE FROM RAJCHAT 
	 @Override
	  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
	    configurer.enable();
	  }

	  @Bean
	  public WebContentInterceptor webContentInterceptor() {
	    WebContentInterceptor interceptor = new WebContentInterceptor();
	    interceptor.setCacheSeconds(0);
	    interceptor.setUseExpiresHeader(true);
	    interceptor.setUseCacheControlHeader(true);
	    interceptor.setUseCacheControlNoStore(true);

	    return interceptor;
	  }

	  @Override
	  public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/lib/**").addResourceLocations("/lib/");
	    registry.addResourceHandler("/app/**").addResourceLocations("/app/");
	    registry.addResourceHandler("/assets/**").addResourceLocations("/assets/");
	  }

	  @Override
	  public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(webContentInterceptor());
	  }

}