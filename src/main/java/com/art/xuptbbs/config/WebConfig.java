package com.art.xuptbbs.config;

import com.art.xuptbbs.converter.IntegerToBaseEnumConverterFactory;
import com.art.xuptbbs.converter.StringToBaseEnumConverterFactory;
import com.art.xuptbbs.interceptor.AuthInterceptor;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    FileConfig fileConfig;

    @Autowired
    private AuthInterceptor authInterceptor;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(new IntegerToBaseEnumConverterFactory());
        registry.addConverterFactory(new StringToBaseEnumConverterFactory());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/avatar/**").addResourceLocations("file:"+"/usr/local/runjar/images/avatar/");
        registry.addResourceHandler("/images/post/**").addResourceLocations("file:" + "/usr/local/runjar/images/post/");
        /**
         * 添加swagger静态
         */
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(authInterceptor)
//                .excludePathPatterns("/register")
//                .excludePathPatterns("/login")
//                .excludePathPatterns("/answer")
//                .excludePathPatterns("/code")
//                .excludePathPatterns("/code/{email}")
//                .excludePathPatterns("/post")
//                .excludePathPatterns("/post/detail")
//                .excludePathPatterns("project/{id}")
//                .excludePathPatterns("project")
//                .excludePathPatterns("project/type")
//                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");
//    }

}
