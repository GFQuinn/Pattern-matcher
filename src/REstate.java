public abstract class REstate {
    protected int stateNumber;
    protected int nextStateOne;
    protected int nextStatetwo;

    REstate(int stateNumber, int nextStateOne, int nextStatetwo)
    {
        this.stateNumber = stateNumber;
        this.nextStateOne = nextStateOne;
        this.nextStatetwo = nextStatetwo;

    }
    REstate(int stateNumber)
    {
        this.stateNumber = stateNumber;
    }

    public int getStateNumber() {
        return stateNumber;
    }

    public void setNextStateOne(int nextStateOne) {
        this.nextStateOne = nextStateOne;
    }

    public void setNextStatetwo(int nextStatetwo) {
        this.nextStatetwo = nextStatetwo;
    }

    public void setBothNextStates(int nextStateNumber){
        this.nextStateOne = nextStateNumber;
        this.nextStatetwo = nextStateNumber;
    }

    public abstract void dump();

    public abstract String getAttributes();
}
