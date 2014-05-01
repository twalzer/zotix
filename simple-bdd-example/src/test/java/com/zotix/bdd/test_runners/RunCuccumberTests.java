package com.zotix.bdd.test_runners;

import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by akhettar on 10/04/2014.
 */

@RunWith(Cucumber.class)
@Cucumber.Options(
        format = {"pretty","html:target/cucumber-html-report"},
        glue="com.zotix.bdd", tags="~@Dev15",
        features = "src/test/resources/features")
public class RunCuccumberTests {
}
