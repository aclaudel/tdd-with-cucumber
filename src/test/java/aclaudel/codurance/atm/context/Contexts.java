package aclaudel.codurance.atm.context;

public class Contexts {

    private static final AtmContext ATM_CONTEXT = new AtmMockContext();
    private static final ErrorContext ERROR_CONTEXT = new ErrorContext();

    public static AtmContext atmContext() {
        return  ATM_CONTEXT;
    }

    public static ErrorContext errorContext() {
        return ERROR_CONTEXT;
    }

}
