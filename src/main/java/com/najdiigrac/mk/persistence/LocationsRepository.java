package com.najdiigrac.mk.persistence;

import com.najdiigrac.mk.model.jpa.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by bogda on 19.4.2017.
 */
@Repository
public interface LocationsRepository extends CrudRepository<Location,Long> {
    Location findByName(String name);
}
