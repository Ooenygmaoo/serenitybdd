package starter.ui.registration;


import net.serenitybdd.screenplay.targets.Target;

public class SummaryPage {

    public static Target LABEL_NAME = Target.the("label name").locatedBy("//strong[contains(text(),'Nombre')]/parent::p");
    public static Target LABEL_LASTNAME = Target.the("label last name")
            .locatedBy("//strong[contains(text(),'Apellido')]/parent::p");

}
