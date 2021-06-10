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
    public boolean checkContains(char character){

        if(choices.indexOf(character) == -1)
        {
            return false;
        }
        else
        {
            return true;
        }

    }

    @Override
    public void dump() {
        System.out.println(stateNumber + "   " +choices + "   " + nextStateOne + "    " + nextStatetwo);
    }
    @Override
    public String getAttributes() {
        String attributesOne = String.valueOf(stateNumber);
        String attributesTwo = "[" + choices + "]";
        String attributesThree = String.valueOf(nextStateOne);
        String attributesFour = String.valueOf(nextStatetwo);

        String attributes = attributesOne + null + attributesTwo + null + attributesThree + null + attributesFour;
        return attributes;
    }

}
