package com.renaldo.config;

import com.renaldo.common.BaseContextUtils;
import com.renaldo.common.JacksonObjectMapper;
import com.renaldo.pojo.Employee;
import com.renaldo.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;
import java.util.Optional;

@Configuration
@Slf4j
@EnableJpaAuditing
@ServletComponentScan
public class ApplicationConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("static resource mapping start...");
        registry.addResourceHandler("/back/**")
                .addResourceLocations("classpath:/back/");
        registry.addResourceHandler("/front/**")
                .addResourceLocations("classpath:/front/");
    }

    /**
     * use thread local to get current username in session
     * @return
     */
    @Bean
    public AuditorAware<String> auditorAware(){
        return new AuditorAware() {
            @Override
            public Optional getCurrentAuditor() {
                log.info("Thread id: {}", Thread.currentThread().getId());

                return Optional.of(BaseContextUtils.getCurrentUsername());
            }
        };
    }

    /**
     * could be used to handle Long type id (if there are more than 17 digits), so as to solve loss of precision
     * @param converters
     */
    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        //Create a message converter object
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        //Set the object converter, the bottom layer uses Jackson to convert Java objects to JSON
        converter.setObjectMapper(new JacksonObjectMapper());
        //Append the above message converter object to the converters collection of the MVC framework
        converters.add(0, converter);
    }
}
