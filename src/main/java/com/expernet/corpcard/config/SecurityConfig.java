package com.expernet.corpcard.config;

import com.expernet.corpcard.service.LoginService;

import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.security.ConditionalOnDefaultWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Collection;

import javax.servlet.http.HttpSession;

@EnableWebSecurity
@Configuration(proxyBeanMethods = false)
@ConditionalOnDefaultWebSecurity
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class SecurityConfig implements WebMvcConfigurer {
    /**
     * Login Service
     */
    @Autowired
    private LoginService loginService;
    
    @Autowired
    private HandlerInterceptor jwtHandlerInterceptor;
    
    /**
     * BCrypt Encoder Bean Setting
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    	registry
    	.addInterceptor(jwtHandlerInterceptor)
    	.addPathPatterns("/api/**");
    }

    /**
     * Security Connfiguration Setting
     *
     * @param http : HttpSecurity
     */
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http    //global
                .csrf().disable()
                .userDetailsService(loginService)
                .authorizeHttpRequests((auth) -> {
                    try {
                        auth    //권한에 따른 접근 제한
                                .antMatchers("/login", "/fragments/**").permitAll()
                                .antMatchers("/css/**", "/js/**", "/font/**", "/icons/**").permitAll()
                                .antMatchers("/login/**").permitAll()
                                .antMatchers("/viewLogin").permitAll()
                                .anyRequest().authenticated();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                //로그인 
                .formLogin()
                .loginPage("/viewLogin")
                .usernameParameter("userId")
				.passwordParameter("userPw")
				.loginProcessingUrl("/login_proc")
				.successHandler((request, response, authentication) -> { 
					String a = response.getHeader("Authorization");
					System.out.println("Authorization : " + a);
					response.sendRedirect("/main");
					})
				.failureHandler((request, response, exception) -> {
					String errorMsg = "아이디 또는 비밀번호가 맞지 않습니다.";
					request.setAttribute("errorMsg", errorMsg);
					request.getRequestDispatcher("/viewLogin").forward(request, response); })
				.permitAll()
				.and()
				 
                //로그아웃
                .logout()
                .logoutUrl("/logout")
                .addLogoutHandler((request, response, authentication) -> {
                    HttpSession session = request.getSession();
                    session.invalidate();
                })
                .logoutSuccessHandler((request, response, authentication) -> response.sendRedirect("/viewLogin"));
        return http.build();
    }
}
