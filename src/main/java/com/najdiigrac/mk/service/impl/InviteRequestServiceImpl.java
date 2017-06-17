package com.najdiigrac.mk.service.impl;

/*import com.najdiigrac.mk.events.InviteRequestEvent;*/
import com.najdiigrac.mk.model.jpa.InviteRequest;
import com.najdiigrac.mk.persistence.EventsRepository;
import com.najdiigrac.mk.persistence.InviteRequestRepository;
import com.najdiigrac.mk.persistence.UsersRepository;
import com.najdiigrac.mk.service.InviteRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by bogda on 19.5.2017.
 */
@Service
public class InviteRequestServiceImpl implements InviteRequestService {

    private InviteRequestRepository inviteRequestRepository;

    private EventsRepository eventsRepository;

    private UsersRepository usersRepository;

/*
    private ApplicationEventPublisher publisher;*/

    @Autowired
    public InviteRequestServiceImpl(
            InviteRequestRepository inviteRequestRepository,
            /*ApplicationEventPublisher publisher,*/
            UsersRepository usersRepository,
            EventsRepository eventsRepository){
        this.inviteRequestRepository = inviteRequestRepository;
        this.eventsRepository = eventsRepository;
        this.usersRepository = usersRepository;
        /*this.publisher = publisher;*/

    }


    @Override
    public InviteRequest inviteFriend(Long fromFriendId,Long toFriendId, Long eventId) {
        InviteRequest inviteRequest = new InviteRequest();
        inviteRequest.event = eventsRepository.findOne(eventId);
        inviteRequest.to = usersRepository.findOne(toFriendId);
        inviteRequest.from = usersRepository.findOne(fromFriendId);
        inviteRequest.seen = false;
        inviteRequest.isAccepted = false;
        inviteRequest.message = "I'm inviting you for " + inviteRequest.event.name + " event!";
        inviteRequest = inviteRequestRepository.save(inviteRequest);

        return inviteRequest;
    }

    @Override
    public List<InviteRequest> getRequestsForUser(Long userId) {
        return (List<InviteRequest>) inviteRequestRepository.findByToId(userId);
    }

    @Override
    public void removeRequest(Long requestId) {
         inviteRequestRepository.delete(requestId);
    }


}
