package ru.kpfu.itis.config;

import org.springframework.core.annotation.Order;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

@Order(1)
public class WebAppInitializer extends
        AbstractAnnotationConfigDispatcherServletInitializer {


    //{!begin addToRootContext}
    @Override
    public Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{WebSecurityConfig.class, DataSourceConfig.class, PersistenceConfig.class, CoreConfig.class};
    }
    //{!end addToRootContext}

    @Override
    public Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebConfig.class};
    }

    @Override
    public String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        DelegatingFilterProxy springSecurityFilterChain = new DelegatingFilterProxy("springSecurityFilterChain");

        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);

        return new Filter[]{
                springSecurityFilterChain,
                characterEncodingFilter
        };
    }

}