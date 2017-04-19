package com.najdiigrac.mk.service;

import com.najdiigrac.mk.model.enums.SportType;
import com.najdiigrac.mk.model.jpa.Event;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LongSummaryStatistics;

/**
 * Created by bogda on 19.4.2017.
 */

public interface EventServiceHelper {
    Event createEvent(Long[] adminIds, String name, String description, SportType sport, Long locationId, LocalDateTime dateTime);
    void removeEvent(Long eventId);

    Event updateEventName(Long eventId,String newName);
    Event updateEventDescription(Long eventId, String newDescription);
    Event updateEventLocation(Long eventId,Long newlocationId);
    Event updateEventTime(Long eventId,LocalDateTime newDateTime);

    Event addAdmin(Long eventId,Long adminId);
    Event removeAdmin(Long eventId,Long adminId);
}
