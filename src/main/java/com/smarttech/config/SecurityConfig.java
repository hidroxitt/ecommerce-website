package com.smarttech.config;

import com.smarttech.constant.AppConstant;
import com.smarttech.constant.RoleConstant;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        authenticationProvider.setUserDetailsService(userDetailsService);
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, ThymeleafViewResolver viewResolver, @Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver) throws Exception {
        return http
                .csrf(x -> x.disable())
                .authorizeHttpRequests(request -> {
                    request.mvcMatchers("/auth/**").permitAll()
                            .mvcMatchers("/resources/**").permitAll()
                            .mvcMatchers("/mua-sam/dat-hang").hasAuthority(RoleConstant.USER.name())
                            .mvcMatchers("/mua-sam/**").permitAll()
                            .mvcMatchers("/lien-he", "/").permitAll()
                            .mvcMatchers("/admin/**").hasAuthority(RoleConstant.ADMIN.name())
                            .mvcMatchers(AppConstant.Endpoint.HOME).permitAll()
                            .anyRequest()
                            .authenticated();
                })
                .formLogin(form -> {
                    form.loginPage("/auth")
                        .loginProcessingUrl("/auth")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .defaultSuccessUrl(AppConstant.Endpoint.HOME, true)
                        .failureHandler((request, response, exception) -> {
                            ModelAndView modelAndView = resolver.resolveException(request, response, null, exception);
                            try {
                                View view = viewResolver.resolveViewName(modelAndView.getViewName(), LocaleContextHolder.getLocale());
                                view.render(modelAndView.getModel(), request, response);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        })
                        .permitAll();
                })
                .logout(logout -> {
                    logout
                        .logoutUrl("/auth/logout")
                        .logoutSuccessUrl("/auth")
                        .deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .permitAll();
                })
                .build();
    }
}
