package com.KirklandKoncepts;

/**
 * Created by Kirkland on 10/28/17.
 */
public class Essay extends Question {


    public Essay() {
        setQuestionType("Essay");
    }


    @Override
    public void setAnswer() {

    }


    @Override
    public void display() {
        getPrompt().display();
    }


    @Override
    public void create() {
        getPromptFromUser();

    }

}
