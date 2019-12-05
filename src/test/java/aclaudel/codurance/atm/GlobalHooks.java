package aclaudel.codurance.atm;

import aclaudel.codurance.context.AtmContext;
import io.cucumber.java.Before;

public class GlobalHooks {
    private final AtmContext atmContext = AtmContext.getInstance();

    @Before
    public void before() {
        atmContext.before();
    }
}
