package com.KirklandKoncepts;

import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.ArrayList;

/**
 * Created by Kirkland on 10/21/17.
 */
public class MultipleChoice extends Question {



    public MultipleChoice() {
        setQuestionType("Multiple Choice");
    }

    @Override
    public void displayCorrectAnswer() {

    }

    @Override
    public void setAnswer() {

        ChoiceResponse<String> answers;
        display();
        consoleOutput.display("Enter Number of Correct Answers:");

        try {


            int inputNum = Integer.parseInt(consoleInput.getInput());
            if(inputNum > getNumOfChoices())
                throw new IllegalArgumentException();

            setNumOfCorrectAnswers(inputNum);

            // TODO: 10/29/17 FIX THE SET ANSWER
            for (int i = 0; i < getNumOfCorrectAnswers() ; i++) {
                answers = new StringChoiceResponse();
                consoleOutput.display("Enter Answer #"+ (i + 1) + ":");
                consoleInput.getInput();
            }

        }

        catch (IllegalArgumentException e) {
            consoleOutput.display("Can Not Have More Correct Answers Than Choices");
            setAnswer();

        }

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
