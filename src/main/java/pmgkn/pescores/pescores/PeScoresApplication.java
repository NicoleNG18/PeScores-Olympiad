package pmgkn.pescores.pescores;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PeScoresApplication {

    public static void main(String[] args) {
        SpringApplication.run(PeScoresApplication.class, args);
    }

}
