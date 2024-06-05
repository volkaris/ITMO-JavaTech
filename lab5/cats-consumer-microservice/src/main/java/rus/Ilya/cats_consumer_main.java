package rus.Ilya;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"rus.Ilya.Repositories",
        "rus.Ilya.Services",
        "rus.Ilya.Cats",
        "rus.Ilya.Owners",
        "rus.Ilya.Config",
        "rus.Ilya.Consumers"})
@EntityScan("rus.Ilya.*")
@EnableJpaRepositories("rus.Ilya.Repositories")
public class cats_consumer_main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");

        SpringApplication.run(cats_consumer_main.class);
    }
}