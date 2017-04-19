package com.najdiigrac.mk.model.jpa;

import com.najdiigrac.mk.model.enums.SportType;

import javax.persistence.*;
import java.io.Serializable;
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

    @ManyToMany(fetch = FetchType.EAGER)
    public List<User> admins;

    public String description;
}
