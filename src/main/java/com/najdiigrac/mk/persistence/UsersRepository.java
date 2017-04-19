package com.najdiigrac.mk.persistence;

import com.najdiigrac.mk.model.jpa.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by bogda on 19.4.2017.
 */
@Repository
public interface UsersRepository extends CrudRepository<User, Long> {
    User findByUserName(String userName);
}
