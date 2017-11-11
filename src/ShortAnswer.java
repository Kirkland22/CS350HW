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
    public void tabulate() {

        tabulateHashMap.forEach((k,v) -> consoleOutput.displayTwoColumn((String)k , ((Integer)v).toString() ));

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
    protected void editChoices() {
        consoleOutput.display("Short Answer Question - Can only change Prompt or Answer");
    }

    @Override
    protected void SurveyTake() {

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
