package aclaudel.codurance.atm.context;

import aclaudel.codurance.atm.context.tmp.MongoDBAccountRepositoryContext;

public class AtmMongoDBContext extends AtmContext {

    public AtmMongoDBContext() {
        super(new MongoDBAccountRepositoryContext());
    }
}
