package com.najdiigrac.mk.service.impl;


import com.najdiigrac.mk.model.jpa.UserPicture;
import com.najdiigrac.mk.persistence.UserPictureRepository;
import com.najdiigrac.mk.persistence.UsersRepository;
import com.najdiigrac.mk.service.UserPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.SQLException;

/**
 * Created by bogda on 06.6.2017.
 */
@Service
public class UserPictureServiceImpl implements UserPictureService {

    private UsersRepository usersRepository;

    private UserPictureRepository userPictureRepository;

    @Autowired
    public UserPictureServiceImpl(UsersRepository usersRepository, UserPictureRepository userPictureRepository){
        this.usersRepository = usersRepository;
        this.userPictureRepository = userPictureRepository;
    }

    @Override
    public UserPicture uploadUserPicture(Long userId, byte[] bytes, String contentType) throws SQLException {
        UserPicture userPicture = new UserPicture();
        UserPicture foundUserPicture = userPictureRepository.findByUserId(userId);
        if (foundUserPicture != null) {
            userPictureRepository.delete(foundUserPicture);
        }
        userPicture.user = usersRepository.findOne(userId);
        userPicture.contentType = contentType;
        userPicture.data = new SerialBlob(bytes);
        userPicture.size = Long.valueOf(bytes.length);
        userPicture.fileName = userPicture.user.userName + "_img" ;
        return userPictureRepository.save(userPicture);
    }

    @Override
    public UserPicture findByUserId(Long userId) {
        return userPictureRepository.findByUserId(userId);
    }
}
