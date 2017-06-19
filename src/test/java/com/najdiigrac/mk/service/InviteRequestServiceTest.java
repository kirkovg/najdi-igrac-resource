/*
package com.najdiigrac.mk.service;

import com.najdiigrac.mk.model.enums.SportType;
import com.najdiigrac.mk.model.jpa.Event;
import com.najdiigrac.mk.model.jpa.InviteRequest;
import com.najdiigrac.mk.model.jpa.Location;
import com.najdiigrac.mk.model.jpa.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

*/
/**
 * Created by bogda on 19.5.2017.
 *//*

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class InviteRequestServiceTest {

    @Autowired
    InviteRequestService inviteRequestService;

    @Autowired
    UserService userService;

    @Autowired
    EventService eventService;

    @Autowired
    LocationService locationService;

    Location location;

    Event event;

    User user1,user2,user3;

    @Test
    public void publishEventTest(){
        user1 = userService.createAdminUser("Bogdan","123","mail@mail.com","0707232");
        user2 = userService.createAdminUser("Bogdan2","123","mail1@mail.com","02452232");
        location = locationService.createLocation("Shkolski","Skopje","Orce Nikolov","19");
        Assert.assertNotNull(location);
        String str = "1986-04-08 12:30";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
        event = eventService.createEvent(1l,"Fudbal u shkolski","Blabla", SportType.FOOTBALL,location.id,dateTime);

        inviteRequestService.inviteFriend(user1.id,user2.id,event.id);

        user3 =userService.createAdminUser("Bogdan3","123","mail1@mail.com","02452232");

        inviteRequestService.inviteFriend(user3.id,user2.id,event.id);

        List<InviteRequest> requestsForUser = inviteRequestService.getRequestsForUser(user2.id);
        Assert.assertEquals(requestsForUser.size(),2);

    }

}
*/
