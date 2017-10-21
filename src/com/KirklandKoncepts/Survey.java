package com.KirklandKoncepts;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Kirkland on 10/21/17.
 */
public class Survey implements Serializable {

    private static ConsoleInput consoleInput = new ConsoleInput();
    private static ConsoleOutput consoleOutput = new ConsoleOutput();
    private static String surveyNamePrompt = "Enter of new Survey:";

    private String surveyName;
    private List<Question> questions;

    public Survey() {

    }




    public void createSurvey() {

        consoleOutput.display(surveyNamePrompt);
        setSurveyName( consoleInput.getInput() );


    }


    private void setSurveyName(String surveyName) {
        this.surveyName = surveyName;
    }
}
