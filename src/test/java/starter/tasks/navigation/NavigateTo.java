package starter.tasks.navigation;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import net.thucydides.model.environment.SystemEnvironmentVariables;
import net.thucydides.model.util.EnvironmentVariables;

public class NavigateTo {

    public static Performable navigateToLoginPage() {
        EnvironmentVariables variables = SystemEnvironmentVariables.createEnvironmentVariables();
        String baseUrl = variables.getProperty("webdriver.base.url");

        // Verificación de la URL
        System.out.println(">>> BASE URL (NavigateTo): " + baseUrl);

        if (baseUrl == null || baseUrl.isEmpty()) {
            throw new IllegalStateException("No se encontró 'webdriver.base.url' en serenity.conf");
        }

        return Task.where("{0} navigates to login page", Open.url(baseUrl));
    }
}
