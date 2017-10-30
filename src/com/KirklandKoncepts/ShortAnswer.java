package com.KirklandKoncepts;

/**
 * Created by Kirkland on 10/28/17.
 */
public class ShortAnswer extends Question {

    public ShortAnswer() {
        setQuestionType("Short Answer");
    }

    @Override
    public void setAnswer() {

        display();
        consoleOutput.display("Enter the answer for the Short Answer");
        ChoiceResponse<String> answer;
        answer = new StringChoiceResponse();
        answer.setResponse(consoleInput.getInput());
        addAnswer(answer);

    }

    @Override
    public void create() {
        getPromptFromUser();
    }

    @Override
    public void display() {
        getPrompt().display();

    }

}
