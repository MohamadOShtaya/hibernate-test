package org.hibernate.app;


import org.hibernate.repo.Repo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLException;


public class Main {

    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Persistence");
    private static EntityManager entityManager = entityManagerFactory.createEntityManager();
    private static Repo fileRepository;
    public static void main(String[] args) throws SQLException {

        entityManager.getTransaction().begin();
        fileRepository=new Repo(entityManager);
        fileRepository.saveSubject();
        fileRepository.getAllQuestions();
        entityManager.getTransaction().commit();
        entityManager.close();


    }
}







