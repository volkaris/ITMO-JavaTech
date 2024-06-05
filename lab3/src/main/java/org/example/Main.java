package org.example;


import Controllers.CatControllers.CatController;
import Controllers.CatControllers.ICatController;
import Controllers.OwnerControllers.IOwnerController;
import Controllers.OwnerControllers.OwnerController;
import Ports.Cats.ICatRepository;
import Ports.Owners.IOwnerRepository;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(scanBasePackages = {"Controllers.*","Service.*","Ports.*"})
@EntityScan("Service.*")
@EnableJpaRepositories("Ports.*")
public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistance");

        ApplicationContext context = SpringApplication.run(Main.class, args);
    }
}
