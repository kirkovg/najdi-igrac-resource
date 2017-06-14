package com.najdiigrac.mk.persistence;

import com.najdiigrac.mk.model.jpa.UserPicture;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by bogda on 08.5.2017.
 */
public interface UserPictureRepository extends CrudRepository<UserPicture,Long> {
    UserPicture findByUserId(Long userId);
}
