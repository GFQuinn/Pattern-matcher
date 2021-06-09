public class REstateBranching extends REstate{

    REstateBranching(int stateNumber) {
        super(stateNumber);
    }
    REstateBranching(int stateNumber, int nextStateOne, int nextStatetwo)
    {
        super(stateNumber, nextStateOne, nextStatetwo);
    }

    public void dump()
    {
        System.out.println(stateNumber + "   " + "BR" + "   " + nextStateOne + "    " + nextStatetwo);
    }

}
