package starter.stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;

import net.serenitybdd.screenplay.actions.Open;

import starter.tasks.registration.CheckNewAccountCreated;
import starter.tasks.registration.DoNotSendAllRequieredInformation;
import starter.tasks.registration.RegisterUser;
import starter.tasks.registration.ShouldSeeAllFieldsAreRequired;
import starter.ui.registration.RegisterPage;

import java.net.URISyntaxException;
import java.util.List;

public class RegistrationStepsDefinitions {

    String expectedName = "";
    String expectedLastname = "";
    String expectedAge = "";
    String expectedEmail = "";
    String expectedCountry = "";

    @Given("{actor} wants to sign in the application")
    public void wantsToSignUpInTheApplication(Actor actor) {


        actor.attemptsTo(
                Open.browserOn(new RegisterPage())
        );


    }

    @When("{actor} sends the required information to sign up")
    public void sendsRequiredInformationToSignUp(Actor actor, DataTable userInfo) throws URISyntaxException {
        List<List<String>> rows = userInfo.asLists(String.class);
        for (List<String> columns : rows){
            expectedName = columns.get(0);
            expectedLastname = columns.get(1);
            expectedAge = columns.get(2);
            expectedEmail = columns.get(3);
            expectedCountry = columns.get(4);
        }

            actor.attemptsTo(
                    new RegisterUser(expectedName, expectedLastname, expectedAge, expectedEmail, expectedCountry)


            );


    }

    @Then("{actor} should have a new account created")
    public void shouldHaveANewAccountCreated(Actor actor) {
        actor.attemptsTo(
                new CheckNewAccountCreated(expectedName, expectedLastname));

    }

    @When("{actor} does not send the required information")
    public void doesNotSendTheRequiredInformation(Actor actor) {
        actor.attemptsTo(
                new DoNotSendAllRequieredInformation(expectedName, expectedLastname)
        );

    }

    @Then("{actor} should be told all fields are required")
    public void shouldBeToldAllFieldsAreRequired(Actor actor) {
        actor.attemptsTo(
                new ShouldSeeAllFieldsAreRequired()


        );
    }
}
