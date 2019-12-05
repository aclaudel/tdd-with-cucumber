package aclaudel.codurance.atm;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = "pretty",
        strict = true)
public class AtmCucumberRunners {

    @RunWith(Cucumber.class)
    @CucumberOptions(
            plugin = "pretty",
            strict = true,
            tags = "(@delivery and (@done or @wip))")
    public static class CurrentDeliveryRunner {}

    @RunWith(Cucumber.class)
    @CucumberOptions(
            plugin = "pretty",
            strict = true,
            tags = "@wip")
    public static class WipOnlyRunner {}

    @RunWith(Cucumber.class)
    @CucumberOptions(
            plugin = "pretty",
            strict = true,
            tags = "not @todo")
    public static class AllButTodoDeliveryRunner {}

}


