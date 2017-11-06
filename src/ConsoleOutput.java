/**
 * Created by Kirkland on 10/21/17.
 */
public class ConsoleOutput extends Output {

    @Override
    public void display(Object output) {
        String string = (String)output;
        System.out.println(string);

    }

    @Override
    public void display(Object[] output) {
        String[] strings = (String[]) output;

        for (int i = 0; i < strings.length ; i++) {
            System.out.println(strings[i]);

        }

    }

    public void displayOneLine(String string) {
        System.out.print(string);
    }

    public void displayTwoColumn(String col1, String col2) {
        System.out.printf("%-10s %-10s\n", col1, col2);
    }




}
