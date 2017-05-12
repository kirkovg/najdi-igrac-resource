package com.najdiigrac.mk.service.impl;

import com.najdiigrac.mk.model.jpa.Event;
import com.najdiigrac.mk.persistence.impl.SearchRepository;
import com.najdiigrac.mk.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Win 8 on 12.05.2017.
 */
@Service
public class SearchServiceImpl implements SearchService{

    @Autowired
    private SearchRepository searchRepository;

    @Override
    public List<Event> searchEvent(String query) {
        return searchRepository.searchPhrase(Event.class,query, "name","description",
                "location.name","admin.userName","participants.userName");
    }
}
