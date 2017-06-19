package com.najdiigrac.mk.service.impl;


import com.najdiigrac.mk.model.jpa.Event;
import com.najdiigrac.mk.model.jpa.InviteRequest;
import com.najdiigrac.mk.model.jpa.User;
import com.najdiigrac.mk.persistence.EventsRepository;
import com.najdiigrac.mk.persistence.InviteRequestRepository;
import com.najdiigrac.mk.persistence.UsersRepository;
import com.najdiigrac.mk.service.InviteRequestService;
import org.springframework.beans.factory.annotation.Autowired;
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


    @Autowired
    public InviteRequestServiceImpl(
            InviteRequestRepository inviteRequestRepository,
            UsersRepository usersRepository,
            EventsRepository eventsRepository) {
        this.inviteRequestRepository = inviteRequestRepository;
        this.eventsRepository = eventsRepository;
        this.usersRepository = usersRepository;

    }


    @Override
    public InviteRequest inviteFriend(Long fromFriendId, Long toFriendId, Long eventId) {
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
        return (List<InviteRequest>) inviteRequestRepository.findByToIdOrderByDateTimeDesc(userId);
    }

    @Override
    public void removeRequest(Long requestId) {
        inviteRequestRepository.delete(requestId);
    }

    @Override
    public void acceptInvite(Long inviteId, Long userId) {
        InviteRequest inviteRequest = inviteRequestRepository.findOne(inviteId);
        Event event = eventsRepository.findOne(inviteRequest.event.id);
        User user = usersRepository.findOne(userId);

        event.participants.add(user);
        inviteRequestRepository.delete(inviteRequest);
        eventsRepository.save(event);
    }

    @Override
    public void rejectInvite(Long inviteId, Long userId) {
        InviteRequest inviteRequest = inviteRequestRepository.findByIdAndToId(inviteId,userId);
        inviteRequestRepository.delete(inviteRequest);
    }

    @Override
    public void cascadeDelete(Long eventId) {
        List<InviteRequest> inviteRequests = inviteRequestRepository.findByEventId(eventId);
        inviteRequestRepository.delete(inviteRequests);
    }

}
