import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kirkland on 10/28/17.
 */
public class ShortAnswer extends Question {

    HashMap tabulateHashMap = getTabulationHashMap();

    public ShortAnswer() {
        setQuestionType("Short Answer");
    }

    @Override
    public void setCorrectAnswers() {

        display();
        consoleOutput.display("Enter the answer for the Short Answer");
        ChoiceResponse<String> answer;
        answer = new StringChoiceResponse();
        answer.setResponse(consoleInput.getInput());
        addAnswer(answer);

    }

    @Override
    public void create() {
        getPromptFromUser();
    }

    @Override
    protected void editAnswer() {
        ChoiceResponse answer = getCorrectAnswers().get(0);
        consoleOutput.displayOneLine("Old Choice: ");
        answer.display();

        consoleOutput.display("Enter New Choice: ");
        String newChoice = consoleInput.getInput();

        answer.setResponse(newChoice);
    }

    @Override
    protected int grade() {
        int numberCorrect = 0;

        for (int i = 0; i < getNumOfCorrectAnswers(); i++) {

            if (getUserAnswers().get(i).equals(getCorrectAnswers().get(i)))
            {
                numberCorrect++;
            }
        }

        if (numberCorrect == getNumOfCorrectAnswers())
            return 1;
        else
            return 0;
    }

    @Override
    protected void editChoices() {
        consoleOutput.display("Short Answer Question - Can only change Prompt or Answer");
    }

    @Override
    protected void take() {

        display();
        ChoiceResponse<String> ans = new StringChoiceResponse();
        String input = consoleInput.getInput();
        addTimesChosen(input);
        ans.setResponse(input);
        userAnswers.add(ans);
    }

    @Override
    public void display() {
        getPrompt().display();

    }

    @Override
    public void addTimesChosen(String input) {

        if (tabulateHashMap.containsKey(input))
        {
            tabulateHashMap.put(input, (Integer)tabulateHashMap.get(input) + 1);
        }

        else {
            tabulateHashMap.put(input,1);
        }
    }
}
