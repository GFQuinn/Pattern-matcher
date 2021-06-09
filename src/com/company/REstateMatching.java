package com.company;

public class REstateMatching extends REstate{

    protected Character character;

    REstateMatching(int stateNumber, Character character) {
        super(stateNumber);
        this.character = character;
    }
    public void dump()
    {
        System.out.println(stateNumber + "   " +character + "   " + nextStateOne + "    " + nextStatetwo);
    }
}
