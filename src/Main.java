public class Main {

    private static ConsoleInput consoleInput = new ConsoleInput();
    private static ConsoleOutput consoleOutput = new ConsoleOutput();

    private static String[] generalMenu = {"\nMenu 1:","1) Survey","2) Test","3) Quit"};
    private static String[] surveyMenu2 = {"\nSurvey Menu 2:","1) Create a new Survey","2) Display a Survey","3) Load a Survey","4) Save a Survey","5) Modify an Existing Survey", "6) Take a Survey", "7) Tabulate a Survey", "8) Quit"};
    private static String[] testMenu2 = {"\nTest Menu 2:","1) Create a new Test","2) Display a Test","3) Load a Test","4) Save a Test","5) Modify an Existing Test", "6) Take a Test", "7) Tabulate a Test", "8) Grade a Test" , "9) Quit"};

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

        Test test = null;

        while (true) {
            consoleOutput.display(testMenu2);
            String choice = consoleInput.getInput();

            switch (choice) {
                case "1":
                    test = new Test();
                    test.create();
                    break;
                case "2":
                    consoleOutput.display("Which test would you like to display?");
                    test = Test.load(Test.getFolderName());
                    if( test != null)
                        test.display();
                    break;
                case "3":
                    consoleOutput.display("Which test would you like to load?");
                    test = Test.load(Test.getFolderName());
                    break;
                case "4":
                    if(test != null) {
                        test.save(Test.getFolderName(),test.getSurveyName());
                        consoleOutput.display("Saved");
                    }
                    else
                        consoleOutput.display("No Test Created To Save");
                    break;
                case "5":
                    consoleOutput.display("Which test would you like to modify?");
                    test = Test.load(Test.getFolderName());
                    test.edit();
                    break;

                case "6":
                    consoleOutput.display("Which test would you like to take?");
                    test = Test.load(Test.getFolderName());
                    test.take();
                    break;

                case "7":
                    consoleOutput.display("Which test would you like to tabulate?");
                    test = Test.load(Test.getFolderName());
                    if (test != null)
                        test.tabulate();
                    break;
                case "8":
                    Test temp = null;
                    consoleOutput.display("Which test would you like to grade?");
                    temp = Test.load("test_taken");
                    temp.grade();
                    break;
                case "9":
                    quit();
                    break;
                default:
                    break;

            }
        }
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
                    break;
                case "2":
                    consoleOutput.display("Which survey would you like to display?");
                    survey = Survey.load();
                    survey.display();
                    break;
                case "3":
                    consoleOutput.display("Which survey would you like to load?");
                    survey = Survey.load();
                    break;
                case "4":
                    if(survey != null) {
                        survey.save(Survey.getFolderName() , survey.getSurveyName());
                    }
                    else
                        consoleOutput.display("No Survey To Save");
                    break;
                case "5":
                    consoleOutput.display("Which survey would you like to edit?");
                    survey = Survey.load();
                    survey.edit();
                    break;

                case "6":
                    consoleOutput.display("Which survey would you like to take?");
                    survey = Survey.load();
                    survey.take();
                    break;
                case "7":
                    consoleOutput.display("Which survey would you like to tabulate?");
                    survey = Survey.load();
                    if (survey != null)
                        survey.tabulate();
                    break;

                case "8":
                    quit();
                    break;
                default:
                    break;
            }
        }
    }
    private static void quit() {
        System.exit(0);
    }




}
