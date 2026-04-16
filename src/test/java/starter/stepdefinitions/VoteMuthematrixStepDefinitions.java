package starter.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.*;
import net.serenitybdd.screenplay.conditions.Check;
import net.serenitybdd.screenplay.questions.Presence;
import net.serenitybdd.screenplay.waits.WaitUntil;


import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.*;


public class VoteMuthematrixStepDefinitions {

    @Given("{actor} enter to the muthematrix page")
    public void enterToTheMuthematrixPage(Actor actor) {
        actor.attemptsTo(
                Open.url("https://webx.muthematrix.com/"),
                WaitUntil.the("//body", isVisible()).forNoMoreThan(30).seconds()
        );
    }

    @When("{actor} enter to the login page and click on vote with username {string} and password {string}")
    public void enterToTheLoginPageAndClickOnVote(Actor actor, String username, String password) {
        actor.attemptsTo(

                Check.whether(Presence.of("//button[contains(.,'Cerrar')]").asBoolean())
                        .andIfSo(
                                WaitUntil.the("//button[contains(.,'Cerrar')]", isVisible())
                                        .forNoMoreThan(5).seconds(),
                                Click.on("//button[contains(.,'Cerrar')]")
                        ),

                // Continuar con el flujo normal
                WaitUntil.the("//button[normalize-space()='Log In']", isClickable())
                        .forNoMoreThan(15).seconds(),
                Click.on("//button[normalize-space()='Log In']"),

                WaitUntil.the("//input[@id='username']", isVisible())
                        .forNoMoreThan(15).seconds(),
                Enter.theValue(username).into("//input[@id='username']"),

                WaitUntil.the("//input[@id='password']", isVisible())
                        .forNoMoreThan(10).seconds(),
                Enter.theValue(password).into("//input[@id='password']"),

                Click.on("//input[@id='submit']"),

                WaitUntil.the("//button[normalize-space()='Panel de Usuario']", isVisible())
                        .forNoMoreThan(20).seconds(),
                Click.on("//button[normalize-space()='Panel de Usuario']")
        );
    }

    @Then("{actor} Could be vote for the server")
    public void couldVoteForTheServer(Actor actor) {
        actor.attemptsTo(
                WaitUntil.the("//a[@href='https://webx.muthematrix.com/usercp/vote']", isVisible())
                        .forNoMoreThan(15).seconds(),
                Click.on("//a[@href='https://webx.muthematrix.com/usercp/vote']"),

                WaitUntil.the("//div[@class='vote-box-site']", isVisible())
                        .forNoMoreThan(15).seconds(),
                Scroll.to("//div[@class='vote-box-site']"),
                Click.on("//div[@class='vote-box-site']")
        );
    }
}