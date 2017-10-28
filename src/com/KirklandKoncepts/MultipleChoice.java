package com.KirklandKoncepts;

/**
 * Created by Kirkland on 10/21/17.
 */
public class MultipleChoice extends Question {

    private int numOfCorrectAnswers;
    private int numOfOptions;
    private String numOfOptionsString = "How many multiple choice options would you like?";
    private String addingOptionsString = "Enter Answer #";



    public MultipleChoice() {
        setQuestionType("Multiple Choice Question");
    }

    @Override
    public void create() {
        getPromptFromUser();

        AnswerResponse<String> answer;
        // Get Number of options
       try {
            consoleOutput.display(numOfOptionsString);
            numOfOptions = Integer.parseInt(consoleInput.getInput());


            for (int i = 0; i < numOfOptions; i++) {
                answer = new StringAnswerResponse();
                consoleOutput.display(addingOptionsString + (i+1) + ":");
                answer.setResponse(consoleInput.getInput());
                addAnswer(answer);
            }
        }
        catch (Exception e) {
           create();

      }

    }

    public void setNumOfCorrectAnswers(int numOfCorrectAnswers) {
        this.numOfCorrectAnswers = numOfCorrectAnswers;
    }

    public void setNumOfOptions(int numOfOptions) {
        this.numOfOptions = numOfOptions;
    }

    @Override
    public void display() {

        getPrompt().display();
        for (int i = 0; i < answers.size(); i++) {

            consoleOutput.displayONELINE(getMutlipleChoiceOptions()[i] + ") " + answers.get(i).getResponse() + "\n");
        }

        consoleOutput.displayONELINE("\n");
    }
}
