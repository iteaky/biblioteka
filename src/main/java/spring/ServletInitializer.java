package spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = MessageSourceAutoConfiguration.class)
@ComponentScan({"controllers", "spring"})
public class ServletInitializer extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(ServletInitializer.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
        return application.sources(ServletInitializer.class);
    }
}

