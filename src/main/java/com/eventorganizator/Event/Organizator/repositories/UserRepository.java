package com.eventorganizator.Event.Organizator.repositories;

import com.eventorganizator.Event.Organizator.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
