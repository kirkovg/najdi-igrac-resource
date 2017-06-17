package com.najdiigrac.mk.model.jpa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.najdiigrac.mk.model.enums.UserType;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.search.annotations.*;

import javax.persistence.*;
import java.util.List;

/**
 * Created by bogdan on 19.4.2017.
 */
@Entity
@Table(name = "users")
@Indexed
public class User extends BaseEntity {

    @Field(index = org.hibernate.search.annotations.Index.YES, store = Store.NO, analyze = Analyze.YES)
    @Analyzer(definition = "najdiIgracAnalyser")
    @Boost(1.5f)
    public String userName;

    @Enumerated(EnumType.STRING)
    public UserType userType;

    /*Za da mozis da postnis user i da go zacuvas passwordot
    * a ko ke zemas od baza da ne go pokazuvas
    * */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String password;

    public String email;

    public String telephone;

    public String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
            value = {"password",
                    "email",
                    "telephone",
                    "followers",
                    "userType",
                    "following",
                    "description"},
            allowSetters = true)
    // allowSetters za da mojs da deserializiras od fronteind
    /*@LazyCollection(LazyCollectionOption.FALSE)*/
    public List<User> followers;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
                  value = {"password",
                    "email",
                    "telephone",
                    "followers",
                    "userType",
                    "following",
                    "description"},
            allowSetters = true)
    /*@LazyCollection(LazyCollectionOption.FALSE)*/
    public List<User> following;

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", userType=" + userType +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", followers=" + followers +
                '}';
    }
}
