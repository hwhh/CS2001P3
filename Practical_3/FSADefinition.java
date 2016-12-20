
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FSADefinition {

    public List<Integer> acceptingStates = new ArrayList<>();

    public LinkedHashMap<Character, List<FSAState>> createDefinition (String fileName){
        LinkedHashMap<Character, List<FSAState>> definitionMap = new LinkedHashMap<>();
        try {
            Scanner definition = new Scanner(new File(fileName));
            while (definition.hasNext()) {
                List<String> line = Arrays.asList(definition.nextLine().split(" +"));//
                FSAState fsaState = new FSAState(Integer.parseInt(line.get(0)),line.get(1),Integer.parseInt(line.get(2)));
                Character character = line.get(1).charAt(0);
                if(line.contains("*")) {
                    acceptingStates.add(Integer.parseInt(line.get(2)));
                }
                if (definitionMap.get(character) != null){
                    definitionMap.get(character).add(fsaState);

                }else {
                    List<FSAState> fsaStates = new ArrayList<>();
                    fsaStates.add(fsaState);
                    definitionMap.put(character, fsaStates);
                }
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        setAcceptingStates(definitionMap);
        return definitionMap;
    }


    public void setAcceptingStates (LinkedHashMap<Character, List<FSAState>> definitionMap){
        for (List<FSAState> fsaStates : definitionMap.values()) {
            for (FSAState fsaState : fsaStates) {
                if(acceptingStates.contains(fsaState.getTransitionState()))
                    fsaState.setEndState(true);
            }
        }

    }






}
