package com.eventorganizator.Event.Organizator.repositories;

import com.eventorganizator.Event.Organizator.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUsername(String userName);
    boolean existsByUsername(String username);
}
