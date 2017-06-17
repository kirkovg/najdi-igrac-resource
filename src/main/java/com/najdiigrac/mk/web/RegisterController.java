package com.najdiigrac.mk.web;

import com.najdiigrac.mk.model.jpa.User;
import com.najdiigrac.mk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Win 8 on 17.06.2017.
 */
@RestController
@RequestMapping(value = "/api/register", produces = "application/json")
public class RegisterController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public void save(@RequestBody User user) {
        System.out.println(user);
        userService.createUser(user.userName, user.password, user.description, user.email, user.telephone);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String hello() {
        return "Hello from register";
    }
}
