package aclaudel.codurance.atm;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = "pretty",
        strict = true,
        tags = "(@delivery and (@wip or @done))")
public class AtmCucumberRunner { }
