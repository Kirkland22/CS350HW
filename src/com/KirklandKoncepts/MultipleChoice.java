package com.KirklandKoncepts;

import java.util.ArrayList;

/**
 * Created by Kirkland on 10/21/17.
 */
public class MultipleChoice extends Question {



    public MultipleChoice() {
        setQuestionType("Multiple Choice");
    }


    @Override
    public void getAnswer() {

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
