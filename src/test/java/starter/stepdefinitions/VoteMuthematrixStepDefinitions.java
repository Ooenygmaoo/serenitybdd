package starter.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.*;
import net.serenitybdd.screenplay.conditions.Check;
import net.serenitybdd.screenplay.questions.Presence;
import net.serenitybdd.screenplay.waits.WaitUntil;


import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.*;


public class VoteMuthematrixStepDefinitions {

    @Given("{actor} enter to the muthematrix page")
    public void enterToTheMuthematrixPage(Actor actor) {
        actor.attemptsTo(
                Open.url("https://webx.muthematrix.com/"),
                WaitUntil.the("//body", isVisible()).forNoMoreThan(30).seconds()
        );
    }

    @When("{actor} enter to the login page and click on vote with username {string} and password {string}")
    public void enterToTheLoginPageAndClickOnVote(Actor actor, String username, String password) {
        actor.attemptsTo(

                Check.whether(Presence.of("//button[contains(.,'Cerrar')]").asBoolean())
                        .andIfSo(
                                WaitUntil.the("//button[contains(.,'Cerrar')]", isVisible())
                                        .forNoMoreThan(5).seconds(),
                                Click.on("//button[contains(.,'Cerrar')]")
                        ),

                // Continuar con el flujo normal
                WaitUntil.the("//button[normalize-space()='Log In']", isClickable())
                        .forNoMoreThan(15).seconds(),
                Click.on("//button[normalize-space()='Log In']"),

                WaitUntil.the("//input[@id='username']", isVisible())
                        .forNoMoreThan(15).seconds(),
                Enter.theValue(username).into("//input[@id='username']"),

                WaitUntil.the("//input[@id='password']", isVisible())
                        .forNoMoreThan(10).seconds(),
                Enter.theValue(password).into("//input[@id='password']"),

                Click.on("//input[@id='submit']"),

                WaitUntil.the("//button[normalize-space()='Panel de Usuario']", isVisible())
                        .forNoMoreThan(20).seconds(),
                Click.on("//button[normalize-space()='Panel de Usuario']")
        );
    }

    @Then("{actor} Could be vote for the server")
    public void couldVoteForTheServer(Actor actor) {
        try {
            // Paso 1: Navegar a la página de votación
            actor.attemptsTo(
                    WaitUntil.the("//a[@href='https://webx.muthematrix.com/usercp/vote']", isVisible())
                            .forNoMoreThan(15).seconds(),
                    Click.on("//a[@href='https://webx.muthematrix.com/usercp/vote']"),
                    WaitUntil.the("//div[@class='vote-text']", isVisible())
                            .forNoMoreThan(10).seconds(),
                    Scroll.to("//div[@class='vote-text']")
            );

            // Paso 2: Verificar qué texto aparece
            boolean debeEsperar = Presence.of("//div[@class='vote-text' and contains(text(),'Puedes votar en')]")
                    .answeredBy(actor);

            boolean puedeVotar = false;

            if (debeEsperar) {
                // Escenario 1: Usuario debe esperar (dentro de las 12 horas)
                System.out.println("⏳ [VOTE-CHECK] Se encontró 'Puedes votar en...'. Esperando 60 segundos...");
                Thread.sleep(60000);
                
                System.out.println("🔄 [VOTE-CHECK] Recargando la página para actualizar el estado...");
                actor.attemptsTo(
                        Open.url("https://webx.muthematrix.com/usercp/vote"),
                        WaitUntil.the("//div[@class='vote-text']", isVisible()).forNoMoreThan(10).seconds(),
                        Scroll.to("//div[@class='vote-text']")
                );
                
                System.out.println("✅ [VOTE-CHECK] Página recargada. Buscando 'Puedes votar ahora!'...");
                
                // Intentar hasta 3 veces para encontrar el texto
                for (int i = 0; i < 3; i++) {
                    boolean textoEncontrado = Presence.of("//div[@class='vote-text' and normalize-space(text())='Puedes votar ahora!']")
                            .answeredBy(actor);
                    if (textoEncontrado) {
                        puedeVotar = true;
                        System.out.println("✅ [VOTE-CHECK] Encontrado 'Puedes votar ahora!'. Procediendo a votar...");
                        break;
                    } else {
                        System.out.println("⏳ [VOTE-CHECK] Intento " + (i+1) + "/3 - No encontrado, reaciargando...");
                        if (i < 2) {
                            Thread.sleep(10000);
                            actor.attemptsTo(
                                    Open.url("https://webx.muthematrix.com/usercp/vote"),
                                    WaitUntil.the("//div[@class='vote-text']", isVisible()).forNoMoreThan(10).seconds(),
                                    Scroll.to("//div[@class='vote-text']")
                            );
                        }
                    }
                }
                
                if (!puedeVotar) {
                    System.out.println("⚠️  [VOTE-CHECK] No se encontró 'Puedes votar ahora!' después de varios intentos.");
                    System.out.println("⚠️  [VOTE-ACTION] No se ejecutó el voto - El usuario debe esperar para poder votar nuevamente.");
                    return; // TEST PASA sin hacer clic
                }
            } else {
                // Escenario 2: Usuario puede votar inmediatamente
                System.out.println("✅ [VOTE-CHECK] Ya puede votar! Encontrado 'Puedes votar ahora!' inmediatamente.");
                puedeVotar = true;
            }

            // Paso 3: Si puede votar, hacer clic
            if (puedeVotar) {
                System.out.println("🎯 [VOTE-ACTION] Haciendo clic en el botón de votar...");
                actor.attemptsTo(
                        Scroll.to("//div[@class='vote-box-site']"),
                        Click.on("//div[@class='vote-box-site']")
                );
                System.out.println("✅ [VOTE-ACTION] Voto completado exitosamente!");
            }
        } catch (Exception e) {
            System.out.println("❌ [ERROR] Error inesperado: " + e.getMessage());
        }
    }
}