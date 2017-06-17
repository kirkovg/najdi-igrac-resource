package com.najdiigrac.mk.web;

import com.najdiigrac.mk.model.jpa.Event;
import com.najdiigrac.mk.model.jpa.User;
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

    @RequestMapping(value={"/searchEvents"},method = RequestMethod.GET)
    @ResponseBody
    public List<Event> searchEvents(@RequestParam String query) {
        List<Event> events = searchService.searchEvent(query);
        return events;
    }

    @RequestMapping(value={"/searchUsers"}, method = RequestMethod.GET)
    @ResponseBody
    public List<User> searchUsers(@RequestParam String query) {
        List<User> events = searchService.searchUsers(query);
        return events;
    }

}
