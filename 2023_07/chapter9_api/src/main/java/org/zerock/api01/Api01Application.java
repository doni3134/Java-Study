package org.zerock.api01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Api01Application {

    public static void main(String[] args) {
        SpringApplication.run(Api01Application.class, args);
    }

}
