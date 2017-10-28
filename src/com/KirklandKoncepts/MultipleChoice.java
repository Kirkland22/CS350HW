package com.KirklandKoncepts;

/**
 * Created by Kirkland on 10/21/17.
 */
public class MultipleChoice extends Question {

    private int numOfCorrectAnswers;
    private int numOfOptions;
    private String numOfOptionsPrompt = "How many multiple choice options would you like?";



    public MultipleChoice() {
        create();
    }

    @Override
    public void create() {

        // Get Number of options
        try {
            consoleOutput.display(numOfOptionsPrompt);
            Integer options = Integer.parseInt(consoleInput.getInput());

            for (int i = 0; i < options; i++) {

            }

        }
        catch (Exception e) {

        }

    }

    private void setNumOfCorrectAnswers(int numOfCorrectAnswers) {
        this.numOfCorrectAnswers = numOfCorrectAnswers;
    }

    private void setNumOfOptions(int numOfOptions) {
        this.numOfOptions = numOfOptions;
    }
}
