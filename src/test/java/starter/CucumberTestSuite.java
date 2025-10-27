package starter;

import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features", // ruta a tus features
        glue = "starter.stepdefinitions",        // paquete de tus step definitions
        tags = "@webMuthematrix",                // si quieres filtrar por tag
        plugin = {"pretty"}                       // plugin de Cucumber (Serenity genera sus propios reportes)
)
public class CucumberTestSuite {
}
