package aclaudel.codurance.atm.steps;

import aclaudel.codurance.atm.context.AtmContext;
import aclaudel.codurance.atm.context.Contexts;
import io.cucumber.java.Before;

public class GlobalHooks {

    private final AtmContext atmContext = Contexts.atmContext();

    @Before
    public void before() {
        atmContext.setup();
    }
}
