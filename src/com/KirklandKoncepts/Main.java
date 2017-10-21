package com.KirklandKoncepts;




public class Main {

    private static ConsoleInput consoleInput = new ConsoleInput();
    private static ConsoleOutput consoleOutput = new ConsoleOutput();
    private static String[] surveyMenu2 = {"Survey Menu 2:","1) Create a new Survey","2) Display a Survey","3) Load a Survey","4) Save a Survey","5) Quit"};


    public static void main(String[] args) {


        start();

    }


    private static void start() {
            consoleOutput.display("Menu 1:");
            consoleOutput.display("1) Survey");
            consoleOutput.display("2) Test");
            consoleOutput.display("3) Quit");

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
            }

        }
    private static void testMenu() {
        consoleOutput.display("You picked Test");
    }


    private static void surveyMenu() {

        Survey survey;

        while (true) {
            consoleOutput.display(surveyMenu2);
            String choice = consoleInput.getInput();

            switch (choice) {
                case "1":
                    System.out.println("Create");
                    break;
                case "2":
                    System.out.println("Display");
                    break;
                case "3":
                    quit();
                    break;
            }
        }
    }




    private static void quit() {
        System.exit(0);
    }




}
