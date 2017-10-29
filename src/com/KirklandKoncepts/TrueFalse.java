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

        ChoiceResponse<String> trueAnswer = new StringChoiceResponse();
        ChoiceResponse<String> falseAnswer = new StringChoiceResponse();

        trueAnswer.setResponse("True");
        falseAnswer.setResponse("False");
        addAnswer(trueAnswer);
        addAnswer(falseAnswer);


    }

    @Override
    public void display() {
        super.display();
    }
}
