package ru.kpfu.itis.model.enums;

public enum UserGroup {
    ADMIN("Администратор", 1),
    ELDER("Староста", 2),
    STUDENT("Студент", 3),
    ANONYMOUS("Анонимный пользователь", 4);
    String title;
    int level;

    UserGroup(String title, int level) {
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

