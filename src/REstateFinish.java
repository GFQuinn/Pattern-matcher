public class REstateFinish extends REstate {

    Byte finishingSymbol = -1;

    REstateFinish(int stateNumber) {
        super(stateNumber);

    }

    REstateFinish(int stateNumber, int nextStateOne, int nextStatetwo) {
        super(stateNumber, nextStateOne, nextStatetwo);
    }

    public void dump() {
        System.out.println(stateNumber + "   Finish");
    }

    @Override
    public String getAttributes() {
        String attributesOne = String.valueOf(stateNumber);
        String attributesTwo = finishingSymbol.toString();
        String attributesThree = String.valueOf(nextStateOne);
        String attributesFour = String.valueOf(nextStatetwo);

        String attributes = attributesOne + null + attributesTwo + null + attributesThree + null + attributesFour;
        return attributes;
    }
    @Override
    public String toString()
    {
        return "Finish";
    }

}
