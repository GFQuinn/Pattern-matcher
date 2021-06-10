public class REstateBranching extends REstate{

    REstateBranching(int stateNumber) {
        super(stateNumber);
    }
    REstateBranching(int stateNumber, int nextStateOne, int nextStatetwo)
    {
        super(stateNumber, nextStateOne, nextStatetwo);
    }
    @Override
    public void dump()
    {
        System.out.println(stateNumber + "   " + "BR" + "   " + nextStateOne + "    " + nextStatetwo);
    }

    @Override
    public String getAttributes() {

        String attributesOne = String.valueOf(stateNumber);
        String attributesTwo = String.valueOf(nextStateOne);
        String attributesThree = String.valueOf(nextStatetwo);

        String attributes = attributesOne + null + attributesTwo + null + attributesThree;
        return attributes;
    }
    @Override
    public String toString()
    {
        return "Branching";
    }
}
