package aclaudel.codurance.atm.steps;

import aclaudel.codurance.atm.context.AtmContext;
import aclaudel.codurance.atm.context.AtmMongoContext;
import io.cucumber.java.Before;

public class GlobalHooks {

    private final AtmContext atmContext;

    public GlobalHooks(AtmMongoContext atmContext) {
        this.atmContext = atmContext;
    }

    @Before
    public void before() {
        atmContext.setup();
    }
}
