package com.tatsiana.grocery.security;

import com.tatsiana.grocery.handler.CustomAuthenticationFailureHandler;
import com.tatsiana.grocery.service.impl.Oauth2AuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeRequests()
                .antMatchers("/adduser", "/login", "/login-error", "/login-verified", "/login-disabled", "/verify/email")
                .permitAll().anyRequest().authenticated()
                .and().formLogin().loginPage("/login").defaultSuccessUrl("/lists/", true).failureHandler(customAuthenticationFailureHandler)//defaultSuccessUrl("/list")//loginProcessingUrl("/list")//.successForwardUrl("/list")
                .and().rememberMe().key("remember-me-key").rememberMeCookieName("course-tracker-remember-me")
                .and().oauth2Login().loginPage("/login").successHandler(new Oauth2AuthenticationSuccessHandler())
                .and().logout().deleteCookies("course-tracker-remember-me");
        ;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/webjars/**", "/images/*", "/css/*", "/h2-console/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
