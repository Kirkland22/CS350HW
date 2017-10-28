package com.KirklandKoncepts;

import java.util.ArrayList;

/**
 * Created by Kirkland on 10/21/17.
 */
public abstract class Question {

protected ConsoleInput consoleInput;
protected ConsoleOutput consoleOutput;

private Prompt prompt;

protected ArrayList<AnswerResponse> answers = new ArrayList<>();

private final String PROMPT_QUESTION = "What is the question?";
private String[] mutlipleChoiceOptions = {"A","B","C","D","E","F","G"};

    public Question() {
        consoleInput = new ConsoleInput();
        consoleOutput = new ConsoleOutput();
        prompt = new StringPrompt();
        getPromptFromUser();

    }


    private void getPromptFromUser() {

        consoleOutput.display(PROMPT_QUESTION);
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

    public abstract void create();

    public abstract void display();


}
