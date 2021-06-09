package com.company;

public class REstateFinish extends REstate{

    Byte finishingSymbol = -1;

    REstateFinish(int stateNumber) {
        super(stateNumber);

    }

    public void dump()
    {
        System.out.println(stateNumber + "   Finish");
    }
}
