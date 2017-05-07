package com.najdiigrac.mk.model.jpa;

import com.najdiigrac.mk.model.enums.SportType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by bogdan on 19.4.2017.
 */
@Entity
@Table(name = "events")
public class Event extends BaseEntity {

    public String name;

    public LocalDateTime dateTime;

    @ManyToOne
    public Location location;

    @Enumerated(EnumType.STRING)
    public SportType sport;

    @ManyToOne
    public User admin;

    @ManyToMany/*(fetch = FetchType.EAGER)*/
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<User> participants;

    public String description;
}
