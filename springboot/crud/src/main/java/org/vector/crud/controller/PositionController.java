package org.vector.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vector.crud.pojo.Position;
import org.vector.crud.service.PositionService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/position")
public class PositionController {

    @Autowired
    private PositionService positionService;

    @GetMapping("/lists")
    public List<Position> lists() {
        List<Position> list = new ArrayList<>();
        List<Position> lists = positionService.lists();
        if (lists != null) {
            for (Position p : lists) {
                /*System.out.println(p.getName());*/
                list.add(p);
            }
            return list;
        }
        return null;
    }

    @GetMapping("/find/{id}")
    public Position find(@PathVariable("id") Long id) {
        return positionService.find(id);
    }

    @PutMapping("/update")
    public String update(@RequestBody Position position) {
        if (positionService.findByName(position.getName()) != null) {
            return "exit";
        } else {
            Position update = positionService.update(position);
            if (update != null) {
                return "ok";
            } else {
                return "no";
            }
        }
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        int delete = positionService.delete(id);
        if (delete == 0) {
            return "ok";
        }
        return "no";
    }


    @PostMapping("/insert")
    public String insert(@RequestBody Position position) {
        Position byName = positionService.findByName(position.getName());
        if (byName != null) {
            return "exit";
        } else {
            Position add = positionService.add(position);
            System.out.println(add);
            if (add != null) {
                return "ok";
            }
        }
        return "no";
    }

    @GetMapping("/insert/{name}")
    public String insertOne(@PathVariable("name") String name) {
        Position byName = positionService.findByName(name);
        if (byName != null) {
            return "exit";
        } else {
            Position position = positionService.addOne(name);
            if (position != null) {
                return "ok";
            }
        }
        return "no";
    }

    @PostMapping("/name/{name}")
    public String name(@PathVariable("name") String name) {
        String name1 = positionService.name(name);
        if (name1 == null) {
            return "ok";
        } else {
            return "no";
        }

    }


}
