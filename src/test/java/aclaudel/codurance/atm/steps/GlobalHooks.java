package aclaudel.codurance.atm.steps;

import aclaudel.codurance.atm.context.AtmContext;
import aclaudel.codurance.atm.context.Context;
import io.cucumber.java.Before;

public class GlobalHooks {

    private final AtmContext atmContext;

    public GlobalHooks(Context context) {
        atmContext = context.atmContext();
    }

    @Before
    public void before() {
        atmContext.setup();
    }
}
