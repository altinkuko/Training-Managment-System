package com.sda.trainingmanagmentsystem.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class AppSecurity extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            HttpSecurity disable = http.authorizeRequests()
                    .anyRequest().authenticated()
                    .and().httpBasic()
                    .and().formLogin().and().logout()
                    .and().headers().frameOptions().disable()
                    .and().csrf().disable();
        }
        @Bean
        public PasswordEncoder generateEncoder() {
            return new BCryptPasswordEncoder();
        }
    }


