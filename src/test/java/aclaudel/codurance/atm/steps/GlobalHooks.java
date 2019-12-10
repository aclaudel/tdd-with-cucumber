package aclaudel.codurance.atm.steps;

import aclaudel.codurance.atm.context.AtmMockContext;
import io.cucumber.java.Before;

public class GlobalHooks {

    private final AtmMockContext atmContext;

    public GlobalHooks(AtmMockContext atmContext) {
        this.atmContext = atmContext;
    }

    @Before
    public void before() {
        atmContext.setup();
    }
}
