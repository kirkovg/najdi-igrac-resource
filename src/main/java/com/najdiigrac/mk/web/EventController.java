package com.najdiigrac.mk.web;

import com.najdiigrac.mk.model.jpa.Event;
import com.najdiigrac.mk.model.jpa.User;
import com.najdiigrac.mk.service.EventService;
import com.najdiigrac.mk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Win 8 on 08.05.2017.
 */

@RestController
@RequestMapping(value = "/api/events", produces = "application/json")
public class EventController {

    private EventService eventService;
    private UserService userService;

    @Autowired
    public EventController(EventService eventService, UserService userService) {
        this.eventService = eventService;
        this.userService = userService;
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

    @RequestMapping(value = "/{eventId}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long eventId) {
        eventService.removeEvent(eventId);
    }


    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public Event update(@RequestBody Event event) {
        return eventService.updateEvent(event.id,
                event.name,
                event.description,
                event.sport,
                event.location.id,
                event.dateTime
        );
    }

    @RequestMapping(value = "/addParticipant/{userId}", method = RequestMethod.POST)
    @ResponseBody
    public Event addParticipant(@RequestBody Event event, @PathVariable Long userId) {
        return eventService.addParticipant(event.id, userId);
    }

    @RequestMapping(value = "/removeParticipant/{userId}", method = RequestMethod.DELETE)
    @ResponseBody
    public Event removeParticipant(@RequestBody Event event, @PathVariable Long userId) {
        return eventService.removeParticipant(event.id, userId);
    }

    @RequestMapping(value = "/participants", method = RequestMethod.POST)
    @ResponseBody
    public List<User> findParticipants(@RequestBody Event event) {
        return eventService.findParticipantsForEvent(event.id);
    }
}