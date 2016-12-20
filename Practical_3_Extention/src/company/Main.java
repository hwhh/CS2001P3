package company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;


public class Main {

    private static company.GUI gui;
    private static List<LinkedHashMap<Character, List<FSAState>>> definitionMaps = new ArrayList<>();
    private static LinkedHashMap<Character, List<FSAState>> currentState = new LinkedHashMap<>();

    public static void main(String[] args) {
        //Create a new GUI object on a new thread
        java.awt.EventQueue.invokeLater(() -> {
            gui = new GUI();
            gui.setVisible(true);
        });

    }

    static void startJob (){
        try {
            //Create a new FSADefinition object
            FSADefinition fsaDefinition = new FSADefinition();
            //Create and add all of the generated FSA definitions to a list
            for (File file : gui.getDefinitionFiles()) {
                definitionMaps.add(fsaDefinition.createDefinition(file.getAbsolutePath()));
            }
            //Get the data from the selected input file
            Scanner input = new Scanner(new File(gui.getjTextFieldInputFile().getText()));
            while (input.hasNext()){
                //Set the current FSA definition to the first definition in the list
                currentState = definitionMaps.get(0);
                boolean success = true;
                int location = 1, count=0;
                //Remove spaces from current line in input file and split into character array
                String inputLine = input.nextLine().replaceAll("\\s","");
                char[] chars = inputLine.toCharArray();
                for (char aChar : chars) {
                    //Check if last character in line
                    if (count == chars.length-1)
                        location = getInput(aChar, location, true);
                    else
                        location = getInput(aChar, location, false);
                    if (location == -1) {
                        //If the input line contains an error break and notify the user
                        success = false;
                        gui.getjTextAreaDefinition().append("invalid string in input file: " + inputLine + "\n");
                        break;
                    }
                    count++;
                }
                if(success){
                    gui.getjTextAreaDefinition().append("success: "+inputLine+"\n");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    static int getInput (char input, int pos, boolean lastChar) {
        //Check the current definition contains the input character
        List<FSAState> states = currentState.get(input);
        if(states == null){//If the current definition doesnt contains the input character
            if(getNextFSA()) {//Change the current definition
                if(currentState.get(input) != null) {//Check that the new definition contains the input
                    for (FSAState fsaState : currentState.get(input)) {
                        if (fsaState.getCurrentState() == 1)//Make sure that the input is in the valid place
                            return 1;
                    }
                }
            }
            return -1;
        }
        for (FSAState state : states) {//Loop through the valid states
            if (state.getCurrentState() == pos && !lastChar) {//Check the input character can follow the previous character
                return state.getTransitionState();
            } else if (state.getCurrentState() == pos && lastChar && state.isEndState()) {//If the last character on the line make sure input is accepting state
                return state.getTransitionState();
            }
        }
        return -1;
    }


    static boolean getNextFSA (){
        int count = 0;
        boolean isNextState = false;
        for (LinkedHashMap<Character, List<FSAState>> definitionMap : definitionMaps) {//Get the next FSA definition
            if(!definitionMap.equals(currentState)){
                currentState=definitionMaps.get(count);
                isNextState = true;
            }
            count++;
        }
        return isNextState;
    }




}
