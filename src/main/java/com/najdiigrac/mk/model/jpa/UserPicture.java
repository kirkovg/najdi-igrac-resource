package com.najdiigrac.mk.model.jpa;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.sql.Blob;

/**
 * Created by bogda on 08.5.2017.
 */
@Entity
@Table(name = "user_pictures")
public class UserPicture extends BaseEntity{
    @OneToOne
    public User user;

    public Blob data;

    public String fileName;

    public String contentType;

    public Long size;
}
