package com.najdiigrac.mk.service.impl;

import com.najdiigrac.mk.model.jpa.ParticipateRequest;
import com.najdiigrac.mk.persistence.EventsRepository;
import com.najdiigrac.mk.persistence.ParticipateRequestRepository;
import com.najdiigrac.mk.persistence.UsersRepository;
import com.najdiigrac.mk.service.ParticipateRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by bogda on 01.6.2017.
 */
@Service
public class ParticipateRequestImpl implements ParticipateRequestService {
    private ParticipateRequestRepository participateRequestRepository;

    private EventsRepository eventsRepository;

    private UsersRepository usersRepository;


    @Autowired
    public ParticipateRequestImpl(
            ParticipateRequestRepository participateRequestRepository,
            UsersRepository usersRepository,
            EventsRepository eventsRepository
    ){
        this.participateRequestRepository = participateRequestRepository;
        this.eventsRepository = eventsRepository;
        this.usersRepository = usersRepository;
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
}
