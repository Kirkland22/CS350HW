import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Kirkland on 10/28/17.
 */
public class Ranking extends Question {

    HashMap tabulateHashMap = getTabulationHashMap();

    public Ranking() {
        setQuestionType("Ranking");
    }


    @Override
    public void tabulate() {

        tabulateHashMap.forEach((k,v) -> consoleOutput.displayTwoColumn((String)k, ((Integer)v).toString()));
    }

    // Gets the Correct Answers for Ranking
    @Override
    public void setCorrectAnswers() {

        // Clears the list of correct answers in case it is full
        clearCorrectAnswers();

        display();
        consoleOutput.display("Enter the correct order");
        ChoiceResponse<String> answer;
        ArrayList<String> choices = new ArrayList<>();
        String input;


        try {
            for (int i = 0; i < getNumOfChoices(); i++) {
                choices.add(getMultipleChoiceOptions().get(i));

            }

            for (int i = 0; i < getNumOfChoices(); i++) {
                answer = new StringChoiceResponse();
                consoleOutput.display("Enter Rank Order #" + (i + 1) + ":");

                input = consoleInput.getInput().toUpperCase();

                if (!choices.contains(input))
                    throw new IllegalStateException();
                if (wasAnswerPicked(input,getCorrectAnswers()))
                    throw new SetSameAnswerTwiceException();

                answer.setResponse(input);
                addAnswer(answer);
            }
        } catch (IllegalStateException e) {
            consoleOutput.display("Not an Valid Answer");
            setCorrectAnswers();
        } catch (SetSameAnswerTwiceException e) {
            consoleOutput.display("Ranking choice can only be used once");
            setCorrectAnswers();
        }
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

    @Override
    protected void editAnswer() {
        setCorrectAnswers();
    }

    public void addTimesChosen(String input) {

        if (tabulateHashMap.containsKey(input))
        {
            tabulateHashMap.put(input, (Integer)tabulateHashMap.get(input) + 1);
        }

        else {
            tabulateHashMap.put(input,1);
        }
    }

    @Override
    protected void SurveyTake() {

        ArrayList<String> multipleChoices = new ArrayList<>();
        clearUserAnswers();
        display();
        //Stores rank order
        String rankOrder = "";
        try {

            // Gets Multiple choice options for given question -- Returns A -> D if question has 4 options
            for (int i = 0; i < getQuestionChoicesSize(); i++) {
                multipleChoices.add(getMultipleChoiceOptions().get(i));
            }

            // Get User Answers
            for (int i = 0; i < getQuestionChoicesSize(); i++) {

                ChoiceResponse<String> ans = new StringChoiceResponse();
                consoleOutput.display("Enter Rank Option #" + (i + 1) + ":");
                String input = consoleInput.getInput().toUpperCase();
                if (!multipleChoices.contains(input))
                    throw new IllegalStateException();
                // Check if same answer was used twice
                if (wasAnswerPicked(input, getUserAnswers()))
                    throw new SetSameAnswerTwiceException();

                //i.e rankOrder will be A,C,B,
                rankOrder = rankOrder + input + ",";
                ans.setResponse(input);
                userAnswers.add(ans);
            }

            //Trim off last ','
            rankOrder = rankOrder.substring(0,rankOrder.length()-1);

            addTimesChosen(rankOrder);


        } catch (SetSameAnswerTwiceException e) {
            consoleOutput.display("Rank choice can only be used once");
            SurveyTake();
        } catch (IllegalStateException e) {
            consoleOutput.display("Not a Valid Answer");
            SurveyTake();
        }
    }


}
