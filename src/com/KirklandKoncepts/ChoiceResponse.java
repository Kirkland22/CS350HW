package com.KirklandKoncepts;

import java.io.Serializable;

/**
 * Created by Kirkland on 10/27/17.
 */
public abstract class ChoiceResponse<T> implements Serializable {

    private T response;

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public abstract void display();
}
