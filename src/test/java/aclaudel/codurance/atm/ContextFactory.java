package aclaudel.codurance.atm;

import aclaudel.codurance.atm.infrastructure.MockAccountRepositoryContext;
import aclaudel.codurance.atm.infrastructure.MongoDBAccountRepositoryContext;

public class ContextFactory {

    public static final String ATM_CONTEXT_PROPERTY = "atm.context";
    public static final String MONGO_CONTEXT = "mongo";

    private final AtmContext atmContext;
    private final ErrorContext errorContext;

    public ContextFactory() {
        atmContext = getAtmContext();
        errorContext = new ErrorContext();
    }

    private static AtmContext getAtmContext() {
        if(isMongoContext()) {
            return new AtmContext(new MongoDBAccountRepositoryContext());
        } else {
            return defaultAtmContext();
        }
    }

    private static AtmContext defaultAtmContext() {
        return new AtmContext(new MockAccountRepositoryContext());
    }

    private static boolean isMongoContext() {
        return MONGO_CONTEXT.equals(System.getProperty(ATM_CONTEXT_PROPERTY));
    }

    public AtmContext atmContext() {
        return  atmContext;
    }

    public ErrorContext errorContext() {
        return errorContext;
    }
}
