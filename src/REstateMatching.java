public class REstateMatching extends REstate{

    protected Character character;

    REstateMatching(int stateNumber, Character character) {
        super(stateNumber);
        this.character = character;
    }
    REstateMatching(int stateNumber, Character character, int nextStateOne, int nextStateTwo) {
        super(stateNumber, nextStateOne, nextStateTwo);
        this.character = character;
    }
    @Override
    public void dump()
    {
        System.out.println(stateNumber + "   " +character + "   " + nextStateOne + "    " + nextStatetwo);
    }
    @Override
    public String getAttributes() {
        String attributesOne = character.toString();
        String attributesTwo = String.valueOf(stateNumber);
        String attributesThree = String.valueOf(nextStateOne);
        String attributesFour = String.valueOf(nextStatetwo);

        String attributes = attributesOne + null + attributesTwo + null + attributesThree + null + attributesFour;
        return attributes;
    }
}
