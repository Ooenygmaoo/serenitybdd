package hooks;

import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Hooks {

    @Before
    public void setupDriver() {
        WebDriverManager.chromedriver().setup();
    }
}
