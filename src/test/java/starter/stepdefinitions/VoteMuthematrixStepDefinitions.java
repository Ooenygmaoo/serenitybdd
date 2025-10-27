package starter.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actions.Scroll;

public class VoteMuthematrixStepDefinitions {



    @Given("{actor}enter to the muthermatrix page")
    public void entertothemuthermatrixpage (Actor actor){
        actor.attemptsTo(
                Open.url("https://webx.muthematrix.com/")
        );
    }


    @When("{actor}enter to the login page and clic on en vote")
    public void entertotheloginpageandcliconenvote(Actor actor){

        actor.attemptsTo(
                Click.on("//button[normalize-space()='Log In']"),
                Enter.theValue("slash17").into("//input[@id='username']"),
        Enter.theValue("nirvana16").into("//input[@id='password']"),
                Click.on("//input[@id='submit']"),
                Click.on("//button[normalize-space()='Panel de Usuario']")


        );




    }

    @Then("{actor}Could be vote for the server")
    public void Couldbevotefortheserver(Actor actor){

        actor.attemptsTo(
                Scroll.to("//a[@href='https://webx.muthematrix.com/usercp/vote']//div[@class='usercp_main_item']"),
                Click.on("//a[@href='https://webx.muthematrix.com/usercp/vote']//div[@class='usercp_main_item']"),
                Scroll.to("//div[@class='vote-box-site']"),
                Click.on("//div[@class='vote-box-site']")
        );

    }
}
