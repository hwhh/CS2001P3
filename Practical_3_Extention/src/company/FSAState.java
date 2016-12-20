package company;

class FSAState {

    private int currentState, transitionState;
    private String input;
    private boolean endState;

    FSAState(int currentState, String input, int transitionState) {
        this.currentState = currentState;
        this.input = input;
        this.transitionState = transitionState;
    }

    void setEndState(boolean endState) {
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

    int getCurrentState() {
        return currentState;
    }

    int getTransitionState() {
        return transitionState;
    }

    boolean isEndState() {
        return endState;
    }
}
