package ru.kpfu.itis.auth.response;

public class ErrorResponse {
    private String error;
    private String msg;

    public ErrorResponse(String error, String msg) {
        this.error = error;
        this.msg = msg;
    }

    public ErrorResponse() {
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
