package ru.kpfu.itis.util;

import java.util.List;

/**
 * Created by sanekesv on 02.08.15.
 */
public class listResponse<T> {
    public List<T> list;

    public listResponse(List<T> list) {
        this.list = list;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
