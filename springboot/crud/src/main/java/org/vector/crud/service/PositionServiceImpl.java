package org.vector.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vector.crud.dao.PositionDao;
import org.vector.crud.pojo.Position;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {
    @Autowired
    private PositionDao positionDao;

    @Transactional
    @Override
    public Position add(Position position) {
        return positionDao.save(position);
    }

    @Transactional
    @Override
    public Position addOne(String name) {
        Position p = new Position();
        p.setName(name);
        return positionDao.save(p);
    }

    @Transactional
    @Override
    public int delete(Long id) {
        positionDao.deleteById(id);
        return 1;
    }

    @Transactional
    @Override
    public Position update(Position position) {
       /* Position  p = new Position();
        p.setId(position.getId());
        p.setName(position.getName());*/
        return positionDao.save(position);
    }

    @Override
    public Position find(Long id) {
        return positionDao.findById(id).get();
    }

    public Position findByName(String name){
        return positionDao.findByName(name);
    }

    @Override
    public List<Position> lists() {
        return positionDao.findAll();
    }

    public String name(String name){
        return positionDao.name("%" + "学" + "%");
    }

}
