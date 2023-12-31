package com.consultascore.serasa.config;

import com.consultascore.serasa.service.LoginService;
import jakarta.servlet.Filter;
import org.apache.catalina.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    LoginService loginService;

    @Autowired
    JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Bean
    public Filter authenticationJwtTokenFilter() {
        return new JwtRequestFilter();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(loginService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    /**
     * Metodo responsavel por liberar o login sem token e barrar as outras rotas
     * */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity, HandlerMappingIntrospector introspector) throws Exception {

        MvcRequestMatcher mvcRequestMatcherLogin = new MvcRequestMatcher(introspector, "/**");
        mvcRequestMatcherLogin.setServletPath("/api/v1/serasa/login");
        MvcRequestMatcher mvcRequestMatcherSwagger = new MvcRequestMatcher(introspector, "/**");
        mvcRequestMatcherSwagger.setServletPath("/swagger-ui/**");
        MvcRequestMatcher mvcRequestMatcherH2 = new MvcRequestMatcher(introspector, "/**");
        mvcRequestMatcherH2.setServletPath("/h2-console/**");
        MvcRequestMatcher mvcRequestMatcherScore = new MvcRequestMatcher(introspector, "/**");
        mvcRequestMatcherScore.setServletPath("/api/v1/serasa/score");

        httpSecurity.securityMatcher("/api/**").authorizeHttpRequests(rmr -> rmr
                .requestMatchers(mvcRequestMatcherScore, mvcRequestMatcherLogin, mvcRequestMatcherH2, mvcRequestMatcherSwagger).permitAll().anyRequest().authenticated()
        ).httpBasic(httpbc -> httpbc
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
        ).sessionManagement(smc -> smc
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        ).csrf(AbstractHttpConfigurer::disable);

        httpSecurity.authenticationProvider(authenticationProvider());
        httpSecurity.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();

    }

}
