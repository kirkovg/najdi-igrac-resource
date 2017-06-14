package com.najdiigrac.mk.web;

import com.najdiigrac.mk.authentication.NajdiIgracUserDetailsService;
import com.najdiigrac.mk.model.jpa.User;
import com.najdiigrac.mk.persistence.UsersRepository;
import com.najdiigrac.mk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/users", produces = "application/json")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UsersRepository usersRepository;


    @RequestMapping(method = RequestMethod.GET)
    public List<User> findAll() {
        return userService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @RequestMapping(value={"/save"}, method = RequestMethod.POST)
    public void save(@RequestBody User user) {
        userService.createUser(user.userName, user.password, user.email, user.telephone);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable Long id, @RequestBody User user) {
        userService.updateUser(id, user.userName, user.password, user.email, user.telephone);
    }

    @RequestMapping(value = "/addFollower/{id}", method = RequestMethod.POST)
    public void addFollower(@PathVariable Long id) {
        //get the current authenticated user
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentAuthenticatedUser = userService.findByUserName(userDetails.getUsername());
        userService.addFollower(currentAuthenticatedUser.id,id);
        //System.out.println(currentAuthenticatedUser);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable Long id) {
        userService.removeUser(id);
    }

}
