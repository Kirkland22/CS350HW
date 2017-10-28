package com.KirklandKoncepts;

/**
 * Created by Kirkland on 10/27/17.
 */
public abstract class AnswerResponse<T> {

    private T response;

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public abstract void display();
}
