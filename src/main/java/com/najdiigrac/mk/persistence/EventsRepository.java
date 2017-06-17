package com.najdiigrac.mk.persistence;

import com.najdiigrac.mk.model.jpa.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EventsRepository extends CrudRepository<Event, Long> {

    List<Event> findByParticipantsId(Long id);

    Page<Event> findAllByOrderByDateTimeDesc(Pageable pageable);
}
