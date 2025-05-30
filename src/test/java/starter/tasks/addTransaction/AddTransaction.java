package starter.tasks.addTransaction;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import starter.ui.addTransaction.AddtransactionPage;

public class AddTransaction {

    public static Performable withInfo(String transactionDate, String transactionAmmount, String transactionDescription) {
        return Task.where("{0},adds a new transaction",
                Click.on(AddtransactionPage.BUTTON_ADD_TRANSACTION),
                Enter.theValue(transactionDate).into(AddtransactionPage.INPUT_DATE),
                Enter.theValue(transactionAmmount).into(AddtransactionPage.INPUT_AMOUNT),
                Enter.theValue(transactionDescription).into(AddtransactionPage.INPUT_DESCRIPTION),
                Click.on(AddtransactionPage.BUTTON_SAVE)
        );
    }
}
