package com.KirklandKoncepts;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kirkland on 10/21/17.
 */
public class Survey implements Serializable {

    private static ConsoleInput consoleInput = new ConsoleInput();
    private static ConsoleOutput consoleOutput = new ConsoleOutput();
    private  String surveyNamePrompt = "Please Enter Survey/Test Name:";
    private  static String pickSurveyToLoad = "Which survey would you like to load?";
    private  String[] addQuestionPrompt = {"1) Add a new T/F question","2) Add a new multiple choice question","3) Add a new short answer question","4) Add a new essay question","5) Add a new ranking question" , "6) Add a new multiple choice question "};

    private String name;
    private List<Question> questions = new ArrayList<>();

    public Survey() {
        consoleOutput.display(surveyNamePrompt);
        setName( consoleInput.getInput() );
    }



    public void save() {

        try {
            FileOutputStream fileOut = new FileOutputStream("/Users/Kirkland/Desktop/survey/" + getName());
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
            System.out.println("Survey is saved");
        }catch(IOException i) {
            i.printStackTrace();
        }

    }


    public static Survey load() {

        Survey survey = null;
        boolean pickedValidChoice = false;
        String choice;

        File folder = new File("/Users/Kirkland/Desktop/survey");

        // gets you the list of files at this folder
        File[] listOfFiles = folder.listFiles();
        Integer index = 1;
        if (listOfFiles.length == 0) {

            System.out.println("No Files to load");
        } else {
            for (int i = 0; i < listOfFiles.length; i++) {
                String filename = listOfFiles[i].getName();
                System.out.println(index + ")" + filename);
                index++;
            }

            //consoleOutput.display(pickSurveyToLoad);

            while (!pickedValidChoice) {
                try {
                    consoleOutput.display(pickSurveyToLoad);
                    choice = consoleInput.getInput();
                    File file = listOfFiles[Integer.parseInt(choice) - 1];


                    FileInputStream fileIn = new FileInputStream(file.getAbsolutePath());
                    ObjectInputStream in = new ObjectInputStream(fileIn);
                    survey = (Survey)in.readObject();
                    in.close();
                    fileIn.close();

                    pickedValidChoice = true;

                } catch (ArrayIndexOutOfBoundsException e) {
                    consoleOutput.display("Not a valid survey to load");
                } catch (IOException i) {
                    consoleOutput.display("IOException");
                } catch (ClassNotFoundException c) {
                    consoleOutput.display("Employee class not found");
                }
            }

        }

        return survey;
    }


    public void create() {

        Question q = createQuestion();
        questions.add( q );


    }

    // TODO: 10/21/17 Figure out creation of Questions
    private Question createQuestion () {

      Question question = null;

        consoleOutput.display(addQuestionPrompt);
        String choice = consoleInput.getInput();

        switch (choice) {

            case "1":
                question = new MultipleChoice();
                break;

        }

    return question;
    }


    protected void setName(String name) {
        this.name = name;
    }

    public void display() {
        for (int i = 0; i < questions.size(); i++) {
            questions.get(i).display();
        }
    }


    protected String getName() {
        return name;
    }
}
