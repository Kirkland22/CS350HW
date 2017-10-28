package com.KirklandKoncepts;

import java.util.List;

/**
 * Created by Kirkland on 10/21/17.
 */
public class ConsoleOutput extends Output {

    @Override
    public void display(Object output) {
        String string = (String)output;
        System.out.println(string);

    }

    public void displayONELINE(String string) {
        System.out.print(string);
    }

    @Override
    public void display(Object[] output) {
        String[] strings = (String[]) output;

        for (int i = 0; i < strings.length ; i++) {
            System.out.println(strings[i]);

        }

    }
}
