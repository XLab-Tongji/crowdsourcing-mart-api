package com.crazy.util;

import com.crazy.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**configure which api need to be authenticated
 * Created by SHIKUN on 2016/9/30.
 */


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    UserDetailsService userService()
    {
        return new UserService();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationTokenFilter();
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception{
        /*
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/successful/a.html",true)
                .failureUrl("/login?error")
                .permitAll()
                .and()
                .logout().permitAll();
          */
        http
                // 由于使用的是JWT，我们这里不需要csrf
                .csrf().disable()

                // 基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

                .authorizeRequests()
                //.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                // 对于获取token的rest api要允许匿名访问
                .antMatchers("/auth/**").permitAll()

                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated();

        // 禁用缓存
        http.headers().cacheControl();
        http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);


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

        auth.userDetailsService(userService())
                .passwordEncoder(passwordEncoder());
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
