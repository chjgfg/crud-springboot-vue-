package org.vector.crud.service;

import org.vector.crud.pojo.Position;

import java.util.List;

public interface PositionService {

    Position add(Position position);

    int delete(Long id);

    Position update(Position position);

    Position find(Long id);

    Position findByName(String name);

    List<Position> lists();

    Position addOne(String name);

    String name(String name);
}
