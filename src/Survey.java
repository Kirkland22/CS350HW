import java.io.*;
import java.util.ArrayList;


/**
 * Created by Kirkland on 10/21/17.
 */
public class Survey implements Serializable {

    protected static ConsoleInput consoleInput = new ConsoleInput();
    protected static ConsoleOutput consoleOutput = new ConsoleOutput();
    protected   String surveyNamePrompt = "Please Enter Survey/Test Name:";
    private  static String pickSurveyToLoad = "Which survey would you like to load?";
    protected   String[] addQuestionPrompt = {"1) Add a new T/F question","2) Add a new multiple choice question","3) Add a new short answer question","4) Add a new essay question","5) Add a new ranking question" , "6) Add a new matching question " , "7) Quit"};
   protected String editMenuPrompt = "Which question do you want to edit?\n";
    private static String type = "Survey";
    private static String folderName = "survey";

    private String name;
    protected ArrayList<Question> questions = new ArrayList<>();

    public Survey() {
        consoleOutput.display(surveyNamePrompt);
        setName( consoleInput.getInput() );
    }


    // Static Method
    public static Survey load() {

        Survey survey = null;
        boolean pickedValidChoice = false;
        String choice;

        File folder = new File( getFolderName() );

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
                    survey = (Survey) in.readObject();
                    in.close();
                    fileIn.close();

                    pickedValidChoice = true;

                } catch (ArrayIndexOutOfBoundsException e) {
                    consoleOutput.display("Not a valid survey to load");
                } catch (IOException i) {
                    consoleOutput.display("IOException");
                } catch (ClassNotFoundException c) {
                    consoleOutput.display("Survey class not found");
                }
            }

        }

        return survey;
    }



    public void save() {

        try {

            FileOutputStream fileOut = new FileOutputStream(getFolderName()+"/" + getName());
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
            System.out.println( getType() + " is saved");
        }catch(IOException i) {
            i.printStackTrace();
        }

    }

    public void create() {

        boolean isAddingQuestions = true;
        Question question =  null;

        while (isAddingQuestions) {
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
                case "7":
                    question = null;
                    isAddingQuestions = false;
                    break;
                default:
                    //Nothing Ask again
                    break;
            }
            if (question != null)
                createQuestion(question);
        }

    }

    public void display() {
        consoleOutput.display("\t" + getName());
        consoleOutput.display("--------------------------------");
        consoleOutput.display("");
        for (int i = 0; i < questions.size(); i++) {
            consoleOutput.displayOneLine((i+1) + ") ");
            questions.get(i).display();
        }
    }

    protected void createQuestion(Question question) {
        question.create();
        questions.add( question );
    }

    protected void edit() {

        ArrayList<Question> questions = getQuestions();


            if (questions.size() == 0) {
                consoleOutput.display("No Questions To Edit");
            } 
            else {

                consoleOutput.display(editMenuPrompt);
                for (int i = 0; i < questions.size(); i++) {
                    consoleOutput.displayOneLine(i + 1 + ") ");
                    questions.get(i).display();
                }


                try {
                    Integer choice = consoleInput.getIntegerInput();
                    questions.get(choice - 1).edit();
                }

                catch (NumberFormatException e) {
                    consoleOutput.display("Input a Number");
                    edit();
                }

                catch (IndexOutOfBoundsException e) {
                    consoleOutput.display("Not a valid question choice");
                    edit();
                }
                
        }
           
    }


    // Getters
    protected String getName() {
        return name;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    protected static String getFolderName() {
        return folderName;
    }

    public static String getType() {
        return type;
    }

    // Setters
    protected void setName(String name) {
        this.name = name;
    }


}
