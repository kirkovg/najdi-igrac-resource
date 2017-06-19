package com.najdiigrac.mk.web;


import com.najdiigrac.mk.model.jpa.Event;
import com.najdiigrac.mk.model.jpa.User;
import com.najdiigrac.mk.model.jpa.UserPicture;
import com.najdiigrac.mk.service.UserPictureService;
import com.najdiigrac.mk.service.UserService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;


@RestController
@RequestMapping(value = "/api/users", produces = "application/json")
public class UserController {

    private UserService userService;

    private UserPictureService userPictureService;

    @Autowired
    public UserController(UserService userService, UserPictureService userPictureService) {
        this.userService = userService;
        this.userPictureService = userPictureService;
    }


    @RequestMapping(method = RequestMethod.GET)
    public List<User> findAll() {
        return userService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public User update(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user.userName, user.description, user.email, user.telephone);
    }

    @RequestMapping(value = "/follow/{id}", method = RequestMethod.POST)
    public void follow(@PathVariable Long id) {
        //get the current authenticated user
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentAuthenticatedUser = userService.findByUserName(userDetails.getUsername());
        userService.addFollower(id, currentAuthenticatedUser.id);
    }

    @RequestMapping(value = "/unfollow/{id}", method = RequestMethod.POST)
    public void unfollow(@PathVariable Long id) {
        //get the current authenticated user
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentAuthenticatedUser = userService.findByUserName(userDetails.getUsername());
        userService.removeFollower(id, currentAuthenticatedUser.id);
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
    public List<Event> findEventsForUser(@RequestParam String userName) {
        User user = userService.findByUserName(userName);
        return userService.findEventsForUser(user.id);
    }

    @RequestMapping(value = {"/getUserPicture/{userId}"}, method = RequestMethod.GET)
    @ResponseBody
    public void getUserPicture(@PathVariable Long userId, HttpServletResponse response) throws IOException, SQLException {
        OutputStream out = response.getOutputStream();
        UserPicture picture = userPictureService.findByUserId(userId);
        if (picture == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        String contentDisposition = String.format("inline;filename=\"%s\"",
                picture.fileName + ".png?pictureId=" + picture.id);
        response.setHeader("Content-Disposition", contentDisposition);
        response.setContentType("image/png");
        response.setContentLength(picture.size.intValue());
        IOUtils.copy(picture.data.getBinaryStream(), out);
        out.flush();
        out.close();
    }

    @RequestMapping(value = {"/{userId}/uploadPicture"}, method = RequestMethod.PUT)
    @ResponseBody
    public void uploadPicture(@RequestParam("file") MultipartFile file, @PathVariable Long userId) throws IOException, SQLException {
        byte[] bytes = file.getBytes();
        userPictureService.uploadUserPicture(userId,bytes,file.getContentType());
    }

    @RequestMapping(value = {"/{userId}/checkIfPictureExists"}, method = RequestMethod.GET)
    public void checkIfExists(@PathVariable Long userId, HttpServletResponse response) {
        UserPicture picture = userPictureService.findByUserId(userId);
        if (picture != null) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @RequestMapping(value = {"/{userId}/isFollowing"}, method = RequestMethod.GET)
    public boolean isFollowing(@PathVariable Long userId) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentAuthenticatedUser = userService.findByUserName(userDetails.getUsername());
        return userService.isFollowing(userId,currentAuthenticatedUser.id);
    }
}
