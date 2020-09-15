package org.vector.crud.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_position")

public class Position implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Position() {
    }

    @OneToMany(mappedBy = "position")
    @JsonIgnore    /*重中之重*/
   /* Set<Person> lists = new HashSet<>();*/
    List<Person> lists = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Person> getLists() {
        return lists;
    }

    public void setLists(List<Person> lists) {
        this.lists = lists;
    }

   /* public Set<Person> getLists() {
        return lists;
    }

    public void setLists(Set<Person> lists) {
        this.lists = lists;
    }*/

    @Override
    public String toString() {
        return "Position{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
