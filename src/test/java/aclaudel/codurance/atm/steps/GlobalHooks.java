package aclaudel.codurance.atm.steps;

import aclaudel.codurance.atm.context.AtmContext;
import io.cucumber.java.Before;

public class GlobalHooks {

    private final AtmContext atmContext;

    public GlobalHooks(AtmContext atmContext) {
        this.atmContext = atmContext;
    }

    @Before
    public void before() {
        atmContext.setup();
    }
}
