package com.KirklandKoncepts;

/**
 * Created by Kirkland on 10/28/17.
 */
public class ShortAnswer extends Question {

    public ShortAnswer () {
        setQuestionType("Short Answer");
    }

    @Override
    public void setAnswer() {

    }

    @Override
    public void create() {
        getPromptFromUser();
    }

    @Override
    public void display() {
        getPrompt().display();

    }

    @Override
    public void displayCorrectAnswer() {

    }
}
