package com.KirklandKoncepts;

/**
 * Created by Kirkland on 10/21/17.
 */
public abstract class Question {

protected ConsoleInput consoleInput;
protected ConsoleOutput consoleOutput;

protected StringPrompt prompt;
private String promptQuestion = "What is the question?";
private String[] mutlipleChoiceOptions = {"A","B","C","D","E","F","G"};

    public Question() {
        consoleInput = new ConsoleInput();
        consoleOutput = new ConsoleOutput();
        getPromptFromUser();


    }


    public abstract void create();

    private void getPromptFromUser() {

        consoleOutput.display(promptQuestion);
        prompt.setPrompt(consoleInput.getInput());
    }

}
