public class REstatePeroid extends REstate{

    REstatePeroid(int stateNumber, int nextStateOne, int nextStatetwo)
    {
        super(stateNumber, nextStateOne, nextStatetwo);
    }

    @Override
    public void dump() {
        System.out.println(stateNumber + "   " + "PR" + "   " + nextStateOne + "    " + nextStatetwo);
    }

    @Override
    public String getAttributes() {
    return null;
    }
    public String toString()
    {
        return "Period";

    }
}
