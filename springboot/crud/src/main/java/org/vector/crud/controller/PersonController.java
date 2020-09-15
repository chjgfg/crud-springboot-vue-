package org.vector.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vector.crud.pojo.Person;
import org.vector.crud.pojo.Util;
import org.vector.crud.pojo.UtilLike;
import org.vector.crud.service.PersonService;
import org.vector.crud.service.PositionService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

  @Autowired
  private PersonService personService;

  @Autowired
  private PositionService positionService;

   /* @GetMapping("/findAllPerson/{name}")
    public List<Person> findByPositionName(@PathVariable("name") String name) {
        return personService.findByPositionName(name);
    }*/

    /*@GetMapping("/findAllToPage/{page}/{size}/{name}")
    public Page<UtilLike> findAll(@PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) @PathVariable("page") int page, @PathVariable("size") int size, @PathVariable("name") String name) {
        if (name == null) {
            return null;
        }else{
            PageRequest of = PageRequest.of(page, size);
            return personService.findByPositionNameToPage(of, name,size);
        }
    }*/

  @GetMapping("/findAll/{page}/{size}")
  public Page<Util> findAll(@PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC)
                            @PathVariable("page") int page, @PathVariable("size") int size) {
    PageRequest of = PageRequest.of(page, size);
    return personService.findByUtilPage(of);
  }


  @GetMapping("/lists")
  public String lists() {
    List<String> name = new ArrayList<>();
    List<Person> lists = personService.lists();
    if (lists != null) {
      for (Person p : lists) {
        /*System.out.println(p + p.getPosition().getName());*/
        name.add(p.getPosition().getName());
      }
      /*System.out.println(name);*/
      return "ok";
    }
    return "no";
  }

    /*@GetMapping("/find/{id}")
    public String find(@PathVariable("id") Integer id) {
        Person position = personService.find(id);
        if (position != null) {
            return "ok" + position + position.getPosition().getName();
        }
        return "no";
    }*/

  @GetMapping("/find/{id}")
  public Util find(@PathVariable("id") Long id) {
    Person person = personService.find(id);
    Util util = new Util();
    if (person != null) {
      util.setId(person.getId());
      util.setUserName(person.getUsername());
      util.setWorkId(person.getWorkid());
      util.setSex(person.getSex());
      util.setName(person.getPosition().getName());
    }
    return util;
  }


  @GetMapping("/findPosition/{id}")
  public Person findPosition(@PathVariable("id") Long id) {
    Person position = personService.find(id);
    if (position != null) {
      return position;
    }
    return null;
  }

  @PutMapping("/update")
  public String update(@RequestBody Util util) {
        /*System.out.println(util);

        Position position = positionService.findByName(util.getName());
        System.out.println(position);
        Person byName = personService.find(util.getId());
        byName.setUsername(util.getUserName());
        byName.setSex(util.getSex());
        byName.setWorkid(util.getWorkId());
        byName.getPosition().setId(position.getId());
        System.out.println(byName + "===" + byName.getPosition().getId());

        Person add = personService.update(byName);
        if (add != null) {
            return "ok";
        }*/

    if (personService.findByName(util.getUserName()) != null) {
      return "exit";
    } else {

      Person update = personService.update(util);

      if (update != null) {
        return "ok";
      } else {
        return "no";
      }
    }
  }


  @DeleteMapping("/delete/{id}")
  public String delete(@PathVariable("id") Long id) {
    int delete = personService.delete(id);
    if (delete == 0) {
      return "ok";
    }
    return "no";
  }


    /*@PostMapping("/insert/{name}")
    public String insert(@PathVariable("name") String name,Person person) {
        Person add = personService.add(person, name);
        if (add != null) {
            return "ok";
        }
        return "no";
    }*/


  @PostMapping("/add")
  public String insertObj(@RequestBody Util util) {
    Person byName = personService.findByName(util.getUserName());
    if (byName != null) {
      return "exit";
    } else {
      Person p = new Person();
      p.setUsername(util.getUserName());
      p.setWorkid(util.getWorkId());
      p.setSex(util.getSex());
            /*System.out.println(util);
            System.out.println(p + "======" + util.getName());*/
      Person add = personService.add(p, util.getName());
      if (add != null) {
        return "ok";
      }
    }
    return "no";
  }


  @GetMapping("/findCount")
  public List<Integer> findCount() {
    return personService.findCount();
  }


  @GetMapping("/findAllByPosition/{page}/{size}/{name}")
  public Page<Object[]> findAllByPosition(@PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) @PathVariable("page") int page, @PathVariable("size") int size, @PathVariable("name") String name) {
    Page<Object[]> byPosition = personService.findByPosition(name, page, size);
    return byPosition;
  }


}
