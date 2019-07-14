package org.firefly.provider.springboot.rest.response;

public class Response<T> {
    private int code;
    private Status status;
    private T data;

    public enum Status {
        OK, EXCEPTION
    }

    public Response(int code, Status status, T data) {
        this.code = code;
        this.status = status;
        this.data = data;
    }

    public static <T> Response<T> success(T data) {
        return new Response<>(200, Status.OK, data);
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
}
