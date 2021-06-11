import java.io.*;
import java.util.ArrayList;

public class REsearch {

    public static void main(String[] args) {

        ArrayList<String[]> fsmStrings = readInputFSM();
        REsearchFiniteStateMachine fsm = parseFiniteStateMachine(fsmStrings);

        String filename = args[0];
        ArrayList<String> fileLines = readFile(filename);
        int lineCounter = 0;
        ArrayList<Integer> lineMatchList = new ArrayList<>();
        for (String line : fileLines) {
            for (int i = 0; i < line.length(); i++) {
                //if we find a match in the line add it to the lineMatchList and stop looping for this line
                if (runSearch(i, line.length(), line, fsm)){
                    System.out.println(lineCounter + "   starting at char number =" + i);
                    //lineMatchList.add(lineCounter);
                    break;
                }
            }
            lineCounter++;
        }

    }

    private static boolean runSearch(int startIndex, int lineLength, String currentLine, REsearchFiniteStateMachine fsm)
    {
        int point = 0;
        REstate startingState = fsm.getStart();
        REdeque deque = new REdeque(startingState);
        REstateScan scanState = new REstateScan();
        deque.addLast(scanState);
        REstate currentState;

        while(deque.getSize() != 0)
        {

            currentState = deque.pop();
            if(!(currentState instanceof REstateScan))
            {

                if(currentState instanceof REstateFinish)
                {
                    return true;
                }
                else if(currentState instanceof REstateBranching)
                {
                    int nextStateOneNumber = currentState.nextStateOne;
                    int nextStateTwoNumber = currentState.nextStatetwo;

                    if(nextStateOneNumber == nextStateTwoNumber)
                    {
                        REstate nextState = fsm.getState(nextStateOneNumber);
                        deque.addLast(nextState);
                    }
                    else {
                        REstate nextStateOne = fsm.getState(nextStateOneNumber);
                        REstate nextStateTwo = fsm.getState(nextStateTwoNumber);
                        deque.addLast(nextStateOne);
                        deque.addLast(nextStateTwo);
                    }
                }
                else if(currentState instanceof REstateSquareBrackets)
                {
                    if(startIndex + point == lineLength)
                    {
                        break;
                    }
                    Character currentCharacter = currentLine.charAt(point+startIndex);
                    //if the REstateSquareBrackets contains the character then we can add next states
                    if(((REstateSquareBrackets) currentState).checkContains(currentCharacter))
                    {
                        int nextStateOneNumber = currentState.nextStateOne;
                        int nextStateTwoNumber = currentState.nextStatetwo;
                        if(nextStateOneNumber == nextStateTwoNumber)
                        {
                            REstate nextState = fsm.getState(nextStateOneNumber);
                            deque.addLast(nextState);
                            point++;
                        }
                        else
                        {
                            REstate nextStateOne = fsm.getState(nextStateOneNumber);
                            REstate nextStateTwo = fsm.getState(nextStateTwoNumber);
                            deque.addLast(nextStateOne);
                            deque.addLast(nextStateTwo);
                            point++;
                        }
                    }
                    //else we don't have a match for this state so it just gets popped
                }
                //we must now be looking at a REstateMatching
                else
                {
                    if(startIndex + point == lineLength)
                    {
                        break;
                    }
                    Character currentCharacter = currentLine.charAt(point+startIndex);
                    //if the currentCharacter is a match with the matching state char add its next states to deque
                    if(((REstateMatching)currentState).isMatching(currentCharacter))
                    {
                        int nextStateOneNumber = currentState.nextStateOne;
                        int nextStateTwoNumber = currentState.nextStatetwo;
                        if(nextStateOneNumber == nextStateTwoNumber)
                        {

                            REstate nextState = fsm.getState(nextStateOneNumber);
                            deque.addLast(nextState);
                            point++;
                        }
                        else
                        {
                            REstate nextStateOne = fsm.getState(nextStateOneNumber);
                            REstate nextStateTwo = fsm.getState(nextStateTwoNumber);
                            deque.addLast(nextStateOne);
                            deque.addLast(nextStateTwo);
                            point++;
                        }
                    }
                }
            }
            //else its a scan state
            else
            {
                if(deque.getSize()!= 0)
                {
                    deque.addLast(scanState);
                }

            }

        }

        return false;
    }



    private static ArrayList<String> readFile(String filename)
    {
     ArrayList<String> fileLines = new ArrayList<>();
        String line = "";
        try {
            BufferedReader inputReader = new BufferedReader(new FileReader(filename));
            while((line = inputReader.readLine()) != null)
            {
                fileLines.add(line);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileLines;
    }

    private static ArrayList<String[]> readInputFSM() {
        String currentLine;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        ArrayList<String[]> fsmString = new ArrayList<String[]>();
        Character spliter = null;
        try {
            reader = new BufferedReader(new InputStreamReader(System.in));
            while ((currentLine = reader.readLine()) != "") {
                String[] attributes = currentLine.split(String.valueOf(spliter));
                fsmString.add(attributes);
            }
        } catch (Exception e) {
        }
        return fsmString;
    }
    public static REsearchFiniteStateMachine parseFiniteStateMachine(ArrayList<String[]> inputStrings) {
        REsearchFiniteStateMachine fsm = new REsearchFiniteStateMachine(inputStrings);
        return fsm;
    }


}
