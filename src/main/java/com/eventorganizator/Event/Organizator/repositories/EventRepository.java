package com.eventorganizator.Event.Organizator.repositories;

import com.eventorganizator.Event.Organizator.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event,Long> {


}
