package com.najdiigrac.mk.service;

import com.najdiigrac.mk.model.jpa.Event;
import com.najdiigrac.mk.model.jpa.User;

import java.util.List;

/**
 * Created by bogda on 19.4.2017.
 */
public interface UserService {
    User createAdminUser(String userName, String password, String email, String telephone);

    User createUser(String userName, String password, String email, String telephone);

    void removeUser(Long userId);

    User updateUser(Long userId, String userName, String password, String email, String telephone);

    User removeFollower(Long userId, Long friendId);

    User addFollower(Long userId, Long friendId);

    List<User> findUserFollowers(Long userId);

    List<Event> findEventsForUser(Long userId);

    User findById(Long userId);

    List<User> findAll();

    User findByUserName(String username);
}
