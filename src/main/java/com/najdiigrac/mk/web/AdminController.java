package com.najdiigrac.mk.web;

import com.najdiigrac.mk.model.jpa.User;
import com.najdiigrac.mk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by bogda on 08.5.2017.
 */

@RestController
@RequestMapping(value = "/api/admin", produces = "application/json")
public class AdminController {


    @Autowired
    UserService userService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void saveAdminUser(@RequestBody User user) {
        userService.createAdminUser(user.userName, user.password, user.email, user.telephone);
    }


    @RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable Long id) {
        userService.removeUser(id);
    }


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String hello() {
        return "hello from admin";
    }

    @RequestMapping(value = "/someOtherGet", method = RequestMethod.GET)
    public String hello2() {
        return "hello from someOtherGet";
    }

}
