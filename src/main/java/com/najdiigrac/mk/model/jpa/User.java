package com.najdiigrac.mk.model.jpa;

import com.najdiigrac.mk.model.enums.UserType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

/**
 * Created by bogdan on 19.4.2017.
 */
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    public String userName;

    @Enumerated(EnumType.STRING)
    public UserType userType;

    public String password;

    public String email;

    public String telephone;

    @ManyToMany/*(fetch = FetchType.LAZY)*/
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<User> followers;

}
