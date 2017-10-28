package com.KirklandKoncepts;

/**
 * Created by Kirkland on 10/27/17.
 */
public abstract class Prompt <T> {

    private T prompt;

    public abstract void display();

    public void setPrompt(T prompt) {
        this.prompt = prompt;
    }

    public T getPrompt() {
        return prompt;
    }
}
