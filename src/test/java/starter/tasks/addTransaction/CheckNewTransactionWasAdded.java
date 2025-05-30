package starter.tasks.addTransaction;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.ensure.Ensure;
import starter.questions.GetTransactionInfo;
import starter.ui.addTransaction.AddtransactionPage;

public class CheckNewTransactionWasAdded implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Ensure.that(GetTransactionInfo.getDescription()).contains("Testing Description"),

                Ensure.that(AddtransactionPage.TRANSACTIONS_TABLE)
                        .text().contains("500")

        );
    }
}
