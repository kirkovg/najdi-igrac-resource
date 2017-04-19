package com.najdiigrac.mk.model;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @ManyToOne
    public String sport;

    @ManyToOne
    public User creator;


}
