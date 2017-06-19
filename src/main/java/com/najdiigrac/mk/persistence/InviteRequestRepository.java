package com.najdiigrac.mk.persistence;

import com.najdiigrac.mk.model.jpa.InviteRequest;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by bogda on 19.5.2017.
 */
public interface InviteRequestRepository extends CrudRepository<InviteRequest,Long> {
    List<InviteRequest> findByToIdOrderByDateTimeDesc(Long userId);

    InviteRequest findByIdAndToId(Long id, Long toId);

    List<InviteRequest> findByToId(Long userId);

    List<InviteRequest> findByEventId(Long eventId);
}
