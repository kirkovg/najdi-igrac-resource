package com.najdiigrac.mk.config;

import com.najdiigrac.mk.authentication.LoginFailureHandler;
import com.najdiigrac.mk.authentication.LoginSuccessHandler;
import com.najdiigrac.mk.model.enums.UserType;
import com.najdiigrac.mk.web.filters.CORSFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(logoutSuccessHandler())
                .deleteCookies("JSESSIONID")
                .permitAll();

        http
                .addFilterBefore(new CORSFilter(), ChannelProcessingFilter.class)
                .formLogin()
                    .successHandler(localSuccessHandler())
                    .failureHandler(loginFailureHandler())
                .permitAll();

        http.authorizeRequests()
                .antMatchers(
                        "/api/users/**",
                        "/api/locations/**"
                )
                .authenticated();

        http.authorizeRequests()
                .antMatchers("/api/admin/**")
                .hasRole("ADMIN");

        http.authorizeRequests()
                .antMatchers("/**")
                .permitAll();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

    @Bean
    public AuthenticationFailureHandler loginFailureHandler() {
        return new LoginFailureHandler();
    }

    @Bean
    public AuthenticationSuccessHandler localSuccessHandler() {
        return new LoginSuccessHandler();
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new com.najdiigrac.mk.authentication.LogoutSuccessHandler();
    }
}
