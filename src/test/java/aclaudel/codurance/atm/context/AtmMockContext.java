package aclaudel.codurance.atm.context;

import aclaudel.codurance.atm.context.tmp.MockAccountRepositoryContext;

public class AtmMockContext extends AtmContext {

    public AtmMockContext() {
        super(new MockAccountRepositoryContext());
    }
}
