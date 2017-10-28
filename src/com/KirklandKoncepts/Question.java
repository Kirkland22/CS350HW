package com.KirklandKoncepts;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Kirkland on 10/21/17.
 */
public abstract class Question implements Serializable {

protected ConsoleInput consoleInput;
protected ConsoleOutput consoleOutput;
private Prompt prompt;

protected ArrayList<AnswerResponse> answers = new ArrayList<>();

private String questionType;
private String PROMPT_QUESTION = "Enter the prompt or ";
private String[] mutlipleChoiceOptions = {"A","B","C","D","E","F","G"};

    public Question() {
        consoleInput = new ConsoleInput();
        consoleOutput = new ConsoleOutput();
        prompt = new StringPrompt();

    }

    // ABSTRACT METHODS ///
    public abstract void create();

    public abstract void display();
    ///////////////////////////////


    public void getPromptFromUser() {

        consoleOutput.display((PROMPT_QUESTION + questionType));
        prompt.setPrompt(consoleInput.getInput());
    }

    public ArrayList<AnswerResponse> getAnswers() {
        return answers;
    }

    public void addAnswer(AnswerResponse answerResponse) {

        answers.add(answerResponse);

    }

    public Prompt getPrompt() {
        return prompt;
    }

    public String[] getMutlipleChoiceOptions() {
        return mutlipleChoiceOptions;
    }

    public String getQuestionType() {


        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }
}
