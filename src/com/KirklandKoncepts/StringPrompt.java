package com.KirklandKoncepts;

/**
 * Created by Kirkland on 10/27/17.
 */
public class StringPrompt extends Prompt {

    @Override
    public void display() {
        String string = (String) getPrompt();
        System.out.println(string + "\n");
    }

    @Override
    public Object getPrompt() {
        return super.getPrompt();
    }

    @Override
    public void setPrompt(Object prompt) {
        String string = (String)prompt;
        super.setPrompt(string);
    }
}
