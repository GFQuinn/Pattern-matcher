import java.io.PrintStream;
import java.util.ArrayList;

public class REcompilerFiniteStateMachine {
    private REstate startState;
    private REstate finishState;
    private ArrayList<REstate> states;

    REcompilerFiniteStateMachine()
    {
        states = new ArrayList<REstate>();
    }


    REcompilerFiniteStateMachine(int stateNumber)
    {
        REstateBranching staringState = new REstateBranching(stateNumber);
        states = new ArrayList<>();
        states.add(staringState);
        startState = staringState;
        finishState = staringState;
    }

    REcompilerFiniteStateMachine(int stateNumber, String choices)
    {
        REstateSquareBrackets staringState = new REstateSquareBrackets(stateNumber, choices);
        states = new ArrayList<>();
        states.add(staringState);
        startState = staringState;
        finishState = staringState;
    }
    REcompilerFiniteStateMachine(int stateNumber, Character character)
    {
        REstateMatching staringState = new REstateMatching(stateNumber, character);
        states = new ArrayList<>();
        states.add(staringState);
        startState = staringState;
        finishState = staringState;
    }

    public int closure(REcompilerFiniteStateMachine fsmToAdd, int stateNumber)
    {
        //create the new Branching states
        REstateBranching firstBranching = new REstateBranching(stateNumber);
        stateNumber++;
        REstateBranching secondBranching = new REstateBranching(stateNumber);
        stateNumber++;

        //point first branching to start state of fsmToAdd and second Branching
        firstBranching.setNextStateOne(secondBranching.getStateNumber());
        firstBranching.setNextStatetwo(secondBranching.getStateNumber());


        //point fsmtoAdd to its start and second branching state
        REstate closureFinishing = fsmToAdd.getFinishState();
        REstate closureStart = fsmToAdd.getStartState();
        closureFinishing.setNextStateOne(closureStart.getStateNumber());
        closureFinishing.setNextStatetwo(secondBranching.getStateNumber());

        finishState.setNextStateOne(closureStart.getStateNumber());
        finishState.setNextStatetwo(firstBranching.getStateNumber());

        //add all the states and point finish to secondBranching
        this.appendFiniteStateMachine(fsmToAdd);
        states.add(firstBranching);
        states.add(secondBranching);
        finishState = secondBranching;
        return stateNumber;
    }

    public int plus(REcompilerFiniteStateMachine fsmToAdd, int stateNumber){
        //add new state to be the finishing state
        REstateBranching branching = new REstateBranching(stateNumber);
        stateNumber++;

        //get the start and finish from the fsmToAdd
        REstate plusStart = fsmToAdd.getStartState();
        REstate plusFinish = fsmToAdd.getFinishState();

        //set the current finishing state to point to the fsmToAdd
        finishState.setBothNextStates(plusStart.getStateNumber());

        //set the fsmToAdd finish to point to its start and branching state
        plusFinish.setNextStateOne(plusStart.getStateNumber());
        plusFinish.setNextStatetwo(branching.getStateNumber());

        //append the two finite states and add the branching state to the end
        this.appendFiniteStateMachine(fsmToAdd);
        states.add(branching);
        finishState = branching;

        return stateNumber;
    }

    public int questionMark(REcompilerFiniteStateMachine fsmToAdd, int stateNumber)
    {
        //create the new Branching states
        REstateBranching firstBranching = new REstateBranching(stateNumber);
        stateNumber++;
        REstateBranching secondBranching = new REstateBranching(stateNumber);
        stateNumber++;

        //get the start and finish from the fsmToAdd
        REstate plusStart = fsmToAdd.getStartState();
        REstate plusFinish = fsmToAdd.getFinishState();

        firstBranching.setNextStateOne(secondBranching.getStateNumber());
        firstBranching.setNextStatetwo(plusStart.getStateNumber());

        finishState.setBothNextStates(firstBranching.getStateNumber());


        plusFinish.setBothNextStates(secondBranching.getStateNumber());

        states.addAll(fsmToAdd.states);
        states.add(firstBranching);
        states.add(secondBranching);
        finishState = secondBranching;
        return stateNumber;
    }

    public int alternation(REcompilerFiniteStateMachine fsmToAddOne, REcompilerFiniteStateMachine fsmToAddTwo, int stateNumber){

        //get start and finish of the alternating fsm's
        REstate fsmOneStart = fsmToAddOne.getStartState();
        REstate fsmOneFinish = fsmToAddOne.getFinishState();
        REstate fsmTwoStart = fsmToAddTwo.getStartState();
        REstate fsmTwoFinish = fsmToAddTwo.getFinishState();

        //create the new Branching states
        REstateBranching firstBranching = new REstateBranching(stateNumber);
        stateNumber++;
        REstateBranching secondBranching = new REstateBranching(stateNumber);
        stateNumber++;

        //point both fsm's to second branch as its the new finishing state
        fsmOneFinish.setBothNextStates(secondBranching.getStateNumber());
        fsmTwoFinish.setBothNextStates(secondBranching.getStateNumber());

        //point the first branching at each of the fsm's
        firstBranching.setNextStateOne(fsmOneStart.getStateNumber());
        firstBranching.setNextStatetwo(fsmTwoStart.getStateNumber());

        //point the current fsm to at the first branching state
        finishState.setBothNextStates(firstBranching.getStateNumber());

        //add all the new states to this fsm
        states.add(firstBranching);
        states.addAll(fsmToAddOne.states);
        states.addAll(fsmToAddTwo.states);
        states.add(secondBranching);
        //reset the finish state to be the second branching state
        finishState = secondBranching;
        return stateNumber;
    }

    public void addFinishState(int stateNumber)
    {
        REstateFinish finishingState = new REstateFinish(stateNumber);
        finishState.setBothNextStates(finishingState.getStateNumber());
        finishState = finishingState;
        states.add(finishingState);
    }
    public void addState(REstate stateToAdd)
    {
        states.add(stateToAdd);
        finishState.setBothNextStates(stateToAdd.getStateNumber());
        finishState = stateToAdd;
    }

    public void appendFiniteStateMachine(REcompilerFiniteStateMachine fsmToAdd)
    {
        finishState.setBothNextStates(fsmToAdd.getStartState().getStateNumber());
        finishState = fsmToAdd.getFinishState();
        ArrayList<REstate> statesToAdd = fsmToAdd.getStates();
        states.addAll(statesToAdd);
    }

    public ArrayList<REstate> getStates() {
        return states;
    }

    public REstate getFinishState() {
        return finishState;
    }

    public REstate getStartState() {
        return startState;
    }

    public void printToStream()
    {
        PrintStream printer = new PrintStream(System.out);
        for (REstate state: states) {

         String attributes = state.getAttributes();
            printer.println(attributes);
        }
    }

    public void dump()
    {
        for (REstate state: states) {
            state.dump();
        }
    }
}
