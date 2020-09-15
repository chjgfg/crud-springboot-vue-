package org.vector.crud;

import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.vector.crud.dao.AdminDao;
import org.vector.crud.dao.PersonDao;
import org.vector.crud.dao.PositionDao;
import org.vector.crud.pojo.Admin;
import org.vector.crud.pojo.Person;
import org.vector.crud.pojo.Position;
import org.vector.crud.service.AdminService;
import org.vector.crud.util.Sha1HashUtil;

import javax.management.Query;
import java.util.List;
import java.util.UUID;

@SpringBootTest
public class CrudApplicationTests {

    @Autowired
    private AdminDao adminDao;

    @Autowired
    private AdminService adminService;

    @Test
    public void adminAddUseHeard() {
        Admin admin = new Admin();
        admin.setAdminName("admin");
        admin.setPassword("123456");
        adminDao.save(admin);
    }

    @Test
    public void adminFind() {
        Admin admin = adminService.findAdmin("admin", Sha1HashUtil.toSha1Hash("123456"));
        System.out.println(admin);
    }

    @Test
    public void adminAdd() {
        Admin admin = new Admin();
        admin.setAdminName("lyf");
        admin.setPassword(Sha1HashUtil.toSha1Hash("111111"));
        adminService.addAdmin(admin);
    }


    @Test
    public void adminFindByName() {
        Admin admin = adminService.findAdmin("admin");
        System.out.println(admin);
    }


    /*====================================================================================================================================================================================================================================================================================================================================================================================*/

    @Autowired
    private PositionDao positionDao;

    @Test
    public void add() {
        Position position = new Position();
        position.setName("11111部");
        Position save = positionDao.save(position);
        System.out.println(save);
    }

    @Test
    public void delete() {
        positionDao.deleteById((long) 4);
    }

    @Test
    public void find() {
        Position position1 = positionDao.findById((long) 1).get();
        System.out.println(position1);
    }

    @Test
    public void findByName() {
        Position p = positionDao.findByName("文艺部");
        System.out.println(p);
    }

    @Test
    public void update() {
        Position p1 = new Position();
        p1.setId((long) 4);
        p1.setName("社调部");

        Position p = new Position();
        p.setId(p1.getId());
        p.setName(p1.getName());
        Position update = positionDao.save(p);



        /*Position position = new Position();
        position.setName("11111部");
        Position save = positionDao.save(position);*/
        System.out.println(update);
    }

    @Test
    public void lists() {
        List<Position> all = positionDao.findAll();
        System.out.println(all);
    }


    @Test
    public void name() {
        String name = positionDao.name("%" + "学" + "%");
        System.out.println(name);
    }

    /*====================================================================================================================================================================================================================================================================================================================================================================================*/
    @Autowired
    private PersonDao personDao;

    @Test
    public void add1() {

        for (int i = 0; i < 500; i++) {
            String s = UUID.randomUUID().toString();
            //System.out.println(s);
            String s2 = s.substring(0, 6).toString();
            //System.out.println(s.substring(0,6));
            StringBuffer sb = new StringBuffer(s);
            String s1 = sb.reverse().toString();
            //System.out.println(s1);
            String s3 = s1.substring(0, 11).toString();
            //System.out.println(s1.substring(0,11));

            Person person1 = new Person();
            person1.setUsername(s2);
            if (i % 3 == 0) {
                person1.setSex("女");
            } else {
                person1.setSex("男");
            }
            person1.setWorkid(s3);
            Position p = new Position();
            if (i % 2 == 0) {
                p.setId((long) 1);
            } else if (i % 3 == 0) {
                p.setId((long) 2);
            } else if (i % 5 == 0) {
                p.setId((long) 3);
            } else if (i % 7 == 0) {
                p.setId((long) 4);
            } else if (i % 8 == 0) {
                p.setId((long) 5);
            } else {
                p.setId((long) 6);
            }
            person1.setPosition(p);
            Person save = personDao.save(person1);
            System.out.println(save);
        }
    }

    @Test
    public void delete1() {
        personDao.deleteById((long) 3);

    }

    @Test
    public void update1() {
        Person person = personDao.findById((long) 2).get();
        person.setUsername("1222");
        person.setSex("女");
        person.setWorkid("1258963258");
        Position p = new Position();
        p.setId(person.getPosition().getId());
        p.setName("创新部");
        person.setPosition(p);
        Position save1 = positionDao.save(p);
        Person save = personDao.save(person);
        System.out.println(save + save.getPosition().getName());
        System.out.println(save1);
    }

    @Test
    public void find1() {
        Person person = personDao.findById((long) 12).get();
        System.out.println(person.getUsername() + person.getPosition().getName());
    }

    @Test
    public void list() {
        List<Person> all = personDao.findAll();
        for (Person p : all) {
            System.out.println(p);
            System.out.println(p.getPosition().getName());
        }
    }
/*
    @Test
    public void findAll() {
        PageRequest pageRequest = new PageRequest();
        Page<Person> all = personDao.findAll(pageRequest);
        for (Person p:all){
            System.out.println(p);
        }
    }*/

    @Test
    public void findByPersonName() {
        int total = 0;//数据总数
        int row = 10;//一页数量
        int totalPage = 0;
        List<Person> name = personDao.findByPositionName("%" + "生活" + "%");
        for (Person person : name) {
            System.out.println(person);
            total++;
        }
        System.out.println(total);

        System.out.println((total - 1) / row + 1);
    }


    /*
        @Query(nativeQuery = true, value = "select p from Person p left join Position pp on p.position.id = pp.id where pp.name like ?1 and  limit ?2 , ?3 ")

    * */
   /* @Test
    public void findByPersonNamefor() {
        Session session = null;
        String sql = "select p from Person p left join Position pp on p.position.id = pp.id where pp.name like ?1 ";
        session.createQuery(sql);

        *//*List<Person> name = personDao.findByPositionName("生活部");
        for(Person person :name){
            System.out.println(person);
        }
        System.out.println(name);*//*
    }*/


    @Test
    public void count() {
        int xuexiBu = personDao.findXuexiBu();
        System.out.println("学习部" + xuexiBu);
        System.out.println("===============================");
        int wenyibu = personDao.findWenyiBu();
        System.out.println("文艺部" + wenyibu);
        System.out.println("===============================");
        int tiyuBu = personDao.findTiyuBu();
        System.out.println("体育部" + tiyuBu);
        System.out.println("===============================");
        int shenghuoBu = personDao.findShenghuoBu();
        System.out.println("生活部" + shenghuoBu);
        System.out.println("===============================");
        int wailianBu1 = personDao.findWailianBu();
        System.out.println("外联部" + wailianBu1);
        System.out.println("===============================");
        int zuzhiBu = personDao.findZuzhiBu();
        System.out.println("组织部" + zuzhiBu);
        System.out.println("===============================");
        int suguanBu = personDao.findSuguanBu();
        System.out.println("宿管部" + suguanBu);
        System.out.println("===============================");
    }


    @Test
    public void name222() {
        PageRequest p = PageRequest.of(1,10);
        Page<Object[]> allByNameLike = personDao.findAllByNameLike("%" + "学" + "%",p);
        List<Object[]> content = allByNameLike.getContent();
        System.out.println(allByNameLike);

        for (Object pp :content){
            System.out.println(pp);
        }

    }


}
