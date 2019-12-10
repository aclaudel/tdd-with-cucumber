package aclaudel.codurance.atm.steps;

import aclaudel.codurance.atm.context.AtmContext;
import aclaudel.codurance.atm.context.AtmMongoDBContext;
import aclaudel.codurance.atm.context.Factory;
import io.cucumber.java.Before;

public class GlobalHooks {

    private final AtmContext atmContext;

    public GlobalHooks(AtmMongoDBContext atmContext) {
        this.atmContext = Factory.getAtmContext();;
    }

    @Before
    public void before() {
        atmContext.setup();
    }
}
