package com.najdiigrac.mk.persistence;

import com.najdiigrac.mk.model.jpa.ParticipateRequest;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by bogda on 01.6.2017.
 */
public interface ParticipateRequestRepository extends CrudRepository<ParticipateRequest,Long> {
    List<ParticipateRequest> findByEventId(Long eventId);

    List<ParticipateRequest> findByFromId(Long fromId);
}
