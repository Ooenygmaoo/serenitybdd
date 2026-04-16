package hooks;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.thucydides.model.environment.SystemEnvironmentVariables;
import net.thucydides.model.util.EnvironmentVariables;

public class Hooks {

    @Before(order = 0)
    public void setupDriver(Scenario scenario) {
        EnvironmentVariables variables = SystemEnvironmentVariables.createEnvironmentVariables();
        String driver = variables.getProperty("webdriver.driver");
        String environment = variables.getProperty("environment");

        // Si estamos en BrowserStack
        if ("browserstack".equalsIgnoreCase(environment) || "remote".equalsIgnoreCase(driver)) {
            System.out.println("☁️  Configurando BrowserStack");
            System.out.println("📱 Scenario: " + scenario.getName());
            System.out.println("🏷️  Tags: " + scenario.getSourceTagNames());

            // Verificar credenciales
            String user = System.getProperty("BROWSERSTACK_USER");
            String key = System.getProperty("BROWSERSTACK_KEY");

            if (user != null && key != null) {
                System.out.println("✅ Credenciales BrowserStack encontradas");
                System.out.println("👤 Usuario: " + user);
            } else {
                System.out.println("⚠️  Credenciales BrowserStack NO encontradas en system properties");
            }

            // Mostrar plataforma según tags
            if (scenario.getSourceTagNames().contains("@windows")) {
                System.out.println("🖥️  Plataforma: Windows 10 + Chrome Latest");
            } else if (scenario.getSourceTagNames().contains("@mac")) {
                System.out.println("🍎 Plataforma: Mac OS Monterey + Safari 15.6");
            }

            System.out.println("🌎 Geolocalización: Colombia");
        } else {
            // Configurar driver local
            System.out.println("🔧 Configurando driver local: " + driver);

            if ("chrome".equalsIgnoreCase(driver)) {
                WebDriverManager.chromedriver().setup();
            } else if ("firefox".equalsIgnoreCase(driver)) {
                WebDriverManager.firefoxdriver().setup();
            } else if ("edge".equalsIgnoreCase(driver)) {
                WebDriverManager.edgedriver().setup();
            }
        }
    }
}
