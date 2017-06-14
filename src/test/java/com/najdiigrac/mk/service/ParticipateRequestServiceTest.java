package com.najdiigrac.mk.service;

import com.najdiigrac.mk.model.enums.SportType;
import com.najdiigrac.mk.model.jpa.*;
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

/**
 * Created by bogda on 19.5.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ParticipateRequestServiceTest {

    @Autowired
    ParticipateRequestService participateRequestService;

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
    public void participateRequest(){
        user1 = userService.createAdminUser("Bogdan","123","mail@mail.com","0707232");
        user2 = userService.createAdminUser("Bogdan2","123","mail1@mail.com","02452232");
        location = locationService.createLocation("Shkolski","Skopje","Orce Nikolov","19");
        Assert.assertNotNull(location);
        String str = "1986-04-08 12:30";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
        event = eventService.createEvent(1l,"Fudbal u shkolski","Blabla", SportType.FOOTBALL,location.id,dateTime);

        participateRequestService.participate(user1.id,event.id);
        participateRequestService.participate(user2.id,event.id);

        List<ParticipateRequest> participants = participateRequestService.getRequestsForEvent(event.id);

        Assert.assertEquals(participants.size(),2);

    }

}
