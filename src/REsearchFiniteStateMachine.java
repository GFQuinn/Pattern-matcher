import java.util.ArrayList;

public class REsearchFiniteStateMachine {

    private int startingState;
    REstate[] fsm;

    REsearchFiniteStateMachine(ArrayList<String[]> inputStrings){
        int length = inputStrings.size();
        fsm = new REstate[length];
        startingState = Integer.valueOf(inputStrings.get(0)[0]);

        for (String[] attributes:inputStrings)
        {
            int stateNumber = Integer.valueOf(attributes[0]);
            fsm[stateNumber] = addState(attributes);
        }
    }
    public REstate getState(int stateNumber)
    {
        return fsm[stateNumber];
    }

    public REstate getStart()
    {
        return fsm[startingState];
    }

    public REstate addState(String[] attributes)
    {
        //if the length is 3 it has to be a branching state
        if(attributes.length == 3)
        {
            int stateNumber = Integer.valueOf(attributes[0]);
            int nextStateOne = Integer.valueOf(attributes[1]);
            int nextStateTwo = Integer.valueOf(attributes[2]);

            REstateBranching branchingState = new REstateBranching(stateNumber, nextStateOne, nextStateTwo);
            return branchingState;
        }
        //only finishing state has next state as -1
        else if(  attributes[1].equals("-1") )
        {

            int stateNumber = Integer.valueOf(attributes[0]);
            int nextStateOne = Integer.valueOf(attributes[2]);
            int nextStateTwo = Integer.valueOf(attributes[3]);

            REstateFinish finshingState = new REstateFinish(stateNumber, nextStateOne, nextStateTwo);
            return finshingState;
        }
        //if the matching string is not a char it must be a square bracket state
        else if (attributes[1].length() > 1)
        {

            int stateNumber = Integer.valueOf(attributes[0]);
            String options = attributes[1].substring(1,attributes.length);
            int nextStateOne = Integer.valueOf(attributes[2]);
            int nextStateTwo = Integer.valueOf(attributes[3]);

            REstateSquareBrackets squareBracketState = new REstateSquareBrackets(stateNumber, options, nextStateOne, nextStateTwo);
            return squareBracketState;
        }
        //else its a matching state.
        else
        {
            int stateNumber = Integer.valueOf(attributes[0]);
            Character character = attributes[1].charAt(0);
            int nextStateOne = Integer.valueOf(attributes[2]);
            int nextStateTwo = Integer.valueOf(attributes[3]);
            REstateMatching matchingState = new REstateMatching(stateNumber, character, nextStateOne, nextStateTwo);
            return matchingState;
        }
    }
    public void dump()
    {
        for (REstate state: fsm) {
            state.dump();
        }
    }
}
