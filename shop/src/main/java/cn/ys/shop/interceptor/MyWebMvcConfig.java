package cn.ys.shop.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@Configuration
public class MyWebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(
                new AdminOperatorInterceptor()
        ).addPathPatterns("/*/edit","/*/delete","/*/adminFindAll");

        registry.addInterceptor(
                new UserOperatorInterceptor()
        ).addPathPatterns("/*/addCart");
        super.addInterceptors(registry);
    }
}
