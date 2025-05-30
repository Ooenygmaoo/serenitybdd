package starter.tasks.registration;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import starter.ui.registration.RegisterForm;

public class DoNotSendAllRequieredInformation implements Task {
    private final String expectedName;
    private final String expectedLastname;

    public DoNotSendAllRequieredInformation(String expectedName, String expectedLastname) {

        this.expectedName = expectedName;
        this.expectedLastname = expectedLastname;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Enter.theValue(expectedName).into(RegisterForm.INPUT_NAME),
                Enter.theValue(expectedLastname).into(RegisterForm.INPUT_LAST_NAME),
                Click.on(RegisterForm.BUTTON_SAVE));
    }
}
