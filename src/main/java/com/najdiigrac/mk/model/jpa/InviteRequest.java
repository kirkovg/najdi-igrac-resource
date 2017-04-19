package com.najdiigrac.mk.model.jpa;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by bogda on 19.4.2017.
 */
@Entity
@Table(name = "invite_requests")
public class InviteRequest extends Notification {

    @ManyToOne
    public User from;

    @ManyToOne
    public User to;

    @ManyToOne
    public Event event;

}
