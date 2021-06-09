public class REstateSquareBrackets extends REstate{
    String choices;

    REstateSquareBrackets(int stateNumber, String choices) {
        super(stateNumber);
        this.choices = choices;
    }

    REstateSquareBrackets(int stateNumber, String choices, int nextStateOne, int nextStateTwo) {
        super(stateNumber, nextStateOne, nextStateTwo);
        this.choices = choices;
    }

    @Override
    public void dump() {
        System.out.println(stateNumber + "   " +choices + "   " + nextStateOne + "    " + nextStatetwo);
    }
}
