package aclaudel.codurance.atm.context;

import io.cucumber.java.Before;

public class GlobalHooks {

    @Before
    public void before() {
        AtmContext.setup();
    }
}
