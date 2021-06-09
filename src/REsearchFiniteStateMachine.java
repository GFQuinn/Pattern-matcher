import java.util.ArrayList;

public class REsearchFiniteStateMachine {

    ArrayList<REstate> fsm;

    REsearchFiniteStateMachine(){
        fsm = new ArrayList<>();
    }

    public void addState(String[] attributes)
    {
        System.out.println(attributes[0]+attributes[1]+attributes[2]);
        //if the length is 3 it has to be a branching state
        if(attributes.length == 3)
        {
            int stateNumber = Integer.valueOf(attributes[0]);
            int nextStateOne = Integer.valueOf(attributes[1]);
            int nextStateTwo = Integer.valueOf(attributes[2]);

            REstateBranching branchingState = new REstateBranching(stateNumber, nextStateOne, nextStateTwo);
            fsm.add(branchingState);
        }
        //only finishing state has next state as -1
        else if( (attributes[0]).charAt(0) == -1)
        {
            int stateNumber = Integer.valueOf(attributes[1]);
            int nextStateOne = Integer.valueOf(attributes[2]);
            int nextStateTwo = Integer.valueOf(attributes[3]);

            REstateFinish finshingState = new REstateFinish(stateNumber, nextStateOne, nextStateTwo);
            fsm.add(finshingState);
        }
        //if the matching string is not a char it must be a square bracket state
        else if (attributes[0].length() > 1)
        {
            String options = attributes[0];
            int stateNumber = Integer.valueOf(attributes[1]);
            int nextStateOne = Integer.valueOf(attributes[2]);
            int nextStateTwo = Integer.valueOf(attributes[3]);

            REstateSquareBrackets squareBracketState = new REstateSquareBrackets(stateNumber, options, nextStateOne, nextStateTwo);
            fsm.add(squareBracketState);
        }
        //else its a matching state
        else
        {
            Character character = attributes[0].charAt(0);
            int stateNumber = Integer.valueOf(attributes[1]);
            int nextStateOne = Integer.valueOf(attributes[2]);
            int nextStateTwo = Integer.valueOf(attributes[3]);
            REstateMatching matchingState = new REstateMatching(stateNumber, character, nextStateOne, nextStateTwo);
            fsm.add(matchingState);
        }
    }
    public void dump()
    {
        for (REstate state: fsm) {
            state.dump();
        }
    }



}
