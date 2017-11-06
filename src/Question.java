import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Kirkland on 10/21/17.
 */
public abstract class Question implements Serializable {

protected ConsoleInput consoleInput;
protected ConsoleOutput consoleOutput;
private Prompt prompt;

private ArrayList<ChoiceResponse> questionChoices = new ArrayList<>();
private ArrayList<ChoiceResponse> correctAnswers = new ArrayList<>();

private int numOfCorrectAnswers;
private int numOfChoices;
private boolean canEditChoices = true;
private String questionType;
private String PROMPT_QUESTION = "Enter the prompt or ";
private String EDIT_PROMPT_QUESTION = "Enter the new prompt";
private String editing_prompt = "Which would you like to edit?";
private String[] editing_Options = {"Prompt" , "Choices","Answer(s)"};
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

    public void edit() {

        display();

        int editing_index = 2;

    // Gives options to edit Answers if they exist,(only for Test)
    if (!correctAnswers.isEmpty())
        editing_index = 3;

    consoleOutput.display(editing_prompt);
    for (int i = 0; i < editing_index ; i++) {
            consoleOutput.display((i + 1)+") " + editing_Options[i]);
        }


        String choice = consoleInput.getInput();

        switch (choice) {

            case "1":
                editPrompt();
                break;
            case "2":
                editChoices();
                break;
            case "3":

                if (correctAnswers.isEmpty()) {
                    edit();
                    break;
                }
                else {
                    editAnswer();
                    break;
                }

            default:
                edit();
                break;
        }
    }



    protected void editPrompt() {
        consoleOutput.display("Old Prompt: " + prompt.getPrompt());
        consoleOutput.display(EDIT_PROMPT_QUESTION);
        prompt.setPrompt(consoleInput.getInput());
        consoleOutput.display("New Prompt: " + prompt.getPrompt());

    }

    protected void editChoices() {
        ArrayList<ChoiceResponse> choices = getQuestionChoices();

        if (canEditChoices) {

            consoleOutput.display("Which choice would you like to edit?");
            for (int i = 0; i < choices.size(); i++) {

                consoleOutput.displayONELINE((i + 1) + ") ");
                choices.get(i).display();

            }

            try {
                Integer user_choice = consoleInput.getIntegerInput();
                ChoiceResponse choice = choices.get(user_choice - 1);

                consoleOutput.displayONELINE("Old Choice: ");
                choice.display();

                consoleOutput.display("Enter New Choice: ");
                String newChoice = consoleInput.getInput();

                choice.setResponse(newChoice);

            } catch (NumberFormatException e) {
                consoleOutput.display("Input a Number");
                edit();
            } catch (IndexOutOfBoundsException e) {
                consoleOutput.display("Not a valid question choice");
                edit();
            }
        }

        else {
            consoleOutput.display("Can not change choices for this question\n");
        }

    }

    protected void editAnswer() {

        ArrayList<ChoiceResponse> answers = getCorrectAnswers();

        consoleOutput.display("Which answer would you like to edit?");
        for (int i = 0; i < answers.size(); i++) {

            consoleOutput.displayONELINE((i + 1) + ") ");
            answers.get(i).display();

        }

        try {
            Integer user_choice = consoleInput.getIntegerInput();
            ChoiceResponse answer = answers.get(user_choice - 1);

            consoleOutput.displayONELINE("Old Choice: ");
            answer.display();

            consoleOutput.display("Enter New Choice: ");
            String newChoice = consoleInput.getInput();

            answer.setResponse(newChoice);

        } catch (NumberFormatException e) {
            consoleOutput.display("Input a Number");
            edit();
        } catch (IndexOutOfBoundsException e) {
            consoleOutput.display("Not a valid question answer");
            edit();
        }
    }
    // ABSTRACT METHOD ///
    public abstract void display();
    public abstract void setAnswer();
    ///////////////////////////////


    // Helper Methods

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

    public ArrayList<ChoiceResponse> getQuestionChoices() {
        return questionChoices;
    }

    public void addChoice(ChoiceResponse choiceResponse) {

        questionChoices.add(choiceResponse);

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

    public void setCanEditChoices(boolean canEditChoices) { this.canEditChoices = canEditChoices;}
}
