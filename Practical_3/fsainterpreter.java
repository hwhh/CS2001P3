
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;

public class fsainterpreter {

    public static void main(String[] args) {
      if(args.length == 1){
        FSADefinition fsaDefinition = new FSADefinition();
        LinkedHashMap<Character, List<FSAState>> definitionMap = fsaDefinition.createDefinition(args[0]);
        Scanner input = new Scanner(System.in);
        while (input.hasNext()){
            int location = 1, count=0;
            String inputLine = input.nextLine();
            char[] chars = inputLine.toCharArray();
            boolean accepted = true;
            for (char aChar : chars) {
                if(count == chars.length-1)
                    location = getInput(aChar, location, definitionMap, true);
                else
                    location = getInput(aChar, location, definitionMap, false);
                if (location == -1) {
                    accepted = false;
                    break;
                }
                count++;
            }
            if(accepted)
                System.out.println(inputLine + " Accepted");
            else
                System.out.println(inputLine + " Not accepted");
        }
      }


    }



    public static int getInput (char input, int pos, LinkedHashMap<Character, List<FSAState>> definitionMap, boolean lastChar) {
        try {
            List<FSAState> states = definitionMap.get(input);
            for (FSAState state : states) {
                if (state.getCurrentState()==pos && !lastChar) {
                    return state.getTransitionState();
                }
                else if(state.getCurrentState()==pos && lastChar && state.isEndState()) {
                    return state.getTransitionState();
                }
            }

        } catch (Exception e) {

        }
        return -1;
    }



}
