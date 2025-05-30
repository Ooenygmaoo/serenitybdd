package starter.questions;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import starter.ui.addTransaction.AddtransactionPage;

public class GetTransactionInfo {

    public static Question <String> getDescription(){
        return Question.about("Transaction description").answeredBy(

                actor -> BrowseTheWeb.as(actor).textOf(AddtransactionPage.TRANSACTIONS_TABLE_XPATH)
        );
    }
}
