package ru.kpfu.itis.config;

import org.springframework.core.annotation.Order;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import static org.lightadmin.core.util.LightAdminConfigurationUtils.LIGHT_ADMINISTRATION_BACK_TO_SITE_URL;
import static org.lightadmin.core.util.LightAdminConfigurationUtils.LIGHT_ADMINISTRATION_BASE_PACKAGE;
import static org.lightadmin.core.util.LightAdminConfigurationUtils.LIGHT_ADMINISTRATION_BASE_URL;
//import static org.lightadmin.logging.configurer.LoggingConfigurerSettings.LIGHT_CONFIGURER_BACK_TO_SITE_URL;
//import static org.lightadmin.logging.configurer.LoggingConfigurerSettings.LIGHT_CONFIGURER_BASE_URL;

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

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        servletContext.setInitParameter(LIGHT_ADMINISTRATION_BASE_URL, "/admin");
//        servletContext.setInitParameter(LIGHT_CONFIGURER_BASE_URL, "/logger");
//        servletContext.setInitParameter(LIGHT_CONFIGURER_BACK_TO_SITE_URL, "http://lightadmin.org");
        servletContext.setInitParameter(LIGHT_ADMINISTRATION_BACK_TO_SITE_URL, "http://lightadmin.org");
        servletContext.setInitParameter(LIGHT_ADMINISTRATION_BASE_PACKAGE, "ru.kpfu.itis.administration");

        super.onStartup(servletContext);
    }
}