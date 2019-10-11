package me.stephenj.administration.model;

public class Response {
    private Integer status;
    private String message;
    private Object object;

    private Response() {}

    private Response(Integer status, String message, Object object) {
        this.status = status;
        this.message = message;
        this.object = object;
    }

    public static Response build() {
        return new Response();
    }

    public static Response ok(String message, Object object) {
        return new Response(200, message, object);
    }

    public static Response ok(String message) {
        return new Response(200, message, null);
    }

    public static Response error(String message, Object object) {
        return new Response(500, message, object);
    }

    public static Response error(String message) {
        return new Response(500, message, null);
    }

    public Integer getStatus() {
        return status;
    }

    public Response setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Response setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getObject() {
        return object;
    }

    public Response setObject(Object object) {
        this.object = object;
        return this;
    }
}
