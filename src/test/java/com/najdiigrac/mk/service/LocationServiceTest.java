package com.najdiigrac.mk.service;

import com.najdiigrac.mk.model.jpa.Location;
import com.najdiigrac.mk.persistence.LocationsRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Win 8 on 19.04.2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class LocationServiceTest {
    @Autowired
    LocationService locationService;

    @Autowired
    LocationsRepository locationsRepository;

    @Test
    public void createLocation() {
        Location location = locationService.createLocation(
                "kaj branana",
                "Skopje",
                "pero nakov bb",
                null
        );

        Assert.assertNotNull(location);
        Assert.assertEquals("kaj branana",location.name);
        Assert.assertEquals("Skopje",location.city);
        Assert.assertEquals("pero nakov bb",location.street);
        Assert.assertEquals(null,location.streetNumber);
    }

    @Test
    public void updateLocation() {
        Location location = locationService.createLocation(
                "kaj branana",
                "Skopje",
                "pero nakov bb",
                null
        );

        Location updatedLocation = locationService.updateLocation(
                location.id,
                "na b'ncon",
                "Ohrid",
                "alzirska",
                "69"
        );

        Assert.assertNotNull(updatedLocation);
        Assert.assertEquals("na b'ncon",updatedLocation.name);
        Assert.assertEquals("Ohrid",updatedLocation.city);
        Assert.assertEquals("alzirska",updatedLocation.street);
        Assert.assertEquals("69",updatedLocation.streetNumber);
    }

    @Test
    public void removeLocation() {
        Location location = locationService.createLocation(
                "kaj branana",
                "Skopje",
                "pero nakov bb",
                null
        );

        locationService.removeLocation(location.id);

        Location removedLocation = locationsRepository.findOne(location.id);
        Assert.assertNull(removedLocation);

    }

    @Test
    public void findAllLocations() {
        Location location1 = locationService.createLocation(
                "kaj branana",
                "Skopje",
                "pero nakov bb",
                null
        );

        Location location2 = locationService.createLocation(
                "na b'ncon",
                "Ohrid",
                "alzirska",
                "69"
        );

        List<Location> locations = new ArrayList<>();
        locations.add(location1);
        locations.add(location2);


        List<Location> locationsFromDB = locationService.findAllLocations();
        Assert.assertNotNull(locationsFromDB);
        Assert.assertEquals(2,locationsFromDB.size());
        Assert.assertEquals(locations,locationsFromDB);
    }
}
