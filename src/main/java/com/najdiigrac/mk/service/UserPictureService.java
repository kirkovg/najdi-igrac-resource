package com.najdiigrac.mk.service;

import com.najdiigrac.mk.model.jpa.UserPicture;

import java.sql.SQLException;

/**
 * Created by bogda on 06.6.2017.
 */
public interface UserPictureService {

    UserPicture uploadUserPicture(Long userId, byte[] bytes, String contentType) throws SQLException;

    UserPicture findByUserId(Long userId);
}
