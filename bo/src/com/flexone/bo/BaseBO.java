package com.flexone.bo;

public abstract class BaseBO {
    int id;

    public int getId() {
        return id;
    }

    public BaseBO setId(int id) {
        this.id = id;
        return this;
    }
}
