package com.elearning.in.Elearning.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CORSConfig implements WebMvcConfigurer {

   
	public void addCorsMappings(CorsRegistry registry) {
  registry.addMapping("/**"); /*.allowedMethods("HEAD", "GET", "PUT", "POST",
  "DELETE", "PATCH");*/ }

   
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

}
