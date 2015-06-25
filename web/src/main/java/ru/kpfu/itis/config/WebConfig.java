package ru.kpfu.itis.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@EnableSwagger
@ComponentScan(basePackages = {"ru.kpfu.itis.controller"})
public class WebConfig extends WebMvcConfigurerAdapter {

    private SpringSwaggerConfig springSwaggerConfig;

    @Autowired
    public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
        this.springSwaggerConfig = springSwaggerConfig;
    }

    @Bean
    public SwaggerSpringMvcPlugin customImplementation() {
        return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
                .apiInfo(apiInfo());
    }

    /*
        write ur own api info here
     */
    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "Api Довольный Студент",
                "Примеры запросов. API v1",
                "Terms..",
                "JetBrains email here",
                "please read the license terms...",
                "jetBrains.kpfu.ru"
        );

        return apiInfo;
    }


    @Bean
    public ViewResolver resolver() {
        InternalResourceViewResolver url = new InternalResourceViewResolver();
        url.setPrefix("/WEB-INF/pages/");
        url.setSuffix(".jsp");
        return url;
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");

        /*
        *   swagger UI resources
        * */
        registry.addResourceHandler("/css/**").addResourceLocations("/api/css/");
        registry.addResourceHandler("/images/**").addResourceLocations("/api/images/");
        registry.addResourceHandler("/js/**").addResourceLocations("/api/js/");
        registry.addResourceHandler("/fonts/**").addResourceLocations("/api/fonts/");
        registry.addResourceHandler("/lib/**").addResourceLocations("/api/lib/");
        registry.addResourceHandler("*.html").addResourceLocations("/");
    }


    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
    }

    @Bean
    public String getBean(ObjectMapper mapper) {
        return "";
    }
}
