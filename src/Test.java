import java.io.*;

/**
 * Created by Kirkland on 10/21/17.
 */
public class Test extends Survey {

    private  static String pickSurveyToLoad = "Which Test would you like to load?";

    public Test() {
        super();
    }




    // Static Method
    public static Test load() {

        Test test = null;
        boolean pickedValidChoice = false;
        String choice;

        File folder = new File("test");

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

            while (!pickedValidChoice) {
                try {
                    consoleOutput.display(pickSurveyToLoad);
                    choice = consoleInput.getInput();
                    File file = listOfFiles[Integer.parseInt(choice) - 1];


                    FileInputStream fileIn = new FileInputStream(file.getAbsolutePath());
                    ObjectInputStream in = new ObjectInputStream(fileIn);
                    test = (Test)in.readObject();
                    in.close();
                    fileIn.close();

                    pickedValidChoice = true;

                } catch (ArrayIndexOutOfBoundsException e) {
                    consoleOutput.display("Not a valid survey to load");
                } catch (IOException i) {
                    consoleOutput.display("IOException");
                } catch (ClassNotFoundException c) {
                    consoleOutput.display("Class not found");
                }
            }

        }

        return test;
    }

    @Override
    public void create() {

        boolean isAddingQuestions = true;
        Question question;

        while (isAddingQuestions) {
            question = null;
            consoleOutput.display(addQuestionPrompt);
            String choice = consoleInput.getInput();

            switch (choice) {

                case "1":
                    question = new TrueFalse();
                    break;
                case "2":
                    question = new MultipleChoice();
                    break;
                case "3":
                    question = new ShortAnswer();
                    break;
                case "4":
                    question = new Essay();
                    break;
                case "5":
                    question = new Ranking();
                    break;
                case "6":
                    question = new Matching();
                    break;
                case "7" :
                    question = null;
                    isAddingQuestions = false;
            }

            if (question != null)
                createTestQuestion(question);
        }



    }

    @Override
    public void display() {
        consoleOutput.display("");
        for (int i = 0; i < questions.size(); i++) {
            consoleOutput.displayONELINE((i+1) + ") ");
            questions.get(i).display();
            questions.get(i).displayCorrectAnswer();
        }
    }

    public void save() {

        try {
            FileOutputStream fileOut = new FileOutputStream("test/" + getName());
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
            System.out.println("Test is saved");
        }catch(IOException i) {
            i.printStackTrace();
        }

    }


    private void createTestQuestion(Question question) {
        question.create();
        question.setAnswer();
        questions.add( question );
    }



}
