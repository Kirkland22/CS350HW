import java.io.*;

/**
 * Created by Kirkland on 10/21/17.
 */
public class Test extends Survey {


    private static String type = "Test";
    private static String folderName = "test";


    public Test() {
        super();
        type = "test";
    }

    // Static Method
    public static Test load(String path) {

        Test test = null;
        boolean pickedValidChoice = false;
        String choice;

        File folder = new File( path );

        // gets you the list of files at this folder
        File[] listOfFiles = folder.listFiles();
        Integer index = 1;
        if (listOfFiles.length == 0) {

            consoleOutput.display("No Files to load");
        } else {
            for (int i = 0; i < listOfFiles.length; i++) {
                String filename = listOfFiles[i].getName();
                System.out.println(index + ")" + filename);
                index++;
            }

            while (!pickedValidChoice) {
                try {
                    choice = consoleInput.getInput();
                    File file = listOfFiles[Integer.parseInt(choice) - 1];


                    FileInputStream fileIn = new FileInputStream(file.getAbsolutePath());
                    ObjectInputStream in = new ObjectInputStream(fileIn);
                    test = (Test) in.readObject();
                    in.close();
                    fileIn.close();

                    pickedValidChoice = true;

                } catch (ArrayIndexOutOfBoundsException e) {
                    consoleOutput.display("Not a valid Test to load");
                } catch (IOException i) {
                    consoleOutput.display("IOException");
                } catch (ClassNotFoundException c) {
                    consoleOutput.display("Class not found");
                } catch (NumberFormatException e) {
                    consoleOutput.display("Enter a number");
                }
            }

        }

        return test;
    }

    @Override
    protected void take() {

        consoleOutput.display("Name of User Taking " + getType() + " :");
        setUserName(consoleInput.getInput());

        for (int i = 0; i < questions.size(); i++) {
            consoleOutput.displayOneLine((i+1) + ") ");
            questions.get(i).take();
        }
        String saveName = getSurveyName() + "_" + getUserName();

        save(folderName,getSurveyName());
        save("test_taken",saveName);

        consoleOutput.display("Done!");

    }

    @Override
    public void display() {
        consoleOutput.display("");
        for (int i = 0; i < questions.size(); i++) {
            consoleOutput.displayOneLine((i+1) + ") ");
            questions.get(i).display();
            questions.get(i).displayCorrectAnswer();
        }
    }

    public void grade() {
        int grade = 0;
        int numberOfUngradeable = 0;

        for (int i = 0; i < questions.size(); i++) {

            int returnValue;

            returnValue = questions.get(i).grade();
            // returnValue will be -1 if question cant be graded automatically
            if (returnValue == -1) {
                numberOfUngradeable++;
            }
            else {
                grade = grade + returnValue;
            }
        }

        consoleOutput.display("Grade = " + (grade*10) + "/" + (questions.size() - numberOfUngradeable)*10 + " with " + numberOfUngradeable + " left to be graded manually.");

    }

    // Getters

    public static String getFolderName() {
        return folderName;
    }

    @Override
    protected void createQuestion(Question question) {
        question.create();
        question.setCorrectAnswers();
        questions.add( question );
    }



}
