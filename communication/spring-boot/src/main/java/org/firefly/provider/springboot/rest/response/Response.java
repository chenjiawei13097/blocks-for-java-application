package org.firefly.provider.springboot.rest.response;

public class Response<T> {
    private int code;
    private Status status;
    private T data;

    public Response(int code, Status status, T data) {
        this.code = code;
        this.status = status;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public enum Status {
        OK, EXCEPTION
    }
}
