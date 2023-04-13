package by.prvsega.restservice.config;

import by.prvsega.restservice.security.EmployeeDetailsImpl;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

@Configuration
public class ConfigSecurityContextHolder {

    public static Integer getUserId(){
        EmployeeDetailsImpl principal = (EmployeeDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal.getId();
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.parse("1024KB"));
        factory.setMaxRequestSize(DataSize.parse("1024KB"));
        return factory.createMultipartConfig();
    }

}
