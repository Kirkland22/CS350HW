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
    public void setAnswer() {
        display();
        consoleOutput.display("Enter the correct order");
        ChoiceResponse<String> answer;
        for (int i = 0; i < getNumOfChoices(); i++) {
            answer = new StringChoiceResponse();
            consoleOutput.display("Enter Rank Order #"+ (i + 1) + ":");
            answer.setResponse(consoleInput.getInput());
            addAnswer(answer);
        }
    }

    @Override
    public void display() {
        ArrayList<ChoiceResponse> leftSideChoices = getChoiceResponses();
        getPrompt().display();
        for (int i = 0; i < leftSideChoices.size(); i++) {
            consoleOutput.displayONELINE(getMultipleChoiceOptions().get(i) + ") " + leftSideChoices.get(i).getResponse() + "\n");
        }
        consoleOutput.displayONELINE("\n");
    }



}
