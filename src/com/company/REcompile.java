package com.company;

public class REcompile {

    private static String pattern;
    private static int currentStateNumber = 0;
    private static int currentIndex = 0;


    public static void main(String[] args) throws Exception {
        pattern = args[0];
        REfiniteStateMachine startingFSM = new REfiniteStateMachine(currentStateNumber);
        currentStateNumber++;
        REfiniteStateMachine finishedFSM = expression(startingFSM);
        finishedFSM.dump();
    }

    private static REfiniteStateMachine expression(REfiniteStateMachine startingFSM) throws Exception {
        if (pattern.charAt(currentIndex) == '|' && currentIndex != 0) {
            currentIndex++;
            return startingFSM;

        } else {

            //if we are at the end of the pattern create fsm and return
            if (pattern.length() == currentIndex) {

                startingFSM.addFinishState(currentStateNumber);
                return startingFSM;
            }
            //ET
            //startingFSM.appendFiniteStateMachine(term(startingFSM));
            startingFSM = term(startingFSM);
            if (pattern.length() == currentIndex) {

                startingFSM.addFinishState(currentStateNumber);
                return startingFSM;
            } else {
                REfiniteStateMachine TE = expression(startingFSM);
                return TE;
            }

            /*if (pattern.charAt(currentIndex) == '(' || isliteral() || pattern.charAt(currentIndex) == '.' | pattern.charAt(currentIndex) == '[' | pattern.charAt(currentIndex) == '|' | pattern.charAt(currentIndex) == (char) 92) {

                if (pattern.length() == currentIndex) {

                    return startingFSM;
                }

            */
        }

    }


    private static REfiniteStateMachine term(REfiniteStateMachine startingFSM) throws Exception {
        REfiniteStateMachine factorsFSM = factor();

        if (pattern.length() == currentIndex) {
            //if factor returns and we are at the end append the factorsFSM and return
            startingFSM.appendFiniteStateMachine(factorsFSM);
            return startingFSM;
        }
        if (pattern.charAt(currentIndex) == '*') {
            currentStateNumber = startingFSM.closure(factorsFSM, currentStateNumber);
            currentIndex++;
            return startingFSM;
        } else {
            startingFSM.appendFiniteStateMachine(factorsFSM);
            return startingFSM;
        }

    }

    private static REfiniteStateMachine factor() throws Exception {
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

            REfiniteStateMachine newState = new REfiniteStateMachine(currentStateNumber, literalList);
            currentStateNumber++;
            return newState;
        } else if (pattern.charAt(currentIndex) == (char) 92) {
            currentIndex++;
            Character currentChar = pattern.charAt(currentIndex);
            currentIndex++;

            REfiniteStateMachine newState = new REfiniteStateMachine(currentStateNumber, currentChar);
            currentStateNumber++;
            return newState;

        } else if (isliteral()) {

            Character currentChar = pattern.charAt(currentIndex);
            currentIndex++;
            REfiniteStateMachine newState = new REfiniteStateMachine(currentStateNumber, currentChar);
            currentStateNumber++;
            return newState;

        } else if (pattern.charAt(currentIndex) == '.') {
            Character currentChar = pattern.charAt(currentIndex);
            currentIndex++;

            REfiniteStateMachine newState = new REfiniteStateMachine(currentStateNumber, currentChar);
            currentStateNumber++;
            return newState;

        } else {
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
