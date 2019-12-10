package aclaudel.codurance.atm.context;

public class Context {

    private final AtmContext ATM_CONTEXT = new AtmMockContext();
    private final ErrorContext ERROR_CONTEXT = new ErrorContext();

    public ErrorContext errorContext() {
        return ERROR_CONTEXT;
    }

    public AtmContext atmContext() {
        return  ATM_CONTEXT;
    }
}
