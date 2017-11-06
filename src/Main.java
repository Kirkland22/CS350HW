public class Main {

    private static ConsoleInput consoleInput = new ConsoleInput();
    private static ConsoleOutput consoleOutput = new ConsoleOutput();

    private static String[] generalMenu = {"\nMenu 1:","1) Survey","2) Test","3) Quit"};
    private static String[] surveyMenu2 = {"\nSurvey Menu 2:","1) Create a new Survey","2) Display a Survey","3) Load a Survey","4) Save a Survey","5) Modify an Existing Survey", "6) Quit"};
    private static String[] testMenu2 = {"\nTest Menu 2:","1) Create a new Test","2) Display a Test","3) Load a Test","4) Save a Test","5) Modify an Existing Test", "6) Quit"};

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
                    if(test != null) {
                        test.display();
                    }
                    break;
                case "3":
                    test = Test.load();
                    break;
                case "4":
                    if(test != null) {
                        test.save();
                    }
                    else
                        consoleOutput.display("No Test To Save");
                    break;
                case "5":
                    if (test == null) {
                        test = test.load();
                    }
                    test.edit();
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
                    if(survey != null) {
                        survey.display();
                    }
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

                    if (survey == null) {
                        survey = Survey.load();
                    }
                    survey.edit();
                    break;

                case "6":
                    quit();
            }
        }
    }
    private static void quit() {
        System.exit(0);
    }




}
