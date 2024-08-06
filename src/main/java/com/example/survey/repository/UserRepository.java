package com.example.survey.repository;

import com.example.survey.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void saveUser(User user) {
        em.persist(user);
    }

    @Transactional
    public User getUser (UUID id){
        User user = em.find(User.class , id);
        return user;
    }

    @Transactional
    public void deleteUser (User user){
        em.remove(user);
    }



}
