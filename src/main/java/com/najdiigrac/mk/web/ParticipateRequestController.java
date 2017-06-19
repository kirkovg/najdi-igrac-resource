package com.najdiigrac.mk.web;

import com.najdiigrac.mk.model.jpa.Event;
import com.najdiigrac.mk.model.jpa.ParticipateRequest;
import com.najdiigrac.mk.model.jpa.User;
import com.najdiigrac.mk.service.ParticipateRequestService;
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
@RequestMapping(value = "/api/participateRequests", produces = "application/json")
public class  ParticipateRequestController {

    private ParticipateRequestService participateRequestService;
    private UserService userService;

    @Autowired
    public ParticipateRequestController(ParticipateRequestService participateRequestService, UserService userService) {
        this.participateRequestService = participateRequestService;
        this.userService = userService;
    }

    @RequestMapping(value = "/forAuthenticatedUser", method = RequestMethod.GET)
    @ResponseBody
    public List<ParticipateRequest> forAuthenticatedUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentAuthenticatedUser = userService.findByUserName(userDetails.getUsername());
       return participateRequestService.getRequestsSentByUser(currentAuthenticatedUser.id);
    }

    @RequestMapping(value = "/getParticipateRequestsForMyEvents", method = RequestMethod.GET)
    public List<ParticipateRequest> getParticipateRequestsForMyEvents() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentAuthenticatedUser = userService.findByUserName(userDetails.getUsername());
        List<ParticipateRequest> participateRequests = participateRequestService
                .getRequestsForAdminOfEvent(currentAuthenticatedUser.id);

        return participateRequests;
    }

    @RequestMapping(value = "/accept/{requestId}", method = RequestMethod.POST)
    public void acceptParticipatingRequest(@PathVariable Long requestId) {
        participateRequestService.acceptParticipatingRequest(requestId);
    }

    @RequestMapping(value = "/reject/{requestId}", method = RequestMethod.POST)
    public void rejectParticipatingRequest(@PathVariable Long requestId) {
        participateRequestService.rejectParticipatingRequest(requestId);
    }
}