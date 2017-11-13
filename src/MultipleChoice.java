import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by Kirkland on 10/21/17.
 */
public class MultipleChoice extends Question {

    HashMap tabulateHashMap = getTabulationHashMap();

    public MultipleChoice() {
        setQuestionType("Multiple Choice");
    }

    @Override
    protected void take() {

        if (getNumOfCorrectAnswers() > 1 ) {
            multipleAnswerTake();
        }

        else {
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
            } catch (IllegalStateException e) {
                consoleOutput.display("Not a Valid Answer");
                take();
            }
        }
    }

    @Override
    protected int grade() {
        int numberCorrect = 0;

        for (int i = 0; i < getUserAnswers().size(); i++) {

            for (int j = 0; j <getCorrectAnswers().size() ; j++) {

                if (getUserAnswers().get(i).equals(getCorrectAnswers().get(j)))
                {
                    numberCorrect++;
                    break;
                }
            }

        }

        if (numberCorrect == getNumOfCorrectAnswers())
            return 1;

        return 0 ;
    }

    // Gets the Correct Answers for MC
    @Override
    public void setCorrectAnswers() {

        display();
        getNumOfCorrectAnswersFromUser();
        getCorrectAnswersFromUser();

    }

    @Override
    public void display() {

        ArrayList<ChoiceResponse> leftSideChoices = getQuestionChoices();

        getPrompt().display();
        for (int i = 0; i < leftSideChoices.size(); i++) {

            consoleOutput.displayOneLine(getMultipleChoiceOptions().get(i) + ") " + leftSideChoices.get(i).getResponse() + "\n");
        }

        consoleOutput.displayOneLine("\n");
    }

    // Override to Tabulate in ABC order
    @Override
    public void tabulate() {

        if (getCorrectAnswers().size() > 1) {
            super.tabulate();
        }

        else {
            consoleOutput.displayTwoColumn("Choice","Times Chosen");
            for (int i = 0; i < getQuestionChoicesSize(); i++) {
                String abc = getMultipleChoiceOptions().get(i);
                Integer count = (Integer) tabulateHashMap.get(abc);
                if (count == null) {
                    count = 0;
                }

                consoleOutput.displayTwoColumn(abc, count.toString());
            }
        }
    }

    protected void multipleAnswerTake() {
        ArrayList<String> multipleChoices = new ArrayList<>();
        ArrayList<String> mcAnswers = new ArrayList<>();
        clearUserAnswers();
        display();

        String mcOrder = "";

        consoleOutput.display("Please give " + getNumOfCorrectAnswers()  + " choices: ");

        try {

            // Gets Multiple choice options for given question -- Returns A -> D if question has 4 options
            for (int i = 0; i < getNumOfChoices(); i++) {
                multipleChoices.add(getMultipleChoiceOptions().get(i));
            }

            // Get User Answers
            for (int i = 0; i < getNumOfCorrectAnswers(); i++) {


                ChoiceResponse<String> ans = null;
                ans = new StringChoiceResponse();
                consoleOutput.display("Enter Answer #" + (i + 1) + ":");
                String input = consoleInput.getInput().toUpperCase();

                if (!multipleChoices.contains(input))
                    throw new IllegalStateException();
                if (wasAnswerPicked(input, userAnswers))
                    throw new SetSameAnswerTwiceException();


                mcAnswers.add(input);
                ans.setResponse(input);
                userAnswers.add(ans);
            }
            Collections.sort(mcAnswers);


            for (String s: mcAnswers) {
                mcOrder = mcOrder + s + ",";
            }

            // Strip trailing ','
            mcOrder = mcOrder.substring(0 , mcOrder.length()-1);
            addTimesChosen(mcOrder);

        } catch (SetSameAnswerTwiceException e) {
            consoleOutput.display("Multiple choice can only be used once");
            multipleAnswerTake();
        } catch (IllegalStateException e) {
            consoleOutput.display("Not a Valid Answer");
            multipleAnswerTake();
        }
    }

    private void getCorrectAnswersFromUser() {
        String input = null;
        ArrayList<String> multipleChoices = new ArrayList<>();
        clearCorrectAnswers();
        try {

            // Gets Multiple choice options for given question -- Returns A -> D if question has 4 options
            for (int i = 0; i < getNumOfChoices(); i++) {
                multipleChoices.add(getMultipleChoiceOptions().get(i));
            }

            // Get Correct Answers
            for (int i = 0; i < getNumOfCorrectAnswers(); i++) {



                ChoiceResponse<String> answers = null;
                answers = new StringChoiceResponse();
                consoleOutput.display("Enter Answer #" + (i + 1) + ":");
                input = consoleInput.getInput().toUpperCase();

                if (!multipleChoices.contains(input))
                    throw new IllegalStateException();
                if (wasAnswerPicked(input,getCorrectAnswers()))
                    throw new SetSameAnswerTwiceException();

                answers.setResponse(input);
                addAnswer(answers);
            }
        }

        catch (SetSameAnswerTwiceException e) {
            consoleOutput.display("Multiple choice can only be used once");
            getCorrectAnswersFromUser();
        }
        catch (IllegalStateException e) {
            consoleOutput.display("Not a Valid Answer");
            getCorrectAnswersFromUser();
        }
    }

    private void getNumOfCorrectAnswersFromUser() {
        consoleOutput.display("Enter Number of Correct Answers:");
        try {

            int inputNum = Integer.parseInt(consoleInput.getInput());

            if (inputNum > getNumOfChoices())
                throw new IllegalArgumentException();
            setNumOfCorrectAnswers(inputNum);

        } catch (IllegalStateException e) {
            consoleOutput.display("Not a Valid Answer");
            getNumOfCorrectAnswersFromUser();
        } catch (IllegalArgumentException e) {
            consoleOutput.display("Can Not Have More Correct Answers Than Choices");
            getNumOfCorrectAnswersFromUser();
        }
    }

}


