package com.najdiigrac.mk.web;

import com.najdiigrac.mk.model.jpa.InviteRequest;
import com.najdiigrac.mk.model.jpa.User;
import com.najdiigrac.mk.service.InviteRequestService;
import com.najdiigrac.mk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Dj Ux on 18-Jun-17.
 */
@RestController
@RequestMapping(value = "/api/inviteRequests", produces = "application/json")
public class InviteRequestController {

    private InviteRequestService inviteRequestService;
    private UserService userService;

    @Autowired
    public InviteRequestController(InviteRequestService inviteRequestService, UserService userService) {
        this.inviteRequestService = inviteRequestService;
        this.userService = userService;
    }

    @RequestMapping(value = "/forAuthenticatedUser", method = RequestMethod.GET)
    @ResponseBody
    public List<InviteRequest> forAuthenticatedUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentAuthenticatedUser = userService.findByUserName(userDetails.getUsername());
        return inviteRequestService.getRequestsForUser(currentAuthenticatedUser.id);
    }

    @RequestMapping(value = "/{inviteId}/acceptInvite/{userId}", method = RequestMethod.POST)
    public void acceptInvite(@PathVariable Long inviteId, @PathVariable Long userId){
        inviteRequestService.acceptInvite(inviteId,userId);
    }

    @RequestMapping(value = "/{inviteId}/rejectInvite/{userId}", method = RequestMethod.POST)
    public void rejectInvite(@PathVariable Long inviteId, @PathVariable Long userId){
        inviteRequestService.rejectInvite(inviteId,userId);
    }

}