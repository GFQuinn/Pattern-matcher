public class REstateFinish extends REstate{

    Byte finishingSymbol = -1;

    REstateFinish(int stateNumber) {
        super(stateNumber);

    }
    REstateFinish(int stateNumber, int nextStateOne, int nextStatetwo)
    {
        super(stateNumber, nextStateOne, nextStatetwo);
    }

    public void dump()
    {
        System.out.println(stateNumber + "   Finish");
    }
}