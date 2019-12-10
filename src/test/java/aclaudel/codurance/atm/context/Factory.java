package aclaudel.codurance.atm.context;

public class Factory {

    private static final AtmContext ATM_CONTEXT = new AtmMockContext();

    public static AtmContext getAtmContext() {
        return  ATM_CONTEXT;
    }

}
