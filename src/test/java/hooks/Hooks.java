package hooks;

import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.thucydides.model.environment.SystemEnvironmentVariables;
import net.thucydides.model.util.EnvironmentVariables;

public class Hooks {

    @Before
    public void setupDriver() {
        EnvironmentVariables variables = SystemEnvironmentVariables.createEnvironmentVariables();
        String driver = variables.getProperty("webdriver.driver");
        String remoteUrl = variables.getProperty("webdriver.remote.url");

        // Solo configurar WebDriverManager si NO estamos usando remote driver (BrowserStack)
        if (remoteUrl == null || remoteUrl.isEmpty()) {
            System.out.println("🔧 Configurando driver local: " + driver);

            if ("chrome".equalsIgnoreCase(driver)) {
                WebDriverManager.chromedriver().setup();
            } else if ("firefox".equalsIgnoreCase(driver)) {
                WebDriverManager.firefoxdriver().setup();
            } else if ("edge".equalsIgnoreCase(driver)) {
                WebDriverManager.edgedriver().setup();
            }
        } else {
            System.out.println("☁️  Usando BrowserStack - Remote WebDriver");
            System.out.println("🌎 Geolocalización: Colombia");
        }
    }
}
