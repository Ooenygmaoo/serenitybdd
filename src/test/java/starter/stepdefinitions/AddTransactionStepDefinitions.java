package starter.stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Open;
import starter.models.LoginModel;
import starter.models.TransactionModel;
import starter.tasks.addTransaction.AddTransaction;
import starter.tasks.addTransaction.CheckNewTransactionWasAdded;
import starter.tasks.login.Login;
import starter.tasks.navigation.NavigateTo;
import starter.util.GetInfoFromTable;


public class AddTransactionStepDefinitions {


    @Given("{actor} is logged into the application")
    public void isloggedIntoTheapplication(Actor actor, DataTable userLogin) {

      LoginModel loginModel= GetInfoFromTable.GetLoginCredentials(userLogin);
        actor.attemptsTo(
                NavigateTo.navigateTo(),
                new Login(loginModel.getUsername(),loginModel.getPassword())

        );

    }

    @When("{actor} enters the required information for the new transaction")
    public void entersTheRequiredInformationForTheNewTransaction(Actor actor,DataTable transactionInfo) {

        TransactionModel transactionModel = GetInfoFromTable.GetTransactionInfo(transactionInfo);
        actor.attemptsTo(
                AddTransaction.withInfo(
                        transactionModel.getTransactionDate(),
                        transactionModel.getTransactionAmmount(),
                        transactionModel.getTransactionDescription())

        );

    }

    @Then("{actor} should see a new transaction in the transaction list with correct details")
    public void shouldSeeaNewTransactionInTheTransactionListWithCorrectDetails(Actor actor) {

        actor.attemptsTo(
                new CheckNewTransactionWasAdded()
        );
    }


}
