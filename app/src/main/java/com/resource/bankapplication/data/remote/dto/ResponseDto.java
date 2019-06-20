package com.resource.bankapplication.data.remote.dto;


public class ResponseDto<T> {

    protected Class<T> T;
    private Error error;

    public Class<T> getT() {
        return T;
    }

    public void setT(Class<T> t) {
        T = t;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}
