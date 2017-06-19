package com.najdiigrac.mk.service.impl;

import ch.qos.logback.classic.Logger;
import com.najdiigrac.mk.model.enums.SportType;
import com.najdiigrac.mk.model.jpa.Event;
import com.najdiigrac.mk.model.jpa.User;
import com.najdiigrac.mk.persistence.EventsRepository;
import com.najdiigrac.mk.persistence.LocationsRepository;
import com.najdiigrac.mk.persistence.UsersRepository;
import com.najdiigrac.mk.service.EventService;
import com.najdiigrac.mk.service.InviteRequestService;
import com.najdiigrac.mk.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * Created by bogda on 19.4.2017.
 */
@Service
public class EventServiceImpl implements EventService {

    private EventsRepository eventsRepository;
    private UsersRepository usersRepository;
    private LocationsRepository locationsRepository;
    private UserService userService;
    private InviteRequestService inviteRequestService;


    static Logger logger = (Logger) LoggerFactory.getLogger(EventService.class);

    @Autowired
    private EventServiceImpl(
            EventsRepository eventsRepository,
            UsersRepository usersRepository,
            LocationsRepository locationsRepository,
            UserService userService,
            InviteRequestService inviteRequestService

    ) {
        this.eventsRepository = eventsRepository;
        this.usersRepository = usersRepository;
        this.locationsRepository = locationsRepository;
        this.userService = userService;
        this.inviteRequestService = inviteRequestService;

    }

    @Override
    public Event createEvent(Long newAdminId, String name,
                             String description, SportType sport,
                             Long locationId,
                             LocalDateTime dateTime,
                             boolean sendRequests) {

        Event event = new Event();

        event.name = name;
        event.admin = usersRepository.findOne(newAdminId);
        event.description = description;
        event.sport = sport;
        event.location = locationsRepository.findOne(locationId);
        event.dateTime = dateTime;
        Event myEvent = eventsRepository.save(event);

        if(sendRequests){
            List<User> followers = userService.findUserFollowers(event.admin.id);
            for(User user : followers){
                inviteRequestService.inviteFriend(event.admin.id,user.id,event.id);
            }
        }
        return myEvent;
    }

    @Override
    public void removeEvent(Long eventId) {
        eventsRepository.delete(eventId);
    }

    @Override
    public Event updateEvent(Long eventId, String newName, String newDescription, SportType newSport, Long newLocationId, LocalDateTime newDateTime) {
        Event event = eventsRepository.findOne(eventId);
        event.name = newName;
        event.description = newDescription;
        event.sport = newSport;
        event.location = locationsRepository.findOne(newLocationId);
        return eventsRepository.save(event);
    }


    @Override
    public Event addAdmin(Long eventId, Long adminId) {
        Event event = eventsRepository.findOne(eventId);
        event.admin = usersRepository.findOne(adminId);
        return eventsRepository.save(event);
    }

    @Override
    public Event removeAdmin(Long eventId, Long adminId) {
        Event event = eventsRepository.findOne(eventId);
        event.admin = null;
        return eventsRepository.save(event);
    }

    @Override
    public List<User> findParticipantsForEvent(Long eventId) {
        Event event = eventsRepository.findOne(eventId);
        return event.participants;
    }


    @Override
    public Event addParticipant(Long eventId, Long participantId) {
        Event event = eventsRepository.findOne(eventId);
        event.participants.add(usersRepository.findOne(participantId));
        return eventsRepository.save(event);
    }

    @Override
    public Event removeParticipant(Long eventId, Long participantId) {
        Event event = eventsRepository.findOne(eventId);
        event.participants.remove(usersRepository.findOne(participantId));
        return eventsRepository.save(event);
    }

    @Override
    public List<Event> findAll(){
        return (List<Event>) eventsRepository.findAll();
    }

    @Override
    public Event findById(Long eventId) {
        return eventsRepository.findOne(eventId);
    }

    @Override
    public List<Event> findUpcomingEvents(int pageNr) {
        return eventsRepository.findAllByOrderByDateTimeDesc(new PageRequest(pageNr,7)).getContent();
    }

    @Override
    public List<Event> findEventsBySport(SportType sport,int pageNr) {
        return eventsRepository.findBySportOrderByDateTimeDesc(sport,new PageRequest(pageNr,7)).getContent();
    }

    @Override
    public Long count() {
        return eventsRepository.count();
    }

    @Override
    public Long countBySport(SportType sport) {
        return eventsRepository.countBySport(sport);
    }

    @Override
    public Long countByAdminId(Long adminId) {
        return eventsRepository.countByAdminId(adminId);
    }

    @Override
    public List<Event> findByAdminId(Long adminId, int pageNr) {
        return eventsRepository.findByAdminId(adminId, new PageRequest(pageNr, 7)).getContent();
    }
}
