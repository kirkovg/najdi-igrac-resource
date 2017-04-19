package com.najdiigrac.mk.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by bogdan on 19.4.2017.
 */
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    public String userName;

    public String password;

    public String email;

    @ManyToMany(fetch = FetchType.LAZY)
    public List<User> friend;

    @ManyToMany(fetch = FetchType.LAZY)
    public List<Event> event;



}
