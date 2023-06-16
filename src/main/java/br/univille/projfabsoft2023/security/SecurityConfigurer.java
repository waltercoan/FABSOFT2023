package br.univille.projfabsoft2023.security;

import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter{
    
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
        .authorizeRequests()
            .antMatchers("/banco_de_dados/**").permitAll()
        .and().headers().frameOptions().disable()
        .and().authorizeRequests().antMatchers("/oauth_login").permitAll()
        .anyRequest().authenticated()
        .and()
        .oauth2Login();
    }
}
