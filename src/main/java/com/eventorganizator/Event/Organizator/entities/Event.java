package com.eventorganizator.Event.Organizator.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private Date date;
    private String location;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "creator_id",nullable = false)
    @JsonIgnore
    private User creator;
    private boolean isPublic;
    @OneToMany(fetch = FetchType.LAZY)
    private List<User> participants;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;


}
