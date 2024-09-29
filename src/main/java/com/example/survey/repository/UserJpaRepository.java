package com.example.survey.repository;

import com.example.survey.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserJpaRepository extends JpaRepository<User, UUID> {

    User findByMailAndPassword(String mail, String password);

    boolean existsByMail(String mail);

    boolean existsByMailAndIdNot(String mail, UUID id);

}
