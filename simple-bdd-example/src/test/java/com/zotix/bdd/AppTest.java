package com.zotix.bdd;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.PendingException;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    @Given("^a customer that Christmas is celebrated (\\d+) of December$")
    public void a_customer_that_Christmas_is_celebrated_of_December(int arg1) throws Throwable {
        // Express the Regexp above with the code you wish you had
        throw new PendingException();
    }

    @When("^a customer buys a book on (\\d+)-(\\d+)-(\\d+)$")
    public void a_customer_buys_a_book_on_(int arg1, int arg2, int arg3) throws Throwable {
        // Express the Regexp above with the code you wish you had
        throw new PendingException();
    }

    @Then("^the shipping cost should be (\\d+) euro$")
    public void the_shipping_cost_should_be_euro(int arg1) throws Throwable {
        // Express the Regexp above with the code you wish you had
        throw new PendingException();
    }
}
