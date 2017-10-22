package com.KirklandKoncepts;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kirkland on 10/21/17.
 */
public class Survey implements Serializable {

    private static ConsoleInput consoleInput = new ConsoleInput();
    private static ConsoleOutput consoleOutput = new ConsoleOutput();
    private static String surveyNamePrompt = "Enter name of new Survey:";
    private static String[] addQuestionPrompt = {"1) Add a new T/F question","2) Add a new multiple choice question","3) Add a new short answer question","4) Add a new essay question","5)Add a new ranking question " , "6)Add a new multiple choice question "};

    private String name;
    private List<Question> questions = new ArrayList<>();

    public Survey() {
        consoleOutput.display(surveyNamePrompt);
        setName( consoleInput.getInput() );
    }



    public void save() {

        try {
            FileOutputStream fileOut = new FileOutputStream("/Users/Kirkland/Desktop/" + getName() + ".survey");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
            System.out.println("Survey is saved");
        }catch(IOException i) {
            i.printStackTrace();
        }

    }


    public void create() {

        Question q = createQuestion();
        questions.add( q );


    }

    // TODO: 10/21/17 Figure out creation of Questions
    private Question createQuestion () {

 /*       Question question = null;

        consoleOutput.display(addQuestionPrompt);
        String choice = consoleInput.getInput();

        switch (choice) {

            case "1":
                question = new MultipleChoice();
                break;

        }
        */

    return null;
    }


    protected void setName(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }
}
