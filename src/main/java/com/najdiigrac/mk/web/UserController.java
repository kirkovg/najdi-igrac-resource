package com.najdiigrac.mk.web;


import com.najdiigrac.mk.model.jpa.Event;
import com.najdiigrac.mk.model.jpa.User;
import com.najdiigrac.mk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/users", produces = "application/json")
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping(method = RequestMethod.GET)
    public List<User> findAll() {
        return userService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @RequestMapping(value = {"/save"}, method = RequestMethod.POST)
    public void save(@RequestBody User user) {
        userService.createUser(user.userName, user.password, user.description, user.email, user.telephone);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable Long id, @RequestBody User user) {
        userService.updateUser(id, user.userName, user.password, user.description, user.email, user.telephone);
    }

    @RequestMapping(value = "/addFollower/{id}", method = RequestMethod.POST)
    public void addFollower(@PathVariable Long id) {
        //get the current authenticated user
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentAuthenticatedUser = userService.findByUserName(userDetails.getUsername());
        userService.addFollower(currentAuthenticatedUser.id, id);
        //System.out.println(currentAuthenticatedUser);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable Long id) {
        userService.removeUser(id);
    }

    @RequestMapping(value = {"/findByUserName"}, method = RequestMethod.GET)
    public User findByUsername(@RequestParam String userName) {
        return userService.findByUserName(userName);
    }

    @RequestMapping(value = {"/findEventsForUser"}, method = RequestMethod.GET)
    public List<Event> findEventsForUser(@RequestParam String userName){
        User user = userService.findByUserName(userName);
        return userService.findEventsForUser(user.id);
    }

}
