package com.najdiigrac.mk.model.jpa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * Created by bogda on 19.4.2017.
 */
@Entity
@Table(name = "participate_requests")
public class ParticipateRequest extends Notification {

    @ManyToOne
    @JsonIgnoreProperties(
            value = {"password",
                    "email",
                    "telephone",
                    "followers",
                    "userType",
                    "following",
                    "description"},
            allowSetters = true)
    public User from;


    @ManyToOne
    public Event event;


    public LocalDateTime dateTime;

    @Override
    public String toString() {
        return "ParticipateRequest{" +
                "from=" + from +
                ", event=" + event +
                ", dateTime=" + dateTime +
                '}';
    }
}
