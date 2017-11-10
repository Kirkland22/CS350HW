

/**
 * Created by Kirkland on 10/28/17.
 */
public class TrueFalse extends MultipleChoice {


    public TrueFalse() {
        setQuestionType("True/False");
    }

    @Override
    public void setCorrectAnswers() {
        ChoiceResponse<String> answer;
        display();
        consoleOutput.display("Enter The Correct Answer (A or B):");

        try {
            answer = new StringChoiceResponse();
            String input = consoleInput.getInput();
            if ( (input.equalsIgnoreCase("a") || input.equalsIgnoreCase("b")) ) {

                answer.setResponse(input.toUpperCase());
                addAnswer(answer);
            }

            else
                throw new IllegalArgumentException();

        } catch (IllegalArgumentException e) {
            consoleOutput.display("Please choice A or B");
            setCorrectAnswers();


        }
    }

    @Override
    public void create() {
        getPromptFromUser();

        ChoiceResponse<String> trueChoice = new StringChoiceResponse();
        ChoiceResponse<String> falseChoice = new StringChoiceResponse();

        trueChoice.setResponse("True");
        falseChoice.setResponse("False");
        addChoice(trueChoice);
        addChoice(falseChoice);


    }

    @Override
    public void displayCorrectAnswer() {
        super.displayCorrectAnswer();
    }

    @Override
    public void display() {
        super.display();
    }


    @Override
    protected void editChoices() {
        consoleOutput.display("Can not edit choices for Multiple Choice Options");
    }
}
