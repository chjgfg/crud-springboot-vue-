package org.vector.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.vector.crud.dao.PersonDao;
import org.vector.crud.dao.PositionDao;
import org.vector.crud.pojo.Person;
import org.vector.crud.pojo.Position;
import org.vector.crud.pojo.Util;
import org.vector.crud.pojo.UtilLike;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDao personDao;

    @Autowired
    private PositionDao positionDao;

    @Autowired
    private PositionService positionService;


    @Transactional
    @Override
    public Person add(Person person, String name) {
        Position byName = positionDao.findByName(name);
        person.setPosition(byName);
        return personDao.save(person);
    }

    @Transactional
    @Override
    public int delete(Long id) {
        personDao.deleteById(id);
        return 0;
    }

    /*@Override
    public Person update(Person person,String name) {
        *//*Person person = personDao.findById(2).get();
        person.setUsername("1222");
        person.setSex("女");
        person.setWorkid("1258963258");*//*
        Position p = new Position();
        p.setId(person.getPosition().getId());
        p.setName(name);
        person.setPosition(p);
        Position save1 = positionDao.save(p);
        Person save = personDao.save(person);
        System.out.println(save + save.getPosition().getName());
        System.out.println(save1);
        return person;
    }*/
    /*@Override
    public Person update(Person person) {
        Person save = personDao.save(person);
        return save;
    }*/

    @Transactional
    public Person update(Util util) {

        Position position = positionService.findByName(util.getName());
        /* System.out.println(position);*/
        //Person byName = personDao.findById(util.getId()).get();
        Person p = personDao.findById(util.getId()).get();
        /* Person p = new Person();*/
        p.setId(util.getId());
        p.setUsername(util.getUserName());
        p.setSex(util.getSex());
        p.setWorkid(util.getWorkId());
        p.setPosition(position);
        /*System.out.println(byName + "===" + byName.getPosition().getId());*/
        /*System.out.println(p + "+++s" + p.getPosition().getId());*/
        Person save = personDao.save(p);

        /* BeanUtils.copyProperties(p,byName);*/
        System.out.println("ooo");
        return save;
    }

    @Override
    public Person find(Long id) {
        Person p = personDao.findById(id).get();
        p.setName(p.getPosition().getName());
        System.out.println(p);
        return p;
    }

    @Override
    public List<Person> lists() {
        List<Person> all = personDao.findAll();
        return all;
    }

    @Override
    public Page<Person> findByPage(PageRequest pageRequest) {
        Page<Person> page = personDao.findAll(pageRequest);
        return page;
    }

    @Override
    public Person findByName(String name) {
        Person p = personDao.findByUserName(name);
        return p;
    }


    @Override
    public Page<Util> findByUtilPage(PageRequest pageRequest) {
        Page<Person> page = personDao.findAll(pageRequest);
        List<Person> pageList = page.getContent();//page转list
        long totalCount = page.getTotalElements();//获取总页数
        List<Util> utilList = new ArrayList<>();//创建一个util集合
        for (Person p : page) {
            Util util = new Util();
            util.setId(p.getId());
            util.setName(p.getPosition().getName());
            util.setSex(p.getSex());
            util.setWorkId(p.getWorkid());
            util.setUserName(p.getUsername());
            utilList.add(util);//将遍历得到的util对象放到util集合里
        }
        return new PageImpl<>(utilList, pageRequest, totalCount);
    }                       //util集合  方法传过来的值  总页数


    /*public List<Person> findByPositionName(String name){
        return personDao.findByPositionName("%"+name+"%");
    }
*/

    public Page<UtilLike> findByPositionNameToPage(PageRequest pageRequest, String name, int row) {
        List<Person> byPositionName = personDao.findByPositionName("%" + name + "%");
        int total = 0;//数据总数
        //int row = 10;//一页数量
        int totalPage = 0;

        List<UtilLike> likes = new ArrayList<>();

        for (Person p : byPositionName) {
            UtilLike utilLike = new UtilLike();
            utilLike.setId(p.getId());
            utilLike.setSex(p.getSex());
            utilLike.setUserName(p.getUsername());
            utilLike.setWorkId(p.getWorkid());
            likes.add(utilLike);
            total++;
        }
        totalPage = (total - 1) / row + 1;
        return new PageImpl<>(likes, pageRequest, totalPage);
    }


    public List<Integer> findCount() {
        List<Integer>  list = new ArrayList<>();
        int xuexiBu = personDao.findXuexiBu();
        list.add(xuexiBu);
        int shenghuoBu = personDao.findShenghuoBu();
        list.add(shenghuoBu);
        int suguanBu = personDao.findSuguanBu();
        list.add(suguanBu);
        int tiyuBu = personDao.findTiyuBu();
        list.add(tiyuBu);
        int wailianBu = personDao.findWailianBu();
        list.add(wailianBu);
        int wenyiBu = personDao.findWenyiBu();
        list.add(wenyiBu);
        int zuzhiBu = personDao.findZuzhiBu();
        list.add(zuzhiBu);
        return list;

    }


    public Page<Object[]> findByPosition(String name,int page, int size){
        PageRequest p = PageRequest.of(page,size);
        Page<Object[]> allByNameLike = personDao.findAllByNameLike("%" + name + "%",p);
        return allByNameLike;
    }



}
