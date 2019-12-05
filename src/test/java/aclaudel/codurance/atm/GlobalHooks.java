package aclaudel.codurance.atm;

import aclaudel.codurance.context.AtmContext;
import io.cucumber.java.Before;

public class GlobalHooks extends AtmContext {

    @Before
    public void before() {
        super.before();
    }

}
