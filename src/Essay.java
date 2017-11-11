/**
 * Created by Kirkland on 10/28/17.
 */
public class Essay extends ShortAnswer {


    public Essay() {
        setQuestionType("Essay");
    }


    @Override
    public void setCorrectAnswers() {

    }

    @Override
    protected void editChoices() {
        consoleOutput.display("Essay Question - Can only change Prompt");
    }

    @Override
    protected void editAnswer() {
        consoleOutput.display("Essay Question - Can only change Prompt");
    }

    @Override
    public void display() {
        getPrompt().display();
    }

    @Override
    public void create() {
        getPromptFromUser();

    }

    @Override
    public void tabulate() {

        for (int i = 0; i < getUserAnswers().size() ; i++) {
            consoleOutput.display(getUserAnswers().get(i).getResponse());
        }

    }
}
