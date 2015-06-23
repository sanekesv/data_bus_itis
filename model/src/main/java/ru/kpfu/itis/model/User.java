package ru.kpfu.itis.model;


import ru.kpfu.itis.model.enums.RoleEnum;

import javax.persistence.*;

@Entity
@SequenceGenerator(name = "user_gen", sequenceName = "users_seq")
@Table(name = "users")
public class User{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_gen")
    private Long id;

    @Column(name = "name")
    private String login;

    private String password;

    private RoleEnum role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }
}
