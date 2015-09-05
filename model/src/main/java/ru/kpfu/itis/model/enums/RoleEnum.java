package ru.kpfu.itis.model.enums;

public enum RoleEnum {
    STUDENT("Студент", 0),
    ADMIN("Администратор", 1),
    ANONYMOUS("Анонимный пользователь", 2),
    ELDER("Староста", 3),
    TEACHER("Учитель", 4);
    String title;
    int level;

    RoleEnum(String title, int level) {
        this.title = title;
        this.level = level;
    }

    public String getTitle() {
        return title;
    }

    public int getLevel() {
        return level;
    }
}