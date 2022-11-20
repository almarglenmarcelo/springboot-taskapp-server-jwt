package springboot.angular.config;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Component
public class JwtFilter {

    @Bean
    public FilterRegistrationBean<AuthFilter> setAuthFilter() {

        FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
        AuthFilter authFilter = new AuthFilter();
        registrationBean.setFilter(authFilter);

        // This block specifies which routes must be protected from non-authenticated users
        registrationBean.addUrlPatterns("/api/tasks/*");
        registrationBean.addUrlPatterns("/api/tasks");
        registrationBean.addUrlPatterns("/api/tasks/{taskId}");


        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean setCorsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowCredentials(true);
        configuration.addAllowedHeader("*");
        configuration.addAllowedOrigin("http://localhost:4200");
        configuration.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", configuration);

        FilterRegistrationBean bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(0);

        return bean;

    }





}
