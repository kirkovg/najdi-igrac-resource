package com.najdiigrac.mk.service;


import com.najdiigrac.mk.model.jpa.ParticipateRequest;

import java.util.List;

/**
 * Created by bogda on 01.6.2017.
 */
public interface ParticipateRequestService {

    ParticipateRequest participate(Long fromFriendId, Long eventId);

    List<ParticipateRequest> getRequestsForEvent(Long eventId);

    void removeRequest(Long requestId);

    List<ParticipateRequest> getRequestsSentByUser(Long userId);

    List<ParticipateRequest> getRequestsForAdminOfEvent(Long userId);

    ParticipateRequest findRequestBy(Long eventId, Long userId);

    void cancelRequest(Long userId,Long eventId);

    void removeRequests(List<ParticipateRequest> requests);

    void cascadeDelete(Long eventId);

    void acceptParticipatingRequest(Long requestId);

    void rejectParticipatingRequest(Long requestId);
}
