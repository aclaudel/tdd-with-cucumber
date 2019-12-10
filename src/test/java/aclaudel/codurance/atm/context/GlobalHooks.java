package aclaudel.codurance.atm.context;

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
