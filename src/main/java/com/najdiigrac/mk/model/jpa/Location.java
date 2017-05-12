package com.najdiigrac.mk.model.jpa;

import org.hibernate.search.annotations.*;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by bogdan on 19.4.2017.
 */
@Entity
@Table(name = "locations")
public class Location extends BaseEntity {

    @Field(index = Index.YES, store = Store.NO, analyze = Analyze.YES)
    @Analyzer(definition = "najdiIgracAnalyser")
    @Boost(1f)
    public String name;

    public String city;

    public String street;

    public String streetNumber;

}
