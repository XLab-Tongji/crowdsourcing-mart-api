package com.crazy.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**configure which api need to be authenticated
 * Created by SHIKUN on 2016/9/30.
 */


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception{

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/test").permitAll()
                .antMatchers("/api/**").permitAll()
                .anyRequest()
                .authenticated();




        /*
        http.
                 csrf().disable()
                .authorizeRequests()
//                .antMatchers(HttpMethod.POST, "/api/**").authenticated()
//                .antMatchers(HttpMethod.PUT, "/api/**").authenticated()
//                .antMatchers(HttpMethod.DELETE, "/api/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .httpBasic().and();
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
*/
    }

    @Override
    protected  void configure(AuthenticationManagerBuilder auth) throws Exception
    {
/*
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("SELECT username,password FROM ACCOUNT WHERE username=?")
                .authoritiesByUsernameQuery("SELECT username,role_name FROM ACCOUNT,ROLES WHERE ACCOUNT.role_id=ROLES.role_id and username=?");
*/
/*
        auth.inMemoryAuthentication()
                .withUser("amdin").password("admin").roles("admin");
*/
    }

}
