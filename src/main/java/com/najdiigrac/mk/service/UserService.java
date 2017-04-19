package com.najdiigrac.mk.service;

import com.najdiigrac.mk.model.jpa.FriendRequest;
import com.najdiigrac.mk.model.jpa.User;

/**
 * Created by bogda on 19.4.2017.
 */
public interface UserService {
    User createAdminUser(String userName,String password);
    User createUser(String userName,String password);
}
