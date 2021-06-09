public class REcompile {

    private static String pattern;
    private static int currentStateNumber = 0;
    private static int currentIndex = 0;


    public static void main(String[] args) throws Exception {
        pattern = args[0];
        REcompilerFiniteStateMachine startingFSM = new REcompilerFiniteStateMachine(currentStateNumber);
        currentStateNumber++;
        REcompilerFiniteStateMachine finishedFSM = expression(startingFSM);
        finishedFSM.addFinishState(currentStateNumber);
        currentStateNumber++;
        //finishedFSM.dump();
        finishedFSM.printToStream();

    }

    private static REcompilerFiniteStateMachine expression(REcompilerFiniteStateMachine startingFSM) throws Exception {
        if (pattern.charAt(currentIndex) == '|' && currentIndex != 0) {
            currentIndex++;

            REcompilerFiniteStateMachine newFSM = new REcompilerFiniteStateMachine(currentStateNumber);
            currentStateNumber++;

            newFSM =  expression(newFSM);
            REcompilerFiniteStateMachine alternationFSM = new REcompilerFiniteStateMachine(currentStateNumber);
            currentStateNumber++;

            currentStateNumber = alternationFSM.alternation(newFSM,startingFSM, currentStateNumber);

            return alternationFSM;
        }
        else if(pattern.charAt(currentIndex) == ')')
        {
            return startingFSM;
        }
        else {

            //if we are at the end of the pattern create fsm and return
            if (pattern.length() == currentIndex) {

                startingFSM.addFinishState(currentStateNumber);
                return startingFSM;
            }

            //ET
            //startingFSM.appendFiniteStateMachine(term(startingFSM));
            startingFSM = term(startingFSM);
            if (pattern.length() == currentIndex) {


                return startingFSM;
            } else {
                REcompilerFiniteStateMachine TE = expression(startingFSM);
                return TE;
            }
        }

    }


    private static REcompilerFiniteStateMachine term(REcompilerFiniteStateMachine startingFSM) throws Exception {
        REcompilerFiniteStateMachine factorsFSM = factor();

        if (pattern.length() == currentIndex) {
            //if factor returns and we are at the end append the factorsFSM and return
            startingFSM.appendFiniteStateMachine(factorsFSM);
            return startingFSM;
        }
        if (pattern.charAt(currentIndex) == '*') {
            currentStateNumber = startingFSM.closure(factorsFSM, currentStateNumber);
            currentIndex++;
            return startingFSM;
        }
        else if(pattern.charAt(currentIndex) == '+')
        {
            currentStateNumber = startingFSM.plus(factorsFSM, currentStateNumber);
            currentIndex++;
            return startingFSM;
        }
        else if(pattern.charAt(currentIndex) == '?')
        {
            currentStateNumber = startingFSM.questionMark(factorsFSM, currentStateNumber);
            currentIndex++;
            return startingFSM;
        }
        else {
            startingFSM.appendFiniteStateMachine(factorsFSM);
            return startingFSM;
        }

    }

    private static REcompilerFiniteStateMachine factor() throws Exception {
        if (pattern.length() == currentIndex) {
            throw new Exception("Invalid expression");
        } else if (pattern.charAt(currentIndex) == '[') {
            currentIndex++;
            String literalList = "";
            do {
                if (pattern.length() == currentIndex) {
                    throw new Exception("Invalid expression No corresponding square bracket");
                }
                literalList = literalList + pattern.charAt(currentIndex);
                currentIndex++;
            }
            while (pattern.charAt(currentIndex) != ']');

            REcompilerFiniteStateMachine newState = new REcompilerFiniteStateMachine(currentStateNumber, literalList);
            currentStateNumber++;
            currentIndex++;
            return newState;
        } else if (pattern.charAt(currentIndex) == '\\' ){

            currentIndex++;
            Character currentChar = pattern.charAt(currentIndex);
            currentIndex++;

            REcompilerFiniteStateMachine newState = new REcompilerFiniteStateMachine(currentStateNumber, currentChar);
            currentStateNumber++;
            return newState;

        } else if (isliteral()) {

            Character currentChar = pattern.charAt(currentIndex);
            currentIndex++;
            REcompilerFiniteStateMachine newState = new REcompilerFiniteStateMachine(currentStateNumber, currentChar);
            currentStateNumber++;
            return newState;

        } else if (pattern.charAt(currentIndex) == '.') {
            Character currentChar = pattern.charAt(currentIndex);
            currentIndex++;

            REcompilerFiniteStateMachine newState = new REcompilerFiniteStateMachine(currentStateNumber, currentChar);
            currentStateNumber++;
            return newState;
        }
        else if (pattern.charAt(currentIndex) == '(')
        {
            currentIndex++;
            REcompilerFiniteStateMachine startingFSM = new REcompilerFiniteStateMachine(currentStateNumber);
            currentStateNumber++;
            REcompilerFiniteStateMachine bracketExpression = expression(startingFSM);
            if(pattern.charAt(currentIndex) == ')') {
                currentIndex++;
            }
            return startingFSM;
        }
        else {

            //current char must be invalid throw error
            throw new Exception("Invalid expression, invalid char at " + currentIndex);
        }

    }


    private static boolean isliteral() {
        String nonliterallist = "\\|*?+.()[]";
        char currentChar = pattern.charAt(currentIndex);
        if (nonliterallist.indexOf(currentChar) == -1) {
            return true;
        }
        return false;
    }
}
