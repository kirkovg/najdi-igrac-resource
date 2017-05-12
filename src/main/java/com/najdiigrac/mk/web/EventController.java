package com.najdiigrac.mk.web;

import com.najdiigrac.mk.model.enums.SportType;
import com.najdiigrac.mk.model.jpa.Event;
import com.najdiigrac.mk.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by Win 8 on 08.05.2017.
 */

@RestController
@RequestMapping(value = "/events", produces = "application/json")
public class EventController {

    private EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public List<Event> findAll() {
        return eventService.findAll();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public Event save(@RequestBody Event event) {

        return eventService.createEvent(
                event.admin.id,
                event.name,
                event.description,
                event.sport,
                event.location.id,
                event.dateTime
        );
    }


}
