import java.util.ArrayList;

/**
 * Created by Kirkland on 10/28/17.
 */
public class Ranking extends Question {


    public Ranking() {
        setQuestionType("Ranking");
    }


    // Gets the Correct Answers for Ranking
    @Override
    public void setAnswer() {
        display();
        consoleOutput.display("Enter the correct order");
        ChoiceResponse<String> answer;
        ArrayList<String> choices = new ArrayList<>();
        String input;


        try {
            for (int i = 0; i < getNumOfCorrectAnswers(); i++) {
                choices.add(getMultipleChoiceOptions().get(i));

            }

            for (int i = 0; i < getNumOfChoices(); i++) {
                answer = new StringChoiceResponse();
                consoleOutput.display("Enter Rank Order #" + (i + 1) + ":");

                input = consoleInput.getInput().toUpperCase();

                if (!choices.contains(input))
                    throw new IllegalStateException();

                answer.setResponse(input);
                addAnswer(answer);
            }
        }

        catch (IllegalStateException e) {
            consoleOutput.display("Not an Valid Answer");
            setAnswer();
        }
    }

    @Override
    public void display() {
        ArrayList<ChoiceResponse> leftSideChoices = getChoiceResponses();
        getPrompt().display();
        for (int i = 0; i < leftSideChoices.size(); i++) {
            consoleOutput.displayONELINE(getMultipleChoiceOptions().get(i) + ") " + leftSideChoices.get(i).getResponse() + "\n");
        }
        consoleOutput.displayONELINE("\n");
    }



}
