package hooks;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.thucydides.model.environment.SystemEnvironmentVariables;
import net.thucydides.model.util.EnvironmentVariables;

public class Hooks {

    @Before
    public void setupDriver(Scenario scenario) {
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
            System.out.println("📱 Scenario: " + scenario.getName());

            // Configurar plataforma según tags
            if (scenario.getSourceTagNames().contains("@windows")) {
                System.out.println("🖥️  Plataforma: Windows 10 + Chrome Latest");
                System.setProperty("bstack.os", "Windows");
                System.setProperty("bstack.osVersion", "10");
                System.setProperty("bstack.browserName", "Chrome");
                System.setProperty("bstack.browserVersion", "latest");
                System.setProperty("bstack.sessionName", "Vote Test - Windows Chrome - " + scenario.getName());
            } else if (scenario.getSourceTagNames().contains("@mac")) {
                System.out.println("🍎 Plataforma: Mac OS Monterey + Safari 15.6");
                System.setProperty("bstack.os", "OS X");
                System.setProperty("bstack.osVersion", "Monterey");
                System.setProperty("bstack.browserName", "Safari");
                System.setProperty("bstack.browserVersion", "15.6");
                System.setProperty("bstack.sessionName", "Vote Test - Mac Safari - " + scenario.getName());
            }

            System.out.println("🌎 Geolocalización: Colombia");
        }
    }
}
