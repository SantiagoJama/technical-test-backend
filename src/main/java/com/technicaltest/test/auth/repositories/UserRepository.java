package com.technicaltest.test.auth.repositories;

import com.technicaltest.test.auth.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.username=:username")
    Optional<User> findbyUsername(@Param("username") String username);
    boolean existsByUsername(String username);
}
