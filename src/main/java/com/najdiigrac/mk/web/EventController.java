package com.najdiigrac.mk.web;

import com.najdiigrac.mk.model.jpa.Event;
import com.najdiigrac.mk.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Win 8 on 08.05.2017.
 */

@RestController
@RequestMapping(value = "/event",  produces = "application/json")
public class EventController {

    private EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @RequestMapping(value = "{/adminId}", method = RequestMethod.POST)
    @ResponseBody
    public Event save(@RequestBody Event event, @PathVariable Long adminId) {
        return eventService.createEvent(
                adminId,
                event.name,
                event.description,
                event.sport,
                event.location.id,
                event.dateTime
        );
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    //@ResponseBody
    public List<Event> findAll() {
        List<Event> events = eventService.findAll();
        return events;
    }
}
