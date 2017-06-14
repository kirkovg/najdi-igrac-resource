package com.najdiigrac.mk.service;

import com.najdiigrac.mk.model.jpa.InviteRequest;

import java.util.List;

/**
 * Created by bogda on 19.5.2017.
 */
public interface InviteRequestService {

    InviteRequest inviteFriend(Long fromFriendId, Long toFriendId, Long eventId);

    List<InviteRequest> getRequestsForUser(Long userId);

    void removeRequest(Long requestId);
}
