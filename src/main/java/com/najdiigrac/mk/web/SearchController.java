package com.najdiigrac.mk.web;

import com.najdiigrac.mk.model.jpa.Event;
import com.najdiigrac.mk.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Win 8 on 12.05.2017.
 */

@RestController
@RequestMapping(value = "/api/search")
public class SearchController {

    private SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService){
        this.searchService = searchService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Event> search(@RequestParam String query) {
        List<Event> events = searchService.searchEvent(query);
        return events;
    }

}
