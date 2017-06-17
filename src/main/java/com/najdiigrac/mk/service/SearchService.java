package com.najdiigrac.mk.service;

import com.najdiigrac.mk.model.jpa.Event;
import com.najdiigrac.mk.model.jpa.User;

import java.util.List;

/**
 * Created by Win 8 on 12.05.2017.
 */
public interface SearchService {
    List<Event> searchEvent(String query);

    List<User> searchUsers(String query);
}
