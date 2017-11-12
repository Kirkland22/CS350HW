/**
 * Created by Kirkland on 10/28/17.
 */
public class TrueFalse extends MultipleChoice {


    public TrueFalse() {
        setQuestionType("True/False");
        tabulateHashMap.put("True",0);
        tabulateHashMap.put("False",0);

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
    protected int grade() {

        if (getUserAnswers().get(0).equals(getCorrectAnswers().get(0)))
            return 1;
        else
            return 0;
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
    public void addTimesChosen(String input) {

        if (input.equals("A"))
        {
            tabulateHashMap.put("True", (Integer)tabulateHashMap.get("True") + 1);
        }

        else {
            tabulateHashMap.put("False", (Integer)tabulateHashMap.get("False") + 1);
        }
    }

    @Override
    public void tabulate() {

            Integer trueCount = (Integer)tabulateHashMap.get("True");
            Integer falseCount = (Integer)tabulateHashMap.get("False");
            consoleOutput.displayTwoColumn("True" , trueCount.toString());
            consoleOutput.displayTwoColumn("False" , falseCount.toString());

    }

    @Override
    protected void editChoices() {
        consoleOutput.display("Can not edit choices for TF Options");
    }

    @Override
    protected void editAnswer() {
        super.editAnswer();
    }
}
