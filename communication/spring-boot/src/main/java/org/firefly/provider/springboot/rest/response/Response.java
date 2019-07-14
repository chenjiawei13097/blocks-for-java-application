package org.firefly.provider.springboot.rest.response;

// 统一响应结构
public class Response<T> {
    /**
     * 比如0代表成功，-1代表用户参数有问题，1代表系统异常等。
     * 还可以用标准的HTTP状态码，如200代表成功。
     */
    private int code;
    /**
     * 如对应200的OK，或者success。
     */
    private String message;
    private T data;

    public Response(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // 一般用于有返回值的，如查询数据表结果
    public static <T> Response<T> success(T data) {
        return new Response<>(200, "success", data);
    }

    // 一般用于无返回值的，如更新、删除
    public static <T> Response<T> success() {
        return new Response<>(200, "success", null);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
