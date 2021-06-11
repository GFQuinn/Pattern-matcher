public class REstateCanNotMatch extends REstate{

    Character canNotMatchSymbol = '!';

    REstateCanNotMatch(int stateNumber) {
        super(stateNumber);
    }

    @Override
    public void dump() {System.out.println(stateNumber + "   canNotMatch");
    }

    @Override
    public String getAttributes() {
        String attributesOne = String.valueOf(stateNumber);
        String attributesTwo = canNotMatchSymbol.toString();
        String attributesThree = String.valueOf(nextStateOne);
        String attributesFour = String.valueOf(nextStatetwo);

        String attributes = attributesOne + null + attributesTwo + null + attributesThree + null + attributesFour;
        return attributes;
    }
    @Override
    public String toString()
    {
        return "CanNotMatch";
    }

}
