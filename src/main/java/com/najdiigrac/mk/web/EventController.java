package com.najdiigrac.mk.web;

import com.najdiigrac.mk.model.enums.SportType;
import com.najdiigrac.mk.model.jpa.Event;
import com.najdiigrac.mk.model.jpa.ParticipateRequest;
import com.najdiigrac.mk.model.jpa.User;
import com.najdiigrac.mk.service.EventService;
import com.najdiigrac.mk.service.LocationService;
import com.najdiigrac.mk.service.ParticipateRequestService;
import com.najdiigrac.mk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    private LocationService locationService;
    private ParticipateRequestService participateRequestService;

    @Autowired
    public EventController(EventService eventService,
                           UserService userService,
                           LocationService locationService,
                           ParticipateRequestService participateRequestService) {
        this.eventService = eventService;
        this.userService = userService;
        this.participateRequestService = participateRequestService;
        this.locationService = locationService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public List<Event> findAll() {
        return eventService.findAll();
    }

    @RequestMapping(value = "/upcoming", method = RequestMethod.GET)
    @ResponseBody
    public List<Event> findUpcomingEvents(@RequestParam int pageNr) {
        return eventService.findUpcomingEvents(pageNr);
    }

    @RequestMapping(value = "/upcoming/category", method = RequestMethod.GET)
    @ResponseBody
    public List<Event> findBySport(@RequestParam String sport, @RequestParam int pageNr) {
        SportType sportType = SportType.valueOf(sport);
        return eventService.findEventsBySport(sportType, pageNr);
    }

    @RequestMapping(value = "/upcoming/count", method = RequestMethod.GET)
    public Long countEvents() {
        return eventService.count();
    }

    @RequestMapping(value = "/upcoming/category/count", method = RequestMethod.GET)
    public Long countEventsBySport(@RequestParam String sport) {
        SportType sportType = SportType.valueOf(sport);
        return eventService.countBySport(sportType);
    }

    @RequestMapping(value = "/participatingRequests",method = RequestMethod.GET)
    public List<ParticipateRequest> findSentParticipatingRequests(@RequestParam String userName){
        User user = userService.findByUserName(userName);
        return participateRequestService.getRequestsSentByUser(user.id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public Event save(@RequestBody Event event) {

        event.location = locationService.findLocationByName(event.location.name);
        event.admin = userService.findByUserName(event.admin.userName);
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

    @RequestMapping(value = "/participate/{eventId}", method = RequestMethod.POST)
    @ResponseBody
    public ParticipateRequest sendParticipateRequest(@PathVariable Long eventId) {
        //get the current authenticated user
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentAuthenticatedUser = userService.findByUserName(userDetails.getUsername());
        return participateRequestService.participate(currentAuthenticatedUser.id, eventId);
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

    @RequestMapping(value = "/{eventId}", method = RequestMethod.GET)
    public Event findById(@PathVariable Long eventId) {
        return eventService.findById(eventId);
    }
}