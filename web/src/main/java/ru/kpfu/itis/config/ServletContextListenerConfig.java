package ru.kpfu.itis.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import static org.lightadmin.core.util.LightAdminConfigurationUtils.*;



/**
 * Created by ermolaev.a on 05.08.2015.
 */
@Configuration
public class ServletContextListenerConfig implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        servletContext.setInitParameter(LIGHT_ADMINISTRATION_BASE_URL, "/admin");
        servletContext.setInitParameter(LIGHT_ADMINISTRATION_BACK_TO_SITE_URL, "http://lightadmin.org");
        servletContext.setInitParameter(LIGHT_ADMINISTRATION_BASE_PACKAGE, "ru.kpfu.itis.administration");


    }
}
