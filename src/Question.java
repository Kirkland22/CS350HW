import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Kirkland on 10/21/17.
 */
public abstract class Question implements Serializable {

    protected static ConsoleInput consoleInput = new ConsoleInput();
    protected static ConsoleOutput consoleOutput = new ConsoleOutput();
    private Prompt prompt;

    private HashMap<String,Integer> tabulationHashMap = new HashMap<>();
    private ArrayList<String> multipleChoiceOptions;
    private ArrayList<ChoiceResponse> questionChoices = new ArrayList<>();
    private ArrayList<ChoiceResponse> correctAnswers = new ArrayList<>();
    public ArrayList<ChoiceResponse> userAnswers = new ArrayList<>();

    private int numOfCorrectAnswers;
    private int numOfChoices;
    private String questionType;
    private String PROMPT_QUESTION = "Enter the prompt or ";
    private String EDIT_PROMPT_QUESTION = "Enter the new prompt";
    private String editing_prompt = "Which would you like to edit?";
    private String[] editing_Options = {"Prompt", "Choices", "Answer(s)"};
    private String[] strings = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};


    public Question() {
        prompt = new StringPrompt();
        multipleChoiceOptions = new ArrayList<>();
        for (int i = 0; i < strings.length; i++) {

            multipleChoiceOptions.add(strings[i]);
        }

    }

    /*** ABSTRACT METHODS ***/
    public abstract void display();

    public abstract void setCorrectAnswers();

    /***********************/



    public void create() {
        getPromptFromUser();
        getChoicesFromUser();

    }

    public void edit() {

        display();

        int editing_index = 2;

        // Gives options to edit Answers if they exist,(only for Test)
        if (!correctAnswers.isEmpty())
            editing_index = 3;

        consoleOutput.display(editing_prompt);
        for (int i = 0; i < editing_index; i++) {
            consoleOutput.display((i + 1) + ") " + editing_Options[i]);
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
                } else {
                    editAnswer();
                    break;
                }

            default:
                edit();
                break;
        }
    }

    public void tabulate() {
        tabulationHashMap.forEach((k,v) -> consoleOutput.displayTwoColumn((String)k, ((Integer)v).toString()));
    }

    protected void editPrompt() {
        consoleOutput.display("Old Prompt: " + prompt.getPrompt());
        consoleOutput.display(EDIT_PROMPT_QUESTION);
        prompt.setPrompt(consoleInput.getInput());
        consoleOutput.display("New Prompt: " + prompt.getPrompt());

    }

    protected void editChoices() {
        ArrayList<ChoiceResponse> choices = getQuestionChoices();


        consoleOutput.display("Which choice would you like to edit?");
        for (int i = 0; i < choices.size(); i++) {

            consoleOutput.displayOneLine((i + 1) + ") ");
            choices.get(i).display();
        }
        try {
            Integer user_choice = consoleInput.getIntegerInput();
            ChoiceResponse choice = choices.get(user_choice - 1);

            consoleOutput.displayOneLine("Old Choice: ");
            choice.display();

            consoleOutput.display("Enter New Choice: ");
            String newChoice = consoleInput.getInput();

            choice.setResponse(newChoice);

        } catch (NumberFormatException e) {
            consoleOutput.display("Input a Number");
            editChoices();
        } catch (IndexOutOfBoundsException e) {
            consoleOutput.display("Not a valid question choice");
            editChoices();
        }
    }

    protected void editAnswer() {

        ArrayList<ChoiceResponse> answers = getCorrectAnswers();
        ArrayList<String> multipleChoicesOptions = new ArrayList<>();

        consoleOutput.display("Which answer would you like to edit?");
        for (int i = 0; i < answers.size(); i++) {

            consoleOutput.displayOneLine((i + 1) + ") ");
            answers.get(i).display();

        }

        for (int i = 0; i < getQuestionChoicesSize(); i++) {

            multipleChoicesOptions.add(getMultipleChoiceOptions().get(i));

        }


        try {
            Integer user_choice = consoleInput.getIntegerInput();
            ChoiceResponse answer = answers.get(user_choice - 1);

            consoleOutput.displayOneLine("Old Choice: ");
            answer.display();

            consoleOutput.display("Enter New Choice: ");
            String newAnswer = consoleInput.getInput().toUpperCase();

            if (!multipleChoicesOptions.contains(newAnswer))
                throw new IllegalStateException();

            answer.setResponse(newAnswer);

        } catch (NumberFormatException e) {
            consoleOutput.display("Input a Number");
            edit();
        } catch (IndexOutOfBoundsException e) {
            consoleOutput.display("Not a valid question answer");
            edit();
        }
        catch (IllegalStateException e) {
            consoleOutput.display("Not a Valid Answer");
            edit();
        }
    }


    protected void SurveyTake() {

        ArrayList<String> multipleChoices = new ArrayList<>();
        clearUserAnswers();
        display();

        try {

            // Gets Multiple choice options for given question -- Returns A -> D if question has 4 options
            for (int i = 0; i < getQuestionChoicesSize(); i++) {
                multipleChoices.add(getMultipleChoiceOptions().get(i));
            }

                ChoiceResponse<String> ans = new StringChoiceResponse();
                String input = consoleInput.getInput().toUpperCase();

                if (!multipleChoices.contains(input))
                    throw new IllegalStateException();

                addTimesChosen(input);
                ans.setResponse(input);
                userAnswers.add(ans);
        }



        catch (IllegalStateException e) {
            consoleOutput.display("Not a Valid Answer");
            SurveyTake();
        }
    }

    /**** Helper Methods ****/



    // / Adds for the number of times chosen for tabulation
    public void addTimesChosen(String input) {

        if (tabulationHashMap.containsKey(input))
        {
            tabulationHashMap.put(input, (Integer)tabulationHashMap.get(input) + 1);
        }

        else {
            tabulationHashMap.put(input,1);
        }
    }

    public boolean wasAnswerPicked(String input, ArrayList<ChoiceResponse> answers) {

        for (int i = 0; i < answers.size(); i++) {

            if (answers.get(i).getResponse().equals(input))
                return true;
        }

        return false;

    }

    // Displays only the Prompt
    public void displayPrompt() {

        Prompt prompt = getPrompt();
        prompt.display();
    }

    //Displays the correct answer if test
    public void displayCorrectAnswer() {

        ArrayList<ChoiceResponse> correct = getCorrectAnswers();

        consoleOutput.display("Correct Answer(s):");
        for (int i = 0; i < correct.size(); i++) {
            String ans = (String) correct.get(i).getResponse();
            consoleOutput.display(ans);
        }

        consoleOutput.displayOneLine("\n");
    }

    // Get the Question Prompt from the AdminUser
    public void getPromptFromUser() {

        consoleOutput.display((PROMPT_QUESTION + questionType + " Question"));
        prompt.setPrompt(consoleInput.getInput());
    }

    // Gets Choices from AdminUser
    private void getChoicesFromUser() {

        ChoiceResponse<String> choice;
        // Get Number of options
        try {
            consoleOutput.display("How many " + questionType + " choices would you like?");
            numOfChoices = Integer.parseInt(consoleInput.getInput());

            if (numOfChoices < 2)
                throw new IllegalArgumentException();

            for (int i = 0; i < numOfChoices; i++) {
                choice = new StringChoiceResponse();
                consoleOutput.display("Enter Choice #" + (i + 1) + ":");
                choice.setResponse(consoleInput.getInput());
                addChoice(choice);
            }
        } catch (NumberFormatException e) {
            consoleOutput.display("Enter a number");
            getChoicesFromUser();
        } catch (IllegalArgumentException e) {
            consoleOutput.display("Must have more than one choice");
            getChoicesFromUser();

        } catch (Exception e) {
            e.printStackTrace();
            getChoicesFromUser();

        }
    }

    // Add Choice object to list of choices
    public void addChoice(ChoiceResponse choiceResponse) {

        questionChoices.add(choiceResponse);

    }

    // Add Answer object to list of answers
    public void addAnswer(ChoiceResponse answer) {

        correctAnswers.add(answer);
    }

    // Clears list of correct answers
    public void clearCorrectAnswers() {
        correctAnswers.clear();
    }

    // Clears list of User input answers
    public void clearUserAnswers() {userAnswers.clear();}



    // Getters
    public Prompt getPrompt() {
        return prompt;
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

    public int getQuestionChoicesSize() { return  questionChoices.size();}

    public ArrayList<ChoiceResponse> getCorrectAnswers() {
        return correctAnswers;
    }

    public ArrayList<String> getMultipleChoiceOptions() {
        return multipleChoiceOptions;
    }

    public ArrayList<ChoiceResponse> getQuestionChoices() {
        return questionChoices;
    }

    public ArrayList<ChoiceResponse> getUserAnswers() {
        return userAnswers;
    }

    public HashMap<String, Integer> getTabulationHashMap() {
        return tabulationHashMap;
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
