package starter.tasks.registration;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Switch;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.targets.Target;
import starter.ui.registration.SummaryPage;

public class CheckNewAccountCreated implements Task {
    private final String expectedName;
    private final String expectedLastname;

    public CheckNewAccountCreated(String expectedName, String expectedLastname) {

        this.expectedName = expectedName;
        this.expectedLastname = expectedLastname;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Switch.toWindowTitled("Summary"),
                Ensure.that(SummaryPage.LABEL_NAME).text()
                        .containsIgnoringCase(expectedName),

                Ensure.that(SummaryPage.LABEL_LASTNAME).text().containsIgnoringCase(expectedLastname));

    }
}
