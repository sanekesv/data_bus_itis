package ru.kpfu.itis.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ru.kpfu.itis.model.enums.GenderEnum;
import ru.kpfu.itis.model.enums.UserGroup;

/**
 * Created by sanekesv on 02.08.15.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    private Long id;

    private String login;

    private String name;

    private UserGroup group;

    private String academicGroup;

    private GenderEnum gender;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserGroup getGroup() {
        return group;
    }

    public void setGroup(UserGroup group) {
        this.group = group;
    }

    public String getAcademicGroup() {
        return academicGroup;
    }

    public void setAcademicGroup(String academicGroup) {
        this.academicGroup = academicGroup;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public Long getEntranceYear() {
        return entranceYear;
    }

    public void setEntranceYear(Long entranceYear) {
        this.entranceYear = entranceYear;
    }
}
