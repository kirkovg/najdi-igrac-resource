package com.najdiigrac.mk.model.jpa;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Created by bogda on 19.4.2017.
 */
@Entity
@Table(name = "participate_requests")
public class ParticipateRequest extends Notification {

    @ManyToOne
    public User from;


    @ManyToOne
    public Event event;

}
