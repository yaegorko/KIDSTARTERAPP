package com.javamentor.kidstarter.security.config;

import com.javamentor.kidstarter.security.handler.SecurityHandler;
import com.javamentor.kidstarter.security.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationService authenticationService;

    private final SecurityHandler securityHandler;

    private final PassEncode passwordEncoder;

    @Autowired
    public WebSecurityConfig(SecurityHandler securityHandler, AuthenticationService authenticationService, PassEncode passwordEncoder) {
        this.securityHandler = securityHandler;
        this.authenticationService = authenticationService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/scripts/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin().loginPage("/main/login").permitAll()
                .successHandler(securityHandler)
                .and()
                .logout().permitAll();
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/main/**", "/api/**").permitAll()
                .antMatchers("/main/become-mentor").hasAnyAuthority("USER")
                .anyRequest().authenticated()
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .antMatchers("/users/**").hasAnyAuthority("USER")
                .antMatchers("/mentor/**").hasAnyAuthority("MENTOR")
                .antMatchers("/organization/**").hasAuthority("OWNER")
                .antMatchers("/organization/kids/**").hasAuthority("KID");

        http
                .sessionManagement()
                .maximumSessions(10000)
                .maxSessionsPreventsLogin(false)
                .expiredUrl("/main/login?logout")
                .sessionRegistry(sessionRegistry());
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(authenticationService);
        authProvider.setPasswordEncoder(passwordEncoder.passwordEncoder());
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }


}