package aclaudel.codurance.atm.context;

public class Context {

    public static final String ATM_CONTEXT_PROPERTY = "atm.context";
    public static final String MONGO_CONTEXT = "mongo";

    private final AtmContext atmContext;
    private final ErrorContext errorContext;

    public Context() {
        if(MONGO_CONTEXT.equals(System.getProperty(ATM_CONTEXT_PROPERTY))) {
            atmContext = new AtmMongoDBContext();
        } else {
            atmContext = new AtmMockContext();
        }
        errorContext = new ErrorContext();
    }

    public AtmContext atmContext() {
        return  atmContext;
    }

    public ErrorContext errorContext() {
        return errorContext;
    }
}
