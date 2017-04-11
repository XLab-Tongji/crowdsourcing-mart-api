package com.crazy.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by jieping on 2017-04-04.
 */
/*
@Configuration
@EnableWebSecurity
*/
public class WebSecurityConfig extends WebSecurityConfigurerAdapter

{
    /*
    @Autowired
    DataSource dataSource;
    */
    @Override
    protected  void configure(AuthenticationManagerBuilder auth) throws Exception
    {
/*
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("SELECT username,password FROM ACCOUNT WHERE username=?")
                .authoritiesByUsernameQuery("SELECT username,role_name FROM ACCOUNT,ROLES WHERE ACCOUNT.role_id=ROLES.role_id and username=?");
*/
        auth.inMemoryAuthentication()
                .withUser("amdin").password("admin").roles("admin");
    }
    @Override
    protected  void configure(HttpSecurity http) throws  Exception
    {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .antMatchers("/admin/**").hasRole("admin")
                .antMatchers("/user/**").hasAnyRole("admin","user")
                .anyRequest().authenticated();
        http.formLogin()
                .loginPage("/login")
                .failureUrl("/login?error")
                .permitAll() //5
                .and();


    }

}
