package com.najdiigrac.mk.service.impl;

import ch.qos.logback.classic.Logger;
import com.najdiigrac.mk.model.enums.SportType;
import com.najdiigrac.mk.model.jpa.Event;
import com.najdiigrac.mk.model.jpa.User;
import com.najdiigrac.mk.persistence.EventsRepository;
import com.najdiigrac.mk.persistence.LocationsRepository;
import com.najdiigrac.mk.persistence.UsersRepository;
import com.najdiigrac.mk.service.EventService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    static Logger logger = (Logger) LoggerFactory.getLogger(EventService.class);

    @Autowired
    private EventServiceImpl(EventsRepository eventsRepository, UsersRepository usersRepository, LocationsRepository locationsRepository){
        this.eventsRepository = eventsRepository;
        this.usersRepository = usersRepository;
        this.locationsRepository = locationsRepository;

    }

    @Override
    public Event createEvent(Long[] adminIds, String name, String description, SportType sport, Long locationId, LocalDateTime dateTime) {

        Event event = new Event();

        event.name = name;
        event.admins = (List<User>) usersRepository.findAll(Arrays.asList(adminIds));
        event.description = description;
        event.sport = sport;
        event.location = locationsRepository.findOne(locationId);
        event.dateTime = dateTime;
        logger.debug(dateTime.toString());

        return eventsRepository.save(event);
    }

    @Override
    public void removeEvent(Long eventId) {
        eventsRepository.delete(eventId);
    }

    @Override
    public Event updateEventName(Long eventId, String newName) {
        Event event = eventsRepository.findOne(eventId);
        event.name = newName;
        return eventsRepository.save(event);
    }

    @Override
    public Event updateEventDescription(Long eventId, String newDescription) {
        return null;
    }

    @Override
    public Event updateEventLocation(Long eventId, Long newlocationId) {
        return null;
    }

    @Override
    public Event updateEventTime(Long eventId, LocalDateTime newDateTime) {
        return null;
    }

    @Override
    public Event addAdmin(Long eventId, Long adminId) {
        return null;
    }

    @Override
    public Event removeAdmin(Long eventId, Long adminId) {
        return null;
    }
}
