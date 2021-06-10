import java.io.*;
import java.util.ArrayList;

public class REsearch {

    public static void main(String[] args) {

        ArrayList<String[]> fsmStrings = readInputFSM();
        REsearchFiniteStateMachine fsm = parseFiniteStateMachine(fsmStrings);
        fsm.dump();
        String filename = args[0];
        ArrayList<String> fileLines = readFile(filename);

    }

    private static ArrayList<String> readFile(String filename)
    {
     ArrayList<String> fileLines = new ArrayList<>();
        String line = "";
        try {
            BufferedReader inputReader = new BufferedReader(new FileReader(filename));
            while((line = inputReader.readLine()) != "")
            {
                fileLines.add(line);
                System.out.println();
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
