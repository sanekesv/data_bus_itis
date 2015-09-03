package ru.kpfu.itis.response;

/**
 * Created by ermolaev.a on 20.08.2015.
 */
public class MyResponse {

    private boolean status;

    private String errors;

    public MyResponse(String errors) {
        this.status = false;
        this.errors = errors;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }
}