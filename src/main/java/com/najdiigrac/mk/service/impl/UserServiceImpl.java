package com.najdiigrac.mk.service.impl;

import com.najdiigrac.mk.model.enums.UserType;
import com.najdiigrac.mk.model.jpa.Event;
import com.najdiigrac.mk.model.jpa.User;
import com.najdiigrac.mk.persistence.EventsRepository;
import com.najdiigrac.mk.persistence.UsersRepository;
import com.najdiigrac.mk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bogda on 19.4.2017.
 */
@Service
public class UserServiceImpl implements UserService {

    private UsersRepository usersRepository;

    private EventsRepository eventsRepository;

    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserServiceImpl(UsersRepository usersRepository,
                            EventsRepository eventsRepository,
                            BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.usersRepository = usersRepository;
        this.eventsRepository = eventsRepository;
        this.passwordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User createAdminUser(String userName, String password, String email, String telephone) {
        User user = new User();
        user.userName = userName;
        user.password = encryptPassword(password);
        user.userType = UserType.ROLE_ADMIN;
        user.email = email;
        user.telephone = telephone;
        return usersRepository.save(user);
    }

    @Override
    public User createUser(String userName, String password, String description, String email, String telephone) {
        User user = new User();
        user.userName = userName;
        user.password = encryptPassword(password);
        user.userType = UserType.ROLE_USER;
        user.email = email;
        user.description = description;
        user.telephone = telephone;
        return usersRepository.save(user);
    }

    @Override
    public User updateUser(Long userId, String userName, String description, String email, String telephone) {
        User user = usersRepository.findOne(userId);
        user.userName = userName;
        user.email = email;
        user.description = description;
        user.telephone = telephone;
        return usersRepository.save(user);
    }

    @Override
    public void removeUser(Long userId) {
        usersRepository.delete(userId);
    }

    @Override
    public List<User> findUserFollowers(Long userId) {
        User user = usersRepository.findOne(userId);
        return user.followers;
    }

    @Override
    public List<Event> findEventsForUser(Long userId) {
        return eventsRepository.findByParticipantsId(userId);
    }

    @Override
    public User findById(Long userId) {
        return usersRepository.findOne(userId);
    }

    @Override
    public List<User> findAll() {
        return (List<User>) usersRepository.findAll();
    }

    @Override
    public User findByUserName(String username) {
        return usersRepository.findByUserName(username);
    }


    @Override
    public User addFollower(Long userId, Long followerId) {
        User user = usersRepository.findOne(userId);
        User follower = usersRepository.findOne(followerId);

        List<User> followersForUser = usersRepository.findOne(userId).followers;
        followersForUser.add(follower);
        user.followers = followersForUser;

        List<User> followingListOfFollower = follower.following;
        followingListOfFollower.add(user);
        follower.following = followingListOfFollower;

        usersRepository.save(follower);
        return usersRepository.save(user);
    }

    @Override
    public User removeFollower(Long userId, Long followerId) {
        User user = usersRepository.findOne(userId);
        User follower = usersRepository.findOne(followerId);

        List<User> followers = usersRepository.findOne(userId).followers;
        followers.remove(follower);
        user.followers = followers;

        List<User> followingListOfFollower = follower.following;
        followingListOfFollower.remove(user);
        follower.following = followingListOfFollower;

        return usersRepository.save(user);
    }

    @Override
    public boolean isFollowing(Long userId, Long followerId) {
        User user = usersRepository.findOne(userId);
        User follower = usersRepository.findOne(followerId);

        List<User> followersOfUser = user.followers;
        List<User> followingListOfFollower = follower.following;

        if (followersOfUser.contains(follower) && followingListOfFollower.contains(user)) {
            return true;
        }
        return false;
    }

    private String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }

}
