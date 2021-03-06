package com.project.PhoneDir.repository;

import com.project.PhoneDir.model.Phone;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class PhoneRepository {

    PhoneRepository() {
        System.out.println("*** Phone Repository ***");
    }

    @PersistenceUnit(unitName = "d4oqmli9plma42")
    private EntityManagerFactory entityManagerFactory;

    public List<Phone> getAllPosts(Integer userId) {
        // WORKING ON JPA
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<Phone> query = entityManager.createQuery("SELECT p from Phone p JOIN FETCH p.user puser WHERE puser.id = :userId", Phone.class);
        query.setParameter("userId", userId);
        List<Phone> result = query.getResultList();
        return result;
    }

    public void createPost(Phone newPost) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(newPost);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
            transaction.rollback();
        }
    }

    public void deletePost(Integer postId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Phone post = entityManager.find(Phone.class, postId);
            entityManager.remove(post);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
            transaction.rollback();
        }
    }
}
