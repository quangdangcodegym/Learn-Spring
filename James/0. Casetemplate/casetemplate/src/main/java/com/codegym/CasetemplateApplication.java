package com.codegym;

import com.codegym.repository.PersonRepository;
import com.codegym.test.cascade.Address;
import com.codegym.test.cascade.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;


@SpringBootApplication
public class CasetemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(CasetemplateApplication.class, args);
    }

}


/**
@SpringBootApplication
public class CasetemplateApplication implements CommandLineRunner {



    @PersistenceContext
    private EntityManager entityManager;
    private Session session;
    private Transaction transaction;

    @Autowired
    private PersonRepository personRepository;
    public static void main(String[] args) {
        SpringApplication.run(CasetemplateApplication.class, args);
    }

    @Override
    public void run(String... args) {

        session = entityManager.unwrap(Session.class);
//        transaction = session.beginTransaction();

        Person person = new Person();
        Address address = new Address();
        address.setPerson(person);
        person.setAddresses(Arrays.asList(address));
        session.persist(person);
        session.flush();
        session.clear();

        personRepository.save(person);




    }
}
**/