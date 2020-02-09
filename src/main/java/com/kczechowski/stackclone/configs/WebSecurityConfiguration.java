package com.kczechowski.stackclone.configs;

import com.kczechowski.stackclone.entities.User;
import com.kczechowski.stackclone.services.AuthDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Component
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    AuthDetailsService authDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authDetailsService).passwordEncoder(User.PASSWORD_ENCODER);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/register")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.FORBIDDEN));
    }

    // To enable CORS
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();

        List<String> origins = new ArrayList<>();
        origins.add("*");
        List<String> methods = new ArrayList<>();
        methods.add("GET");
        methods.add("POST");
        methods.add("PUT");
        methods.add("DELETE");
        List<String> headers = new ArrayList<>();
        headers.add("Authorization");
        headers.add("Cache-Control");
        headers.add("Content-Type");
        configuration.setAllowedOrigins(origins); // www - obligatory
//        configuration.setAllowedOrigins(ImmutableList.of("*"));  //set access from all domains
        configuration.setAllowedMethods(methods);
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(headers);

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
