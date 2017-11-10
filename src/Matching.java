import java.util.ArrayList;

/**
 * Created by Kirkland on 10/28/17.
 */
public class Matching extends Question {

    private ArrayList<ChoiceResponse> rightSideChoices = new ArrayList<>();

    public Matching() {
        setQuestionType("Matching");
    }


    @Override
    public void create() {

    getPromptFromUser();
    getChoices();

    }

    @Override
    public void setCorrectAnswers() {
        clearCorrectAnswers();
        display();

        boolean isValidChoice;
        consoleOutput.display("Select choices of right column that matches left column in descending order:");

        for (int i = 0; i < getNumOfChoices() ; i++) {

            isValidChoice = false;
            while (!isValidChoice) {
                ChoiceResponse<String> tempAnswer = new StringChoiceResponse();

                consoleOutput.display("What number matches " + getMultipleChoiceOptions().get(i));
                try {
                    Integer input = Integer.parseInt(consoleInput.getInput());

                    if (input > getNumOfChoices()) {
                        throw new IllegalArgumentException();
                    }

                    if (wasAnswerPicked(input.toString() , getCorrectAnswers() )) {
                        throw new SetSameAnswerTwiceException();
                    }


                    tempAnswer.setResponse(input.toString());
                    addAnswer(tempAnswer);
                    isValidChoice = true;

                }

                catch (IllegalArgumentException e) {
                    consoleOutput.display("Not a valid option");
                }

                catch (SetSameAnswerTwiceException e) {
                    consoleOutput.display("Set same answer twice");
                }
                catch (Exception e) {
                    e.printStackTrace();
                }


            }

        }
    }

    @Override
    public void display() {

        getPrompt().display();
        ArrayList<ChoiceResponse> leftSideChoices = getQuestionChoices();

        for (int i = 0; i < leftSideChoices.size(); i++) {

            String columnOne = getMultipleChoiceOptions().get(i) + ") " + leftSideChoices.get(i).getResponse();
            String columnTwo = (i+1) + ") " + rightSideChoices.get(i).getResponse();
            consoleOutput.displayTwoColumn(columnOne,columnTwo);
        }

        consoleOutput.displayOneLine("\n");

    }

    @Override
    public void displayCorrectAnswer() {
        ArrayList<ChoiceResponse> correctRightSide = getCorrectAnswers();
        ArrayList<String> leftSide = getMultipleChoiceOptions();


        consoleOutput.display("Correct Answer(s):");
        for (int i = 0; i < correctRightSide.size(); i++) {
            String ans = (String) correctRightSide.get(i).getResponse();
            consoleOutput.displayTwoColumn(leftSide.get(i),ans);
        }

        consoleOutput.displayOneLine("\n");
    }

    @Override
    protected void editChoices() {

        consoleOutput.display("Which Column would you like to edit?");
        consoleOutput.display("1) Left");
        consoleOutput.display("2) Right");
        String input =  consoleInput.getInput();

        switch (input) {
            case "1":
                editLeftSide();
                break;
            case "2":
                editRightSide();
                break;
            default:
                editChoices();
                break;
        }

    }

    @Override
    protected void editAnswer() {
        setCorrectAnswers();
    }

    @Override
    protected void SurveyTake() {
        clearUserAnswers();
        display();


        consoleOutput.display("Select choices of right column that matches left column in descending order:");
        int numberOfChoices =  getQuestionChoicesSize();
        for (int i = 0; i < numberOfChoices ; i++) {


                ChoiceResponse<String> tempAnswer = new StringChoiceResponse();

                consoleOutput.display("What number matches " + getMultipleChoiceOptions().get(i));
                try {

                    Integer input = Integer.parseInt(consoleInput.getInput());

                    if (input > numberOfChoices) {
                        throw new IllegalArgumentException();
                    }

                    if (wasAnswerPicked(input.toString() , getUserAnswers() )) {
                        throw new SetSameAnswerTwiceException();
                    }


                    tempAnswer.setResponse(input.toString());
                    userAnswers.add(tempAnswer);

                }

                catch (IllegalArgumentException e) {
                    consoleOutput.display("Not a valid option");
                    SurveyTake();
                }

                catch (SetSameAnswerTwiceException e) {
                    consoleOutput.display("Cant Match Same option twice");
                    SurveyTake();
                }

            }

    }


    private void getChoices() {

        // Get Number of options
        try {
            consoleOutput.display("How many " + getQuestionType() + " choices would you like?");
            setNumOfChoices(Integer.parseInt(consoleInput.getInput()));

            if (getNumOfChoices() < 2)
                throw new IllegalArgumentException();

            getLeftSideAnswersFromUser();
            getRightSideAnswersFromUser();


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

    private void editLeftSide() {
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

    private void editRightSide() {
        for (int i = 0; i < rightSideChoices.size(); i++) {

            consoleOutput.displayOneLine((i + 1) + ") ");
            rightSideChoices.get(i).display();
        }

        try {
            Integer user_choice = consoleInput.getIntegerInput();
            ChoiceResponse choice = rightSideChoices.get(user_choice - 1);

            consoleOutput.displayOneLine("Old Choice: ");
            choice.display();

            consoleOutput.display("Enter New Choice: ");
            String newChoice = consoleInput.getInput();

            choice.setResponse(newChoice);

        } catch (NumberFormatException e) {
            consoleOutput.display("Input a Number");
            editRightSide();
        } catch (IndexOutOfBoundsException e) {
            consoleOutput.display("Not a valid question choice");
            editRightSide();
        }
    }

    private void getLeftSideAnswersFromUser() {

        ChoiceResponse<String> choice_Column1;
        // Gets Choices for the Left Side of the Matching Questions
        for (int i = 0; i < getNumOfChoices(); i++) {
            choice_Column1 = new StringChoiceResponse();
            consoleOutput.display("(Left Column) Enter Choice #"+ (i + 1) + ":");
            choice_Column1.setResponse(consoleInput.getInput());
            addChoice(choice_Column1);
        }
    }

    private void getRightSideAnswersFromUser() {

        // Gets Choices for the Right Side of the Matching Questions
        for (int i = 0; i < getNumOfChoices(); i++) {
            ChoiceResponse<String> choice_Column2;
            choice_Column2 = new StringChoiceResponse();
            consoleOutput.display("(Right Column) Enter Choice #"+ (i + 1) + ":");
            choice_Column2.setResponse(consoleInput.getInput());
            rightSideChoices.add(choice_Column2);
        }
    }


}
