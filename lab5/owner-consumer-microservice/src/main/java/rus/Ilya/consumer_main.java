package rus.Ilya;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import jakarta.persistence.*;

@SpringBootApplication(scanBasePackages = {"rus.Ilya.Repositories",
                                            "rus.Ilya.Services",
                                            "rus.Ilya.Cats",
                                            "rus.Ilya.Owners",
                                            "rus.Ilya.Config",
                                            "rus.Ilya.Consumers",
"rus.Ilya.JWT",
        "rus.Ilya.Passwords"})
@EntityScan("rus.Ilya.*")
@EnableJpaRepositories("rus.Ilya.Repositories")
public class consumer_main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");

        SpringApplication.run(consumer_main.class,args);
        System.out.println("Hello world!");
    }
}