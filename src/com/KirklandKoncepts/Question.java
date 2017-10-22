package com.KirklandKoncepts;

/**
 * Created by Kirkland on 10/21/17.
 */
public class Question {

protected ConsoleInput consoleInput;
protected ConsoleOutput consoleOutput;
protected int numOfChoices;

    public Question() {
        consoleInput = new ConsoleInput();
        consoleOutput = new ConsoleOutput();
    }


    public void setNumOfChoices(int numOfChoices) {
        this.numOfChoices = numOfChoices;
    }
}
