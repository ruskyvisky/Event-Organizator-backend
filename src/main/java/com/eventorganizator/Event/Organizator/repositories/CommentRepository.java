package com.eventorganizator.Event.Organizator.repositories;

import com.eventorganizator.Event.Organizator.entities.Comment;
import com.eventorganizator.Event.Organizator.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{

    List<Comment> findAllByEvent(Event event);
}
