package company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class FSADefinition {

    private List<Integer> acceptingStates = new ArrayList<>();

    LinkedHashMap<Character, List<FSAState>> createDefinition(String fileName){
        LinkedHashMap<Character, List<FSAState>> definitionMap = new LinkedHashMap<>();
        try {
            //Take in the definitions file
            Scanner definition = new Scanner(new File(fileName));
            while (definition.hasNext()) {//While there are lines in the definition file
                List<String> line = Arrays.asList(definition.nextLine().split(" +"));//Remove spaces from the current line in definition file
                FSAState fsaState = new FSAState(Integer.parseInt(line.get(0)),line.get(1),Integer.parseInt(line.get(2)));//Create a new FSA state object
                Character character = line.get(1).charAt(0);
                if(line.contains("*")) {//Check if the line points to an accepting state
                    acceptingStates.add(Integer.parseInt(line.get(2)));
                }
                if (definitionMap.get(character) != null){//Check if the input character is already in the hasmap
                    definitionMap.get(character).add(fsaState);//Add the state to the list mapped to the input character

                }else {
                    List<FSAState> fsaStates = new ArrayList<>();
                    fsaStates.add(fsaState);//Create a new list containing the new FSA sate
                    definitionMap.put(character, fsaStates);
                }
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        setAcceptingStates(definitionMap);
        return definitionMap;
    }


    private void setAcceptingStates(LinkedHashMap<Character, List<FSAState>> definitionMap){
        for (List<FSAState> fsaStates : definitionMap.values()) {//Find the states in the map which are accepting
            //Update the FSAState object
            fsaStates.stream().filter(fsaState -> acceptingStates.contains(fsaState.getTransitionState())).forEach(fsaState -> fsaState.setEndState(true));
        }

    }






}
