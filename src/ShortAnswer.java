/**
 * Created by Kirkland on 10/28/17.
 */
public class ShortAnswer extends Question {

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
    protected void editChoices() {
        consoleOutput.display("Short Answer Question - Can only change Prompt or Answer");
    }

    @Override
    protected void SurveyTake() {

        display();
        ChoiceResponse<String> ans = new StringChoiceResponse();
        String input = consoleInput.getInput();
        ans.setResponse(input);
    }

    @Override
    public void display() {
        getPrompt().display();

    }

}
