package ru.kpfu.itis.model;


import ru.kpfu.itis.model.enums.RoleEnum;
import ru.kpfu.jbl.auth.domain.AuthUser;

import javax.persistence.*;

@Entity
@SequenceGenerator(name = "user_gen", sequenceName = "users_seq")
@Table(name = "users")
public class User implements AuthUser {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_gen")
    private Long id;

    private String login;

    private String password;

    private RoleEnum role;

    private String salt;

    private String name;

    @ManyToOne
    @JoinColumn(nullable = true, name = "academic_group_id", referencedColumnName = "id")
    private AcademicGroup academicGroup;

    @Column(name = "entrance_year")
    private Long entranceYear;

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

    @Override
    @Transient
    public String getUserRole() {
        return role == null ? null : role.name();
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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AcademicGroup getAcademicGroup() {
        return academicGroup;
    }

    public void setAcademicGroup(AcademicGroup academicGroup) {
        this.academicGroup = academicGroup;
    }

    public Long getEntranceYear() {
        return entranceYear;
    }

    public void setEntranceYear(Long entranceYear) {
        this.entranceYear = entranceYear;
    }
}
