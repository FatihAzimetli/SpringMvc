package com.tpe;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@ComponentScan("com.tpe")
@EnableWebMvc//MVC config etkinleştirmek için
public class WebMvcConfig implements WebMvcConfigurer {

    //view resolver için bir bean oluşturalım
    @Bean
    public InternalResourceViewResolver resolver(){
        InternalResourceViewResolver resolver=new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);//JavaStandartTagLibrary:jsp içinde daha az java kodu yazmamızı sağlar
        resolver.setPrefix("/WEB-INF/views/");//view dosyaları nerede
        resolver.setSuffix(".jsp");//sayfa isminin uzantısı nedir?
        return resolver;
    }

    //statik kaynakların dispatcher servleta gönderilmesine gerek yok
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/resources/**").//bu url ile gelen requestleri statik olarak sun
                addResourceLocations("/resources/").//statik kaynakların yeri
                setCachePeriod(0);//cache periyodu için süre verilebilir.

    }

}
