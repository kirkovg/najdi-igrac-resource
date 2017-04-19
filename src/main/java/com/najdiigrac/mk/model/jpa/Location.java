package com.najdiigrac.mk.model.jpa;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by bogdan on 19.4.2017.
 */
@Entity
@Table(name = "locations")
public class Location extends BaseEntity {

    public String name;

    public String city;

    public String street;

    public String streetNumber;

}
