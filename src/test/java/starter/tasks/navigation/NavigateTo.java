package starter.tasks.navigation;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;

public class NavigateTo {

    public static Performable navigateTo() {
        return Task.where("{0},navigates to login page",
                Open.url("file:///Users/DLOPE26/Downloads/recursos%202/login/login.html"));
    }
}
