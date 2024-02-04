package com.spring.security_practice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration// Configuration is used to define the configuration class
@EnableWebSecurity // EnableWebSecurity is used to enable web security in the application
@EnableGlobalMethodSecurity(prePostEnabled = true)// EnableGlobalMethodSecurity is used to enable method level security based on annotations
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    // WebSecurityConfigurerAdapter is used to customize the security configuration
    // It provides a default configuration for the security
    // We can override the default configuration by extending this class
    // It provides a method to configure the HttpSecurity
    // It provides a method to configure the AuthenticationManager
    // It provides a method to configure the UserDetailsService
    // It provides a method to configure the PasswordEncoder
    // It provides a method to configure the AuthenticationProvider
    // It provides a method to configure the AuthenticationManagerBuilder
    // It provides a method to configure the AuthenticationEntryPoint
    // It provides a method to configure the AccessDeniedHandler
    // It provides a method to configure the SecurityContext
    // It provides a method to configure the SessionManagement
    // It provides a method to configure the RememberMe
    // It provides a method to configure the Logout
    // It provides a method to configure the RequestCache
    // It provides a method to configure the SecurityFilterChain
    // It provides a method to configure the Csrf
    // It provides a method to configure the Headers
    // It provides a method to configure the PortMapper
    // It provides a method to configure the Cors
    // It provides a method to configure the X509
    // It provides a method to configure the Ssl
    // It provides a method to configure the Jee
    // It provides a method to configure the OAuth2
    // It provides a method to configure the OpenID
    // It provides a method to configure the SAML

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                csrf().disable().
                authorizeRequests().
                antMatchers("/"
                        , "/students/greet"
                        ,"index.html"
                        ,"/css/*"
                        ,"/js/*")
                .permitAll().
                anyRequest().authenticated().
                and().
                httpBasic();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
}
