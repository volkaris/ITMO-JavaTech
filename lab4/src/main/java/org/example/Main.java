package org.example;


import Ports.Cats.ICatRepository;
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


        ICatRepository catRepo= context.getBean(ICatRepository.class);




/* IOwnerRepository ownerRepo= context.getBean(IOwnerRepository.class);

        IOwnerController ownerController = context.getBean(OwnerController.class);
        ICatController catController = context.getBean(CatController.class);

  //     var i= catController.save( new Cat(10, "first", "race one", "white", LocalDate.now(), 1));

        Cat cat1 = new Cat(1, "first", "race one", "white", LocalDate.now(), 1);
        Cat cat3 = new Cat(3, "third", "race three", "white", LocalDate.now(), 1);*//*



        // var Ilya = new Owner(1, "Ilya", LocalDate.now());

        //ownerController.save(Ilya);

    */
/*    Cat cat1 = new Cat(1, "first", "race one", "white", LocalDate.now(), 1);
        Cat cat3 = new Cat(3, "third", "race three", "white", LocalDate.now(), 1);

        catController.save(cat1);
        catController.save(new Cat(2, "second", "race two ", "white", LocalDate.now(),1));
        catController.save(cat3);
        catController.save(new Cat(4, "fourth", "race fourth", "white", LocalDate.now(), 1));

        var list=catRepo.getAllByOwnerId(1);



var firstCat=catController.getById(1);
        var secondCat=catController.getById(2);*//*



        int x=10;
//   catController.deleteById(1);
        */
        /*var Ilya = new Owner(1, "Ilya", LocalDate.now());
         *//*
         */
/* var Kostya = new Owner(2, "Kostya", LocalDate.now());
        var Nahid = new Owner(3, "Nahid", LocalDate.now());
        var Kolya = new Owner(new Owner(4, "Kolya", LocalDate.now()));*//*
         */
        /*

         *//*
         */
/*var fiftenthCat = new Cat(15, "fifteen", "race sevent", "white", LocalDate.now(), Kolya);
        var fourthteenCat = new Cat(14, "fourteen", "race six ", "white", LocalDate.now(), Kolya);*//*
         */
/*


        ownerController.save(Ilya);
      *//*
         */
/*  ownerController.save(Kostya);
        ownerController.save(Nahid);
        ownerController.save(Kolya);*//*
         */
/*

        Cat cat1 = new Cat(1, "first", "race one", "white", LocalDate.now(), Ilya.getId());
        Cat cat3 = new Cat(3, "third", "race three", "white", LocalDate.now(), Ilya.getId());

        catController.save(cat1);
        catController.save(new Cat(2, "second", "race two ", "white", LocalDate.now(), Ilya.getId()));
        catController.save(cat3);
        catController.save(new Cat(4, "fourth", "race fourth", "white", LocalDate.now(), Ilya.getId()));*//*


         */
/* catController.save(new Cat(5, "fifth", "race five", "white", LocalDate.now(), Kostya));
        catController.save(new Cat(6, "six", "race six ", "white", LocalDate.now(), Kostya));
        catController.save(new Cat(7, "seventh", "race sevent", "white", LocalDate.now(), Kostya));
        catController.save(new Cat(8, "eight", "race eight", "white", LocalDate.now(), Kostya));

        catController.save(new Cat(9, "ninth", "race one", "white", LocalDate.now(), Nahid));
        catController.save(new Cat(10, "tenth", "race two ", "white", LocalDate.now(), Nahid));
        catController.save(new Cat(11, "eleventh", "race three", "white", LocalDate.now(), Nahid));
        catController.save(new Cat(12, "twelfth", "race fourth", "white", LocalDate.now(), Nahid));

        catController.save(new Cat(13, "thirteen", "race five", "white", LocalDate.now(), Kolya));
        catController.save(fourthteenCat);
        catController.save(fiftenthCat);
        catController.save(new Cat(16, "sixteen", "race eight", "white", LocalDate.now(), Kolya));*//*



        //owner tests (save already used)

        // delete of owners tests. all cats associated with this owner will be deleted by cascade

       */
/* ownerController.deleteById(1);

        ownerController.deleteByEntity(Kostya);*//*


        // ownerController.deleteAll();

        // Get test

       */
/* var cats = ownerController.getAllCatsById(3);
        var cats2 = ownerController.getAllCatsByEntity(Nahid);*//*


         */
/* var copy = ownerController.getById(3);
        var allOwners = ownerController.getAll();*//*


        //Update test

     */
/*   Nahid.setName("New name");
        ownerController.update(Nahid);*//*



        //Cats test

        //delete tests


      */
/*  catController.deleteById(9);
        catController.deleteById(11);
        catController.deleteById(16);
        catController.deleteByEntity(fiftenthCat);

        // update test

        fourthteenCat.setName("Updated name");

        catController.update(fourthteenCat);*//*


        // Get tests

        */
/*var cat14 = catController.getById(14);
        var allCats = catController.getAll();


        //Add friends test

       catController.addFriend(10,12);
        catController.addFriend(10,13);
        catController.addFriend(10,14);


        var catWithFriends10 = catController.getById(10);
        var catWithFriends12 = catController.getById(12);
        var catWithFriends13 = catController.getById(13);
        var catWithFriends14 = catController.getById(14);*/

    }
}
