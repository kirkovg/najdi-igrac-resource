package com.najdiigrac.mk.service.impl;

import com.najdiigrac.mk.model.jpa.Event;
import com.najdiigrac.mk.model.jpa.ParticipateRequest;
import com.najdiigrac.mk.model.jpa.User;
import com.najdiigrac.mk.persistence.EventsRepository;
import com.najdiigrac.mk.persistence.ParticipateRequestRepository;
import com.najdiigrac.mk.persistence.UsersRepository;
import com.najdiigrac.mk.service.EventService;
import com.najdiigrac.mk.service.ParticipateRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bogda on 01.6.2017.
 */
@Service
public class ParticipateRequestImpl implements ParticipateRequestService {
    private ParticipateRequestRepository participateRequestRepository;

    private EventsRepository eventsRepository;

    private UsersRepository usersRepository;

    private EventService eventService;

    @Autowired
    public ParticipateRequestImpl(
            ParticipateRequestRepository participateRequestRepository,
            UsersRepository usersRepository,
            EventsRepository eventsRepository,
            EventService eventService
    ){
        this.participateRequestRepository = participateRequestRepository;
        this.eventsRepository = eventsRepository;
        this.usersRepository = usersRepository;
        this.eventService = eventService;
    }


    @Override
    public ParticipateRequest participate(Long fromFriendId, Long eventId) {
        ParticipateRequest participateRequest = new ParticipateRequest();
        participateRequest.from = usersRepository.findOne(fromFriendId);
        participateRequest.event = eventsRepository.findOne(eventId);
        participateRequest.isAccepted = false;
        participateRequest.seen = false;
        participateRequest.message = "I want to join this event";
        return participateRequestRepository.save(participateRequest);
    }

    @Override
    public List<ParticipateRequest> getRequestsForEvent(Long eventId) {

        return participateRequestRepository.findByEventId(eventId);
    }

    @Override
    public void removeRequest(Long requestId) {
        participateRequestRepository.delete(requestId);
    }

    @Override
    public List<ParticipateRequest> getRequestsSentByUser(Long userId) {
        return participateRequestRepository.findByFromId(userId);
    }

    @Override
    public List<ParticipateRequest> getRequestsForAdminOfEvent(Long userId) {
        List<ParticipateRequest> requests = participateRequestRepository
                .findByEventAdminIdOrderByDateTimeDesc(userId);
        List<ParticipateRequest> requestsToReturn = new ArrayList<>();
        for (ParticipateRequest x : requests) {
            if (x.seen != null && x.isAccepted != null) {
                if (!x.seen && !x.isAccepted) {
                    requestsToReturn.add(x);
                }
            }
        }

        return requestsToReturn;
    }

    @Override
    public ParticipateRequest findRequestBy(Long eventId, Long userId) {
        return participateRequestRepository.findByEventIdAndFromId(eventId,userId).get(0);
    }

    @Override
    public void cancelRequest(Long userId, Long eventId) {
        ParticipateRequest request = findRequestBy(eventId,userId);
        participateRequestRepository.delete(request.id);
    }

    @Override
    public void removeRequests(List<ParticipateRequest> requests) {
        participateRequestRepository.delete(requests);
    }

    @Override
    public void cascadeDelete(Long eventId) {
        List<ParticipateRequest> requests = participateRequestRepository.findByEventId(eventId);
        removeRequests(requests);
    }

    @Override
    public void acceptParticipatingRequest(Long requestId) {
        ParticipateRequest participateRequest = participateRequestRepository.findOne(requestId);
        participateRequest.isAccepted = true;
        participateRequest.seen = true;
        /*Event event = eventsRepository.findOne(participateRequest.event.id);
        User user = usersRepository.findOne(participateRequest.from.id);*/

        eventService.addParticipant(participateRequest.event.id,participateRequest.from.id);
        participateRequestRepository.save(participateRequest);
    }

    @Override
    public void rejectParticipatingRequest(Long requestId) {
        ParticipateRequest participateRequest = participateRequestRepository.findOne(requestId);
        participateRequest.isAccepted = false;
        participateRequest.seen = true;
        participateRequestRepository.save(participateRequest);
    }


}
