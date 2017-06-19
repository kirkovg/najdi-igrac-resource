/*
package com.najdiigrac.mk.service;

import com.najdiigrac.mk.model.enums.SportType;
import com.najdiigrac.mk.model.jpa.Event;
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
 * Created by Win 8 on 07.05.2017.
 *//*

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    LocationService locationService;

    @Autowired
    EventService eventService;

    @Test
    public void addFollower() {

    }

    @Test
    public void removeFollower() {

    }


    @Test
    public void findUserFollowers() {
        User user1 = userService.createUser("jas","nesto",null,null,null);
        User user2 = userService.createUser("jas2","nesto2",null,null,null);
        userService.addFollower(user1.id,user2.id);

        List<User> followers = userService.findUserFollowers(user1.id);
        Assert.assertNotNull(followers);
        Assert.assertEquals(1,followers.size());
    }

    @Test
    public void findEventsForUser() {
        User user1 = userService.createUser("jas","nesto",null,null,null);

        User user2 = userService.createUser("jas2","nesto2",null,null,null);
        Event event1 = eventService.createEvent(user1.id,"Fudbal u shkolski","Blabla", SportType.FOOTBALL,null,null);
        Event event2 = eventService.createEvent(user1.id,"Fudbal u shkolski","Blabla", SportType.FOOTBALL,null,null);

        eventService.addParticipant(event1.id,user2.id);
        eventService.addParticipant(event2.id,user2.id);

        List<Event> foundEvents = userService.findEventsForUser(user2.id);
        Assert.assertNotNull(foundEvents);
        Assert.assertEquals(2,foundEvents.size());
    }


    @Test
    public void isFollowing() {
        User user1 = userService.createUser("jas","nesto",null,null,null);
        User user2 = userService.createUser("jas2","nesto2",null,null,null);

        userService.addFollower(user1.id,user2.id);
        boolean isFollowing = userService.isFollowing(user1.id,user2.id);

        Assert.assertEquals(true,isFollowing);
    }

}
*/
