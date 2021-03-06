package com.najdiigrac.mk.service;

import com.najdiigrac.mk.model.enums.SportType;
import com.najdiigrac.mk.model.jpa.Event;
import com.najdiigrac.mk.model.jpa.User;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by bogda on 19.4.2017.
 */

public interface EventService {
    Event createEvent(
            Long adminId,
            String name,
            String description,
            SportType sport,
            Long locationId,
            LocalDateTime dateTime,
            boolean sendInvites
    );

    void removeEvent(Long eventId);

    Event updateEvent(
            Long eventId,
            String newName,
            String newDescription,
            SportType newSport,
            Long newLocationId,
            LocalDateTime newDateTime
    );

    Event addAdmin(Long eventId, Long adminId);

    Event removeAdmin(Long eventId, Long adminId);

    List<User> findParticipantsForEvent(Long eventId);

    Event addParticipant(Long eventId, Long participantId);

    Event removeParticipant(Long eventId, Long participantId);

    List<Event> findAll();

    List<Event> findUpcomingEvents(int pageNr);

    Event findById(Long eventId);

    List<Event> findEventsBySport(SportType sport,int pageNr);

    Long count();

    Long countBySport(SportType sport);

    Long countByAdminId(Long adminId);

    List<Event> findByAdminId(Long adminId, int pageNr);
}
