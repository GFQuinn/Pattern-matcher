package com.company;
import java.util.*;

import java.util.ArrayList;

public class REfiniteStateMachine {
    private REstate startState;
    private REstate finishState;
    private ArrayList<REstate> states;

    REfiniteStateMachine(int stateNumber)
    {
        REstateBranching staringtState = new REstateBranching(stateNumber);
        states = new ArrayList<>();
        states.add(staringtState);
        startState = staringtState;
        finishState = staringtState;
    }

    REfiniteStateMachine(int stateNumber, String choices)
    {
        REstateSquareBrackets staringtState = new REstateSquareBrackets(stateNumber, choices);
        states = new ArrayList<>();
        states.add(staringtState);
        startState = staringtState;
        finishState = staringtState;
    }
    REfiniteStateMachine(int stateNumber, Character character)
    {
        REstateMatching staringtState = new REstateMatching(stateNumber, character);
        states = new ArrayList<>();
        states.add(staringtState);
        startState = staringtState;
        finishState = staringtState;
    }

    public int closure(REfiniteStateMachine fsmToAdd, int stateNumber)
    {
        //create the new Branching states
        REstateBranching firstBranching = new REstateBranching(stateNumber);
        stateNumber++;
        REstateBranching secondBranching = new REstateBranching(stateNumber);
        stateNumber++;

        //point first branching to startstate of fsmToAdd and second Branching
        firstBranching.setNextStateOne(fsmToAdd.getStartState().getStateNumber());
        firstBranching.setNextStatetwo(secondBranching.getStateNumber());

        //point fsmtoAdd to its start and second branching state
        REstate closureFinishing = fsmToAdd.getFinishState();
        REstate closureStart = fsmToAdd.getStartState();
        closureFinishing.setNextStateOne(closureStart.getStateNumber());
        closureFinishing.setNextStatetwo(secondBranching.getStateNumber());

        //add all the states and point finish to secondBranching
        states.addAll(fsmToAdd.getStates());
        states.add(firstBranching);
        states.add(secondBranching);
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

    public void appendFiniteStateMachine(REfiniteStateMachine fsmToAdd)
    {
        finishState.setBothNextStates(fsmToAdd.finishState.getStateNumber());
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

    public void dump()
    {
        for (REstate state: states) {
            state.dump();
        }
    }
}
