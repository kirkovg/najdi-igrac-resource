package com.najdiigrac.mk.service;

import com.najdiigrac.mk.model.enums.SportType;
import com.najdiigrac.mk.model.jpa.Event;

import java.time.LocalDateTime;

/**
 * Created by bogda on 19.4.2017.
 */

public interface EventService {
    Event createEvent(
            Long[] adminIds,
            String name,
            String description,
            SportType sport,
            Long locationId,
            LocalDateTime dateTime
    );

    void removeEvent(Long eventId);

    public Event updateEvent(
            Long eventId,
            Long[] newAdminIds,
            String newName,
            String newDescription,
            SportType newSport,
            Long newLocationId,
            LocalDateTime newDateTime
    );

    Event addAdmin(Long eventId, Long adminId);

    Event removeAdmin(Long eventId, Long adminId);
}
