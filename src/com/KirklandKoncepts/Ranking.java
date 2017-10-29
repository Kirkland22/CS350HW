package com.KirklandKoncepts;

import java.util.ArrayList;

/**
 * Created by Kirkland on 10/28/17.
 */
public class Ranking extends Question {


    public Ranking() {
        setQuestionType("Ranking");
    }

    @Override
    public void displayCorrectAnswer() {

    }

    @Override
    public void setAnswer() {

    }

    @Override
    public void display() {
        ArrayList<ChoiceResponse> leftSideChoices = getChoiceResponses();
        getPrompt().display();
        for (int i = 0; i < leftSideChoices.size(); i++) {
            consoleOutput.displayONELINE(getMutlipleChoiceOptions()[i] + ") " + leftSideChoices.get(i).getResponse() + "\n");
        }
        consoleOutput.displayONELINE("\n");
    }



}
