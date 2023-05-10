package hu.capsys.szep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Hooks;

@SpringBootApplication
public class SzepApplication {

    public static void main(String[] args) {
        SpringApplication.run(SzepApplication.class, args);
        Hooks.enableAutomaticContextPropagation();
    }

}
