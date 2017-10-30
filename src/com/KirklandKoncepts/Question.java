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

private ArrayList<ChoiceResponse> choiceResponses = new ArrayList<>();
private ArrayList<ChoiceResponse> correctAnswers = new ArrayList<>();

private int numOfCorrectAnswers;
private int numOfChoices;
private String questionType;
private String PROMPT_QUESTION = "Enter the prompt or ";
private String[] strings = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
private ArrayList<String> multipleChoiceOptions = new ArrayList<>();


    public Question() {
        consoleInput = new ConsoleInput();
        consoleOutput = new ConsoleOutput();
        prompt = new StringPrompt();
        for (int i = 0; i < strings.length ; i++) {

            multipleChoiceOptions.add(strings[i]);
        }

    }


    public void create() {
        getPromptFromUser();
        getChoices();
    }


    private void getChoices() {

        ChoiceResponse<String> choice;
        // Get Number of options
        try {
            consoleOutput.display("How many " + questionType + " choices would you like?");
            numOfChoices = Integer.parseInt(consoleInput.getInput());

            if (numOfChoices < 2)
                throw new IllegalArgumentException();

            for (int i = 0; i < numOfChoices; i++) {
                choice = new StringChoiceResponse();
                consoleOutput.display("Enter Choice #"+ (i + 1) + ":");
                choice.setResponse(consoleInput.getInput());
                addChoice(choice);
            }
        } catch (NumberFormatException e) {
            consoleOutput.display("Enter a number");
            getChoices();
        } catch (IllegalArgumentException e) {
            consoleOutput.display("Must have more than one choice");
            getChoices();

        } catch (Exception e) {
            e.printStackTrace();
            getChoices();

        }
    }


    // ABSTRACT METHOD ///
    public abstract void display();
    public abstract void setAnswer();
    ///////////////////////////////


    public void displayCorrectAnswer() {

        ArrayList<ChoiceResponse> correct = getCorrectAnswers();

        consoleOutput.display("Correct Answer(s):");
        for (int i = 0; i < correct.size(); i++) {
            String ans = (String) correct.get(i).getResponse();
            consoleOutput.display(ans);
        }

        consoleOutput.displayONELINE("\n");
    }



    public void getPromptFromUser() {

        consoleOutput.display((PROMPT_QUESTION + questionType + " Question"));
        prompt.setPrompt(consoleInput.getInput());
    }

    public ArrayList<ChoiceResponse> getChoiceResponses() {
        return choiceResponses;
    }

    public void addChoice(ChoiceResponse choiceResponse) {

        choiceResponses.add(choiceResponse);

    }

    public void addAnswer(ChoiceResponse answer) {

        correctAnswers.add(answer);
    }

    // Getters
    public Prompt getPrompt() {
        return prompt;
    }

    public ArrayList<String> getMultipleChoiceOptions() {
        return multipleChoiceOptions;
    }

    public String getQuestionType() {


        return questionType;
    }

    public int getNumOfChoices() {
        return numOfChoices;
    }

    public int getNumOfCorrectAnswers() {
        return numOfCorrectAnswers;
    }

    public ArrayList<ChoiceResponse> getCorrectAnswers() {
        return correctAnswers;
    }

    // Setters

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public void setNumOfCorrectAnswers(int numOfCorrectAnswers) {
        this.numOfCorrectAnswers = numOfCorrectAnswers;
    }

    public void setNumOfChoices(int numOfChoices) {
        this.numOfChoices = numOfChoices;
    }
}
