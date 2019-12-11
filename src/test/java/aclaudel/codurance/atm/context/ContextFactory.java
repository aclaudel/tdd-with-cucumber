package aclaudel.codurance.atm.context;

import aclaudel.codurance.atm.context.infrastructure.MockAccountRepositoryContext;
import aclaudel.codurance.atm.context.infrastructure.MongoDBAccountRepositoryContext;

public class ContextFactory {

    public static final String ATM_CONTEXT_PROPERTY = "atm.context";
    public static final String MONGO_CONTEXT = "mongo";

    private final AtmContext atmContext;
    private final ErrorContext errorContext;

    public ContextFactory() {
        atmContext = getAtmContext();
        errorContext = new ErrorContext();
    }

    private AtmContext getAtmContext() {
        if(isMongoContext()) {
            return new AtmContext(new MongoDBAccountRepositoryContext());
        } else {
            return new AtmContext(new MockAccountRepositoryContext());
        }
    }

    private boolean isMongoContext() {
        return MONGO_CONTEXT.equals(System.getProperty(ATM_CONTEXT_PROPERTY));
    }

    public AtmContext atmContext() {
        return  atmContext;
    }

    public ErrorContext errorContext() {
        return errorContext;
    }
}
