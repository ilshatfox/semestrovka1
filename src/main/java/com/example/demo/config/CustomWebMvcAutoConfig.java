//package com.example.demo.config;
//
//import org.springframework.beans.factory.ListableBeanFactory;
//import org.springframework.beans.factory.ObjectProvider;
//import org.springframework.boot.autoconfigure.AutoConfigureAfter;
//import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
//import org.springframework.boot.autoconfigure.web.ResourceProperties;
//import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
//import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
//import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//
//@Configuration
//@AutoConfigureAfter(DispatcherServletAutoConfiguration.class)
//public class CustomWebMvcAutoConfig extends WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter {
//
//    public CustomWebMvcAutoConfig(ResourceProperties resourceProperties, WebMvcProperties mvcProperties, ListableBeanFactory beanFactory, ObjectProvider<HttpMessageConverters> messageConvertersProvider, ObjectProvider<WebMvcAutoConfiguration.ResourceHandlerRegistrationCustomizer> resourceHandlerRegistrationCustomizerProvider) {
//        super(resourceProperties, mvcProperties, beanFactory, messageConvertersProvider, resourceHandlerRegistrationCustomizerProvider);
//    }
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        String myExternalFilePath = "file:///C:/Temp/whatever/m/";
//
//        registry.addResourceHandler("/m/**").addResourceLocations(myExternalFilePath);
//
//        super.addResourceHandlers(registry);
//    }
//
//}