package com.najdiigrac.mk;

import com.najdiigrac.mk.model.enums.SportType;
import com.najdiigrac.mk.model.jpa.*;
import com.najdiigrac.mk.service.EventService;
import com.najdiigrac.mk.service.LocationService;
import com.najdiigrac.mk.service.UserService;
import org.assertj.core.api.AssertionInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class NajdiIgracApplicationTests {

	@Autowired
	EventService eventService;
	@Autowired
	UserService userService;

	@Autowired
	LocationService locationService;


	User user;
	com.najdiigrac.mk.model.jpa.Event event;
	Location location;


	@Test
	public void contextLoads() {
	}

	@Test
	public void eventServiceHelperTests(){
		userService.createAdminUser("Bogdan","123");
		userService.createAdminUser("Bogdan2","123");
		location = locationService.createLocation("Shkolski","Skopje","Orce Nikolov","19");
		Assert.assertNotNull(location);
        String str = "1986-04-08 12:30";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
		event = eventService.createEvent(new Long[]{1l,2l},"Fudbal u shkolski","Blabla", SportType.FOOTBAL,location.id,dateTime);
	    Assert.assertNotNull(event);
	    Assert.assertEquals(event.name,"Fudbal u shkolski");
	    Assert.assertEquals(event.sport,SportType.FOOTBAL);
	}

}
