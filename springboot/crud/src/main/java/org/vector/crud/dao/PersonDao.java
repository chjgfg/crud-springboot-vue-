package org.vector.crud.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.vector.crud.pojo.Person;

import java.util.List;

public interface PersonDao extends JpaRepository<Person, Long>, JpaSpecificationExecutor<Person> {

    /*@Query("select * from Person p1 left join Position p2 on p1.position=p2.  ")
    Page<Person>  findAll();*/

    Person findByUserName(String name);


    @Query("select p from Person p left join Position pp on p.position.id = pp.id where pp.name like ?1")
    List<Person> findByPositionName(String name);

    @Query("select count(p)  from Person p left join Position pp on p.position.id = pp.id where pp.name ='学习部' ")
    int findXuexiBu();

    @Query("select count(p)  from Person p left join Position pp on p.position.id = pp.id where pp.name ='文艺部' ")
    int findWenyiBu();

    @Query("select count(p)  from Person p left join Position pp on p.position.id = pp.id where pp.name ='体育部' ")
    int findTiyuBu();

    @Query("select count(p)  from Person p left join Position pp on p.position.id = pp.id where pp.name ='生活部' ")
    int findShenghuoBu();

    @Query("select count(p)  from Person p left join Position pp on p.position.id = pp.id where pp.name ='外联部' ")
    int findWailianBu();

    @Query("select count(p)  from Person p left join Position pp on p.position.id = pp.id where pp.name ='组织部' ")
    int findZuzhiBu();

    @Query("select count(p)  from Person p left join Position pp on p.position.id = pp.id where pp.name ='宿管部' ")
    int findSuguanBu();


    @Query(value = "select p from Person p left join Position pp on p.position.id = pp.id where pp.name like ?1")
    Page<Object[]> findAllByNameLike(String name, PageRequest pageRequest);


}
