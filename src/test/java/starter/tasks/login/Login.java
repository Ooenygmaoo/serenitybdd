package starter.tasks.login;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import starter.ui.login.LoginPage;

public class Login implements Task {
      private final String username;
      private  final  String password;
    public Login(String username, String password) {
       this.username=username;
       this.password=password;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue("user").into(LoginPage.INPUT_USERNAME),
                Enter.theValue("pass").into(LoginPage.INPUT_PASSWORD),
                Click.on(LoginPage.BUTTON_LOG_IN)

        );
    }
}
