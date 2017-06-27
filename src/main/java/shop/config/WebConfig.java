package shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.io.File;

/**
 * Created by blackhaski on 21.06.17.
 */
@Configuration
@EnableWebMvc
@ComponentScan("shop.*")
public class WebConfig extends WebMvcConfigurerAdapter {
    @Bean
    public InternalResourceViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setContentType("text/html;charset=UTF-8");
        viewResolver.setPrefix("/pages/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**")
                .addResourceLocations("/styles/");
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:"+System.getProperty("user.home")
                        + File.separator+"Programming"+File.separator
                        + "JavaComplex" + File.separator
                        + "Shop" + File.separator + "src" + File.separator
                        + "main" + File.separator + "webapp" + File.separator
                        + "images" + File.separator);
        registry.addResourceHandler("/js/**")
                .addResourceLocations("/scripts/");
    }

}
