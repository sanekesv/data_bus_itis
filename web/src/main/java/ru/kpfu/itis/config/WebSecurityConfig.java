package ru.kpfu.itis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import ru.kpfu.jbl.auth.AuthenticationFilter;
import ru.kpfu.jbl.auth.config.AuthSecurityModuleConfig;
import ru.kpfu.jbl.auth.config.EncacheTokenServiceConfig;
import ru.kpfu.jbl.auth.config.MD5ProvidersConfig;
import ru.kpfu.jbl.auth.ep.RestAuthenticationEntryPoint;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableWebMvcSecurity
@Import(value = {AuthSecurityModuleConfig.class, MD5ProvidersConfig.class,EncacheTokenServiceConfig.class})
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("md5LoginAuthProvider")
    private AuthenticationProvider authenticationProvider;

    @Autowired
    @Qualifier("tokenAuthProvider")
    private AuthenticationProvider tokenAuthenticationProvider;

    private ObjectMapper mapper;
    private static final String AUTH_URL = "/web/login";

    @Override
    public void configure(HttpSecurity http) throws Exception {
        AuthenticationEntryPoint unauthorizedEntryPoint = unauthorizedEntryPoint(mapper);
        http.
                csrf().disable().
//                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
//                and().
                authorizeRequests().
                    antMatchers("/api/*").permitAll().
                    antMatchers("/register").hasAnyRole("ADMIN").
                    antMatchers("/api/v1/admin/**").hasAnyRole("ADMIN").
                    antMatchers("/api/v1/**").hasAnyRole("STUDENT", "ADMIN", "TEACHER", "ELDER").
                    antMatchers("/download/**").hasAnyRole("ADMIN").
                    and().
                formLogin()
                    .loginPage("/login")
                    .permitAll()
                .and().
                logout()
                    .permitAll()
                .and().

                exceptionHandling()
                .authenticationEntryPoint(unauthorizedEntryPoint)
                .accessDeniedHandler((AccessDeniedHandler) unauthorizedEntryPoint);

        http.addFilterBefore(new AuthenticationFilter(authenticationManager(), mapper, AUTH_URL), BasicAuthenticationFilter.class);
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider).
                authenticationProvider(tokenAuthenticationProvider);
    }

    @Bean(name = "myAuthenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public AuthenticationEntryPoint unauthorizedEntryPoint(ObjectMapper mapper) {
        RestAuthenticationEntryPoint restAuthenticationEntryPoint = new RestAuthenticationEntryPoint();
        restAuthenticationEntryPoint.setMsgMapper(mapper);
        return restAuthenticationEntryPoint;
    }

    @Autowired
    public void setMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }
}