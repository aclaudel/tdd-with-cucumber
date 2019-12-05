package aclaudel.codurance.atm;

import io.cucumber.java.Before;

public class GlobalHooks {
    private final AtmContext atmContext = AtmContext.getInstance();

    @Before
    public void before() {
        atmContext.before();
    }
}
