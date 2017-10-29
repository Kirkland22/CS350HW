package com.KirklandKoncepts;

/**
 * Created by Kirkland on 10/28/17.
 */
public class TrueFalse extends MultipleChoice {


    public TrueFalse() {
        setQuestionType("True/False");
    }


    @Override
    public void create() {
        getPromptFromUser();

        ChoiceResponse<String> trueChoice = new StringChoiceResponse();
        ChoiceResponse<String> falseChoice = new StringChoiceResponse();

        trueChoice.setResponse("True");
        falseChoice.setResponse("False");
        addChoice(trueChoice);
        addChoice(falseChoice);


    }

    @Override
    public void displayCorrectAnswer() {
        super.displayCorrectAnswer();
    }

    @Override
    public void display() {
        super.display();
    }
}
