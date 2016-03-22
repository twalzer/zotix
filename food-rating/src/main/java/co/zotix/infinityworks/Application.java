package co.zotix.infinityworks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.autoconfigure.jackson.*;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration(exclude = { JacksonAutoConfiguration.class })
@SpringBootApplication
@ComponentScan
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
