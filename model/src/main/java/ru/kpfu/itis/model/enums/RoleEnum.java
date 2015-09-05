package ru.kpfu.itis.model.enums;

public enum RoleEnum {
    ADMIN("Администратор", 1),
    ELDER("Староста", 2),
    STUDENT("Студент", 3),
    ANONYMOUS("Анонимный пользователь", 4),
    TEACHER("Учитель", 5);
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