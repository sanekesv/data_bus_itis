package ru.kpfu.itis.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sanekesv on 01.08.15.
 */
@Entity
@SequenceGenerator(name = "group_gen", sequenceName = "group_seq")
@Table(name = "groups")
public class AcademicGroup {

    @OneToMany(mappedBy = "academicGroup", cascade = CascadeType.ALL)
    private List<User> users = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "group_gen")
    private Long id;

    private String title;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
