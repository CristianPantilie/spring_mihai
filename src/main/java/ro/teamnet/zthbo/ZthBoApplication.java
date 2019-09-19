package ro.teamnet.zthbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
//@EntityScan
//@EnableAutoConfiguration
//@ComponentScan
public class ZthBoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZthBoApplication.class, args);
    }

}
