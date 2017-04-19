package com.najdiigrac.mk.service;

import com.najdiigrac.mk.model.enums.SportType;
import com.najdiigrac.mk.model.jpa.Event;
import com.najdiigrac.mk.model.jpa.Location;
import com.najdiigrac.mk.model.jpa.User;
import com.najdiigrac.mk.persistence.EventsRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Win 8 on 19.04.2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class EventServiceTest {

    @Autowired
    EventService eventService;
    @Autowired
    UserService userService;

    @Autowired
    LocationService locationService;

    @Autowired
    EventsRepository eventsRepository;

    User user;
    Event event;
    Location location;
    Location location1;

    @Test
    public void eventCreationTest(){
        userService.createAdminUser("Bogdan","123");
        userService.createAdminUser("Bogdan2","123");
        location = locationService.createLocation("Shkolski","Skopje","Orce Nikolov","19");
        Assert.assertNotNull(location);
        String str = "1986-04-08 12:30";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
        event = eventService.createEvent(new Long[]{1l,2l},"Fudbal u shkolski","Blabla", SportType.FOOTBALL,location.id,dateTime);
        Assert.assertNotNull(event);
        Assert.assertEquals(event.name,"Fudbal u shkolski");
        Assert.assertEquals(event.sport,SportType.FOOTBALL);
    }

    @Test
    public void addAdminsTest(){
        userService.createAdminUser("Bogdan","123");
        userService.createAdminUser("Bogdan2","123");
        location = locationService.createLocation("Shkolski","Skopje","Orce Nikolov","19");
        location1 = locationService.createLocation("Kaj oleg u dvor","Skopje","Xhon Kennedy","47");
        String str = "1986-04-08 14:30";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
        event = eventService.createEvent(new Long[]{1l},"Fudbal u shkolski","Blabla", SportType.FOOTBALL,location.id,dateTime);
        Assert.assertEquals(event.admins.size(),1);
        eventService.addAdmin(1l,2l);
        event = eventsRepository.findOne(1l);
        Assert.assertEquals(event.admins.size(),2);
        event = eventService.removeAdmin(1l,1l);
        Assert.assertEquals(event.admins.size(),1);
        event = eventService.updateEvent(1l,new Long[]{1l,2l},"Basket kaj oleg u dvor","Kopjuterce ke igrame",SportType.BASKETBALL,location1.id,dateTime);
        Assert.assertNotNull(event);
        Assert.assertEquals(event.admins.size(),2);
        Assert.assertEquals(event.name,"Basket kaj oleg u dvor");
        Assert.assertEquals(event.description,"Kopjuterce ke igrame");
        Assert.assertEquals(event.sport,SportType.BASKETBALL);
        Assert.assertEquals(event.location,location1);
        Assert.assertEquals(event.dateTime,dateTime);
        eventService.removeEvent(event.id);
        event = eventsRepository.findOne(event.id);
        Assert.assertNull(event);

    }

}
