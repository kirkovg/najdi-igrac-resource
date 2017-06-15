package com.najdiigrac.mk.model.jpa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.najdiigrac.mk.model.enums.SportType;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bogdan on 19.4.2017.
 */
@Entity
@Table(name = "events")
@Indexed
@AnalyzerDef(name = "najdiIgracAnalyser",
        tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
        filters = {
                @TokenFilterDef(factory = LowerCaseFilterFactory.class)
        })
public class Event extends BaseEntity {

    @Field(index = Index.YES, store = Store.NO, analyze = Analyze.YES)
    @Analyzer(definition = "najdiIgracAnalyser")
    @Boost(2f)
    public String name;

    public LocalDateTime dateTime;

    @ManyToOne
    @IndexedEmbedded
    public Location location;

    @Enumerated(EnumType.STRING)
    public SportType sport;

    @ManyToOne
    @IndexedEmbedded
    @JsonIgnoreProperties({"password", "email", "telephone", "followers", "userType"})
    public User admin;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"password", "email", "telephone", "followers", "userType"})
    /*@LazyCollection(LazyCollectionOption.FALSE)*/
    @IndexedEmbedded
    public List<User> participants = new ArrayList<>();

    @Field(index = Index.YES, store = Store.NO, analyze = Analyze.YES)
    @Analyzer(definition = "najdiIgracAnalyser")
    @Boost(0.5f)
    public String description;

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", dateTime=" + dateTime +
                ", location=" + location +
                ", sport=" + sport +
                ", admin=" + admin +
                ", participants=" + participants +
                ", description='" + description + '\'' +
                '}';
    }
}
