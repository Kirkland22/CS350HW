package com.KirklandKoncepts;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Main {

    private static ConsoleInput consoleInput = new ConsoleInput();
    private static ConsoleOutput consoleOutput = new ConsoleOutput();

    private static String[] generalMenu = {"Menu 1:","1) Survey","2) Test","3) Quit"};
    private static String[] surveyMenu2 = {"Survey Menu 2:","1) Create a new Survey","2) Display a Survey","3) Load a Survey","4) Save a Survey","5) Quit"};


    public static void main(String[] args) {


        start();

    }


    private static void start() {
            consoleOutput.display(generalMenu);
            String choice = consoleInput.getInput();

            switch (choice) {

                case "1":
                    surveyMenu();
                    break;
                case "2":
                    testMenu();
                    break;
                case "3":
                    quit();
                    break;

                default:
                    start();
            }

        }
    private static void testMenu() {

        Test test = new Test();
        consoleOutput.display("You picked Test");
    }


    private static void surveyMenu() {

        Survey survey = null;

        while (true) {
            consoleOutput.display(surveyMenu2);
            String choice = consoleInput.getInput();

            switch (choice) {
                case "1":
                    survey = new Survey();
                    survey.create();
                    //consoleOutput.display(survey.getName());
                    break;
                case "2":
                    if(survey != null) {
                        survey.display();
                    }
                    //consoleOutput.display("Display - Not implemented yet");
                    break;
                case "3":
                    survey = Survey.load();
                    break;
                case "4":
                    if(survey != null) {
                        survey.save();
                    }
                    else
                        consoleOutput.display("No Survey To Save");
                    break;
                case "5":
                    quit();
                    break;
            }
        }
    }

    private static Survey loadSurvey() {

        search(".survey");

        return null;
    }


    private static void search(String fileExtension) {

    }

    private static void quit() {
        System.exit(0);
    }




}
