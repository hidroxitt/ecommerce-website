package com.smarttech.config;

import com.smarttech.interceptor.CommonInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import javax.annotation.PostConstruct;
import java.util.Locale;
import java.util.concurrent.Executor;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private static final Locale VN = new Locale("vi", "VN");

    private final CommonInterceptor commonInterceptor;

    @PostConstruct
    public void init() {
        LocaleContextHolder.setDefaultLocale(VN);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("classpath:/static/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.commonInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/resources/**")
                .order(0);

        registry.addInterceptor(this.localeChangeInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/resources/**")
                .order(0);
    }

    @Bean
    @Primary
    public LocaleResolver localeResolver() {
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setDefaultLocale(VN);
        cookieLocaleResolver.setCookieSecure(true);
        cookieLocaleResolver.setCookieHttpOnly(true);
        return cookieLocaleResolver;
    }

    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Bean
    public Executor notificationExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(5);
        threadPoolTaskExecutor.setMaxPoolSize(10);
        threadPoolTaskExecutor.setThreadNamePrefix("notification-");
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }
}
