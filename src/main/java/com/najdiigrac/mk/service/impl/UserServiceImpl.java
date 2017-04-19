package com.najdiigrac.mk.service.impl;

import com.najdiigrac.mk.model.enums.UserType;
import com.najdiigrac.mk.model.jpa.User;
import com.najdiigrac.mk.persistence.UsersRepository;
import com.najdiigrac.mk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by bogda on 19.4.2017.
 */
@Service
public class UserServiceImpl implements UserService {

    private UsersRepository usersRepository;

    @Autowired
    private UserServiceImpl(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    @Override
    public User createAdminUser(String userName, String password) {
        User user = new User();
        user.userName = userName;
        user.password = (password);
        user.userType = UserType.ROLE_ADMIN;
        user.email = "";
        return usersRepository.save(user);
    }

    @Override
    public User createUser(String userName, String password) {
        return null;
    }

}
