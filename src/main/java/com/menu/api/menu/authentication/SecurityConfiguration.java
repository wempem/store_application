package com.menu.api.menu.authentication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests(authorizeRequests ->
        {
            try {
                authorizeRequests
                        .antMatchers("/login","/h2-console/**").permitAll()
                        .antMatchers(HttpMethod.GET,"/api/store/menu/{id}").hasAnyRole("ADMIN", "USER")
                        .antMatchers("/api/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                        .and()
                        .formLogin()
                        .permitAll();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        httpSecurity.csrf().disable();
        httpSecurity.headers()
                .frameOptions()
                .sameOrigin();
        httpSecurity.httpBasic(withDefaults());

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
