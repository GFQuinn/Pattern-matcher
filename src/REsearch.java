import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class REsearch {

    public static void main(String[] args) {

        ArrayList<String[]> fsmStrings = readInputFSM();
        REsearchFiniteStateMachine fsm = parseFiniteStateMachine(fsmStrings);
        fsm.dump();

    }

    private static ArrayList<String[]> readInputFSM() {
        String currentLine;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String[]> fsmString = new ArrayList<String[]>();
        char spliter = (char) 3;
        try {
            reader = new BufferedReader(new InputStreamReader(System.in));
            while ((currentLine = reader.readLine()) != "") {
                String[] attributes = currentLine.split(String.valueOf(spliter));
                fsmString.add(attributes);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return fsmString;
    }


    public static REsearchFiniteStateMachine parseFiniteStateMachine(ArrayList<String[]> inputStrings) {
        REsearchFiniteStateMachine fsm = new REsearchFiniteStateMachine();
        for (String[] attributes : inputStrings) {
            fsm.addState(attributes);
        }
        return fsm;
    }


}