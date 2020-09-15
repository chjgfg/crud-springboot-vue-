package org.vector.crud.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.vector.crud.pojo.Person;
import org.vector.crud.pojo.Util;
import org.vector.crud.pojo.UtilLike;

import java.util.List;

public interface PersonService {

    Person add(Person person, String name);

    int delete(Long id);

    Person update(Util util);

    Person find(Long id);

    List<Person> lists();

    Page<Person> findByPage(PageRequest pageRequest);

    Person findByName(String name);

    Page<Util> findByUtilPage(PageRequest pageRequest);
//   Page<Person> findByUtilPage(PageRequest pageRequest);

    /*Page<Person> findByPositionName(String name, PageRequest pageRequest, int row);*/

    /*List<Person> findByPositionName(String name);*/

    Page<UtilLike> findByPositionNameToPage(PageRequest pageRequest, String name, int row);

    List<Integer> findCount();

    Page<Object[]> findByPosition(String name,int page, int size);


}
