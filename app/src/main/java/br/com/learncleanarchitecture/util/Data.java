package br.com.learncleanarchitecture.util;

import com.google.gson.Gson;

public class Data<T> {

    private T value;

    public Data(T obj) {
        this.value = obj;
    }

    public String toJson() {
        return new Gson().toJson(value);
    }

    public T getValue() {
        return value;
    }
}