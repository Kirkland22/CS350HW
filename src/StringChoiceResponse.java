/**
 * Created by Kirkland on 10/27/17.
 */
public class StringChoiceResponse extends ChoiceResponse {

    private boolean correctAnswer = false;
    @Override
    public Object getResponse() {
        return super.getResponse();
    }

    @Override
    public void setResponse(Object response) {
        String string = (String) response;
        super.setResponse(string);
    }

    @Override
    public void display() {
        String string = (String) getResponse();
        System.out.println(string);
    }


    public boolean isCorrectAnswer() {
        return correctAnswer;
    }


    public void setCorrectAnswer(boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
