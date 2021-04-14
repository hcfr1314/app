package com.hhgs.app.config;

import com.hhgs.app.common.CustomCode;
import com.hhgs.app.filter.PermissionFilter;
import com.hhgs.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;
import java.io.Writer;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Resource
    private UserService userService;

    @Resource
    private PermissionFilter permissionFilter;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/swagger-ui.html","/login.html","/webjars/**","/configuration/**","/swagger-resources/**","/v2/**","/websocket");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterAfter(permissionFilter,UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/static/login.html")
                .loginProcessingUrl("/login").usernameParameter("username").passwordParameter("password")
                .successHandler(((request, response, authentication) -> {
                    response.setContentType("application/json;charset=UTF-8");
                    Writer out = response.getWriter();
                    out.write("login success");
                    response.setStatus(200);
                    out.close();
                }))
                .failureHandler(((request, response, e) -> {
                    response.setContentType("application/json;charset=UTF-8");
                    Writer out = response.getWriter();
                    String errMsg = "";
                    int code=0;
                    if (e instanceof BadCredentialsException) {
                        errMsg = CustomCode.BAD_CRENTIAL.getDesc();
                        code=CustomCode.BAD_CRENTIAL.getCode();
                    } else if (e instanceof LockedException) {
                        errMsg = CustomCode.LOCKED.getDesc();
                        code=CustomCode.LOCKED.getCode();
                    } else if (e instanceof CredentialsExpiredException) {
                        errMsg = CustomCode.CREDENTIALS_EXPIRED.getDesc();
                        code=CustomCode.CREDENTIALS_EXPIRED.getCode();
                    } else if (e instanceof AccountExpiredException) {
                        errMsg = CustomCode.ACCOUNT_EXPIRED.getDesc();
                        code=CustomCode.ACCOUNT_EXPIRED.getCode();
                    } else if (e instanceof DisabledException) {
                        errMsg =CustomCode.DISABLED.getDesc();
                        code=CustomCode.DISABLED.getCode();
                    } else {
                        errMsg = e.getMessage();
                    }
                    out.write(errMsg);
                    response.setStatus(code);
                    out.close();
                })).permitAll()
                .and()
                .logout().logoutUrl("/logout")
                .logoutSuccessHandler(((request, response, authentication) -> {
                    response.setContentType("application/json;charset=UTF-8");
                    Writer out = response.getWriter();
                    out.write("login out success");
                    response.setStatus(200);
                    out.close();
                })).
                permitAll()
                .and()
                .csrf().disable().exceptionHandling().authenticationEntryPoint(((request, response, e) -> {
                    System.out.println(e.getMessage());
                    response.setContentType("application/json;charset=UTF-8");
                    Writer out = response.getWriter();
                    out.write("no login,please login..");
                    response.setStatus(999);
                    out.close();
                }));


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(userService);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RoleHierarchy getRoleHierarchy(){
        RoleHierarchyImpl roleHierarchy=new RoleHierarchyImpl();
        roleHierarchy.setHierarchy("ROLE_admin > ROLE_user");
        return roleHierarchy;
    }

}
