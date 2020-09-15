package org.vector.crud.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.vector.crud.pojo.Position;

public interface PositionDao extends JpaRepository<Position,Long> {

    Position findByName(String name);

    @Query("select p.name from Position p where p.name like ?1")
    String name(String name);


}
