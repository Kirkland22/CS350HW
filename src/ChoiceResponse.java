import java.io.Serializable;

/**
 * Created by Kirkland on 10/27/17.
 */
public abstract class ChoiceResponse<T> implements Serializable {

    private int timesChosen = 0;

    private T response;

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public abstract void display();

    public int getTimesChosen() {
        return timesChosen;
    }

    public void addTimeChosen() {
        timesChosen = timesChosen + 1;
    }
}
