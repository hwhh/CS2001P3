
public class FSAState {

    private int currentState, transitionState;
    private String input;
    private boolean endState;

    public FSAState(int currentState, String input, int transitionState) {
        this.currentState = currentState;
        this.input = input;
        this.transitionState = transitionState;
    }

    public void setEndState(boolean endState) {
        this.endState = endState;
    }

    @Override
    public String toString() {
        return "FSAState{" +
                "currentState=" + currentState +
                ", transitionState=" + transitionState +
                ", input='" + input + '\'' +
                ", endState=" + endState +
                '}';
    }

    public int getCurrentState() {
        return currentState;
    }

    public int getTransitionState() {
        return transitionState;
    }

    public String getInput() {
        return input;
    }

    public boolean isEndState() {
        return endState;
    }
}
