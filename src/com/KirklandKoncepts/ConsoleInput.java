package com.KirklandKoncepts;

import java.util.Scanner;

/**
 * Created by Kirkland on 10/21/17.
 */


// TODO: 10/21/17 Figure out how to call scan.close() w/o closing System.in
public class ConsoleInput extends Input {

    private Scanner scan = new Scanner(System.in);

    @Override
    public String getInput() {
        return scan.nextLine();
    }


}
