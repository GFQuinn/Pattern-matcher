package com.company;
import java.util.*;

import java.util.ArrayList;

public class REfiniteStateMachine {
    private REstate startState;
    private REstate finishState;
    private ArrayList<REstate> states;

    REfiniteStateMachine()
    {
        states = new ArrayList<REstate>();
    }


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

    public int plus(REfiniteStateMachine fsmToAdd, int stateNumber){
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

    public int questionMark(REfiniteStateMachine fsmToAdd, int stateNumber)
    {
        //create the new Branching states
        REstateBranching firstBranching = new REstateBranching(stateNumber);
        stateNumber++;
        REstateBranching secondBranching = new REstateBranching(stateNumber);
        stateNumber++;

        //get the start and finish from the fsmToAdd
        REstate plusStart = fsmToAdd.getStartState();
        REstate plusFinish = fsmToAdd.getFinishState();

        plusStart.setBothNextStates(firstBranching.getStateNumber());

        firstBranching.setNextStateOne(secondBranching.getStateNumber());
        firstBranching.setNextStatetwo(plusStart.getStateNumber()+1);

        plusFinish.setBothNextStates(secondBranching.getStateNumber());

        this.appendFiniteStateMachine(fsmToAdd);
        states.add(firstBranching);
        states.add(secondBranching);
        finishState = secondBranching;
        return stateNumber;
    }

    public int alternation(REfiniteStateMachine fsmToAddOne, REfiniteStateMachine fsmToAddTwo, int stateNumber){

        REstate fsmOneStart = fsmToAddOne.getStartState();
        REstate fsmOneFinish = fsmToAddOne.getFinishState();

        REstate fsmTwoStart = fsmToAddTwo.getStartState();
        REstate fsmTwoFinish = fsmToAddTwo.getFinishState();

        //create the new Branching states
        REstateBranching firstBranching = new REstateBranching(stateNumber);
        stateNumber++;
        REstateBranching secondBranching = new REstateBranching(stateNumber);
        stateNumber++;

        fsmOneFinish.setBothNextStates(secondBranching.getStateNumber());
        fsmTwoFinish.setBothNextStates(secondBranching.getStateNumber());

        firstBranching.setNextStateOne(fsmOneStart.getStateNumber());
        firstBranching.setNextStatetwo(fsmTwoStart.getStateNumber());

        finishState.setBothNextStates(firstBranching.getStateNumber());

        states.add(firstBranching);
        states.addAll(fsmToAddOne.states);
        states.addAll(fsmToAddTwo.states);
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

    public void dump()
    {
        for (REstate state: states) {
            state.dump();
        }
    }
}
