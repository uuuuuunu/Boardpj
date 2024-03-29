package ptp.myboard.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private final PrincipalDetailService principalDetailService;
    private final AuthenticationFailureHandler customAuthFailureHandler;


    @Autowired
    public SecurityConfig(PrincipalDetailService principalDetailService, AuthenticationFailureHandler customAuthFailureHandler) {
        this.principalDetailService = principalDetailService;
        this.customAuthFailureHandler = customAuthFailureHandler;
    }

    @Bean
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(principalDetailService).passwordEncoder(encodePwd());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String[] staticResources  =  {
                "/css/**",
                "/image/**",
                "/fonts/**",
                "/scripts/**",
        };

        http.csrf().disable()
            .authorizeRequests()
                .antMatchers("/yw","/yw/join","/yw/login","/test","/yw/checkid").permitAll()
                .antMatchers("/static/css/**").permitAll()
                .antMatchers("https://fonts.googleapis.com").permitAll()
                .antMatchers(staticResources).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/basic/login/loginform")
                .usernameParameter("username")
                .loginPage("/yw/login")
                .loginProcessingUrl("/yw/login")
                .defaultSuccessUrl("/yw/boards")
                .failureHandler(customAuthFailureHandler)
                .permitAll();
        http.headers().contentTypeOptions().disable();
        http.logout()
                .logoutUrl("/yw/logout")
                .logoutSuccessUrl("/yw/login")
                .permitAll();
    }









}
