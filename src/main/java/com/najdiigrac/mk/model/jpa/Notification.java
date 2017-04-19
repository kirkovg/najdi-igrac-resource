package com.najdiigrac.mk.model.jpa;

import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 * Created by bogda on 19.4.2017.
 */
@MappedSuperclass
public class Notification extends BaseEntity {

    public Boolean seen;

    public Boolean isAccepted;

    public String message;
}
