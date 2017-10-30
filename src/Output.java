import java.io.Serializable;

/**
 * Created by Kirkland on 10/21/17.
 */
public abstract class Output<T>  implements Serializable {

    public abstract void display(T output);

    public abstract void display(T[] output);
}
