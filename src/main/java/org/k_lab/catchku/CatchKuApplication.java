package org.k_lab.catchku;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CatchKuApplication {
    public static void main(String[] args) {
        SpringApplication.run(CatchKuApplication.class, args);
    }

}
