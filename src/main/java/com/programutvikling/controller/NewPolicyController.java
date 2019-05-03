package com.programutvikling.controller;

import com.programutvikling.mainapp.MainApp;
import com.programutvikling.models.data.forsikring.*;
import com.programutvikling.models.data.kunde.Kunde;
import com.programutvikling.models.exceptions.InvalidNumberFormatException;
import com.programutvikling.models.utils.inputhandlers.Inputvalidator;
import com.programutvikling.models.utils.helpers.AlertHelper;
import com.programutvikling.models.viewChanger.ViewChanger;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import java.time.LocalDate;

public class NewPolicyController{


    @FXML
    BorderPane newPolicyRoot;
    @FXML
    StackPane stack;
    @FXML
    ToggleGroup choosePolicy;
    @FXML
    AnchorPane togglePage, båtPage, reisePage, bilPage, villaPage, fritidsPage;
    @FXML
    RadioButton båt, bil, reise, fritid, villa;
    @FXML
    Button topRightButton;
    @FXML
    private TextField
            fritid_adresse,
            fritid_byggeår,
            fritid_boligtype,
            fritid_byggemateriale,
            fritid_standard,
            fritid_areal,
            fritid_beløpBygning,
            fritid_beløpInnbo,
            fritid_beløp,
            fritid_prÅr,
            villa_adresse,
            villa_byggeår,
            villa_boligtype,
            villa_byggemateriale,
            villa_standard,
            villa_areal,
            villa_beløpBygning,
            villa_beløpInnbo,
            villa_beløp,
            villa_prÅr,
            båt_eier,
            båt_regNr,
            båt_typebåt,
            båt_modell,
            båt_lengde,
            båt_årsmodell,
            båt_motorType,
            båt_effekt,
            båt_forsikringssum,
            reise_forsikringOmraade,
            reise_forsikringsSum,
            reise_beløp;

    private Node active;


    @FXML
    private void initialize() {
        active = togglePage;

    }

    @FXML
    private void next() {
        if (active.getId().equals("togglePage")) {
            active.setVisible(false);

            if (båt.isSelected()) {
                active = båtPage;
            } else if (reise.isSelected()) {
                active = reisePage;
            } else if (fritid.isSelected()) {
                active = fritidsPage;
            } else if (villa.isSelected()) {
                active = villaPage;
            } else {
                active.setVisible(true);
                return;
            }
            active.setVisible(true);
            topRightButton.setText("Fullfør");
        }
        else {
            if (registrerForsikring()) {
                ViewChanger vc = new ViewChanger();
                vc.resetView("newPolicy");
                vc.setView(newPolicyRoot, "mainPage", "views/mainPage.fxml");
            }
        }
    }

    @FXML
    private void back() {
        if (active.getId().equals("togglePage")) {
            ViewChanger vc = new ViewChanger();
            vc.setView(newPolicyRoot, "mainPage", "views/mainPage.fxml");
            vc.resetView("newPolicy");
        }
        else {
            active.setVisible(false);
            active = togglePage;
            active.setVisible(true);
            topRightButton.setText("Next");
        }
    }

    private boolean registrerForsikring() {
        Forsikring forsikring = null;

        if (active.getId().equals("fritidsPage")) {
            forsikring = createFritidsBolig();
        }else if(active.getId().equals("båtPage")){
            forsikring = createBåtForsikring();
        }else if(active.getId().equals("reisePage")){
            forsikring = createReiseForsikring();
        }else if(active.getId().equals("villaPage")){
            forsikring = createVillaForsikring();
        }

        if (forsikring != null) {
            Kunde k = MainApp.getSelectedKunde();
            k.getForsikringer().add(forsikring);
            return true;
        }
        return false;
    }

    /**
     * Creates a new Villa insurance policy from the provided fields
     * @return Villa policy data object
     */
    private Forsikring createVillaForsikring() {
        VillaInnbo villa = null;

        try{
            Inputvalidator.checkIfValidNumber(villa_byggeår.getText());
            Inputvalidator.checkIfValidNumber(villa_areal.getText());
            Inputvalidator.checkIfValidNumber(villa_beløpInnbo.getText());
            Inputvalidator.checkIfValidNumber(villa_beløpBygning.getText());

            villa = new VillaInnbo();

            villa.setForsikrNr(MainApp.getSelectedKunde().getForsikrNr());
            villa.setBetingelser("Betingelser shmetingelser");
            villa.setAdresse(villa_adresse.getText());
            villa.setAvtaleOpprettet(LocalDate.now());
            villa.setByggeaar(Integer.parseInt(villa_byggeår.getText()));
            villa.setBoligtype(villa_boligtype.getText());
            villa.setByggemateriale(villa_byggemateriale.getText());
            villa.setStandard(villa_standard.getText());
            villa.setAreal(Integer.parseInt(villa_areal.getText()));
            villa.setForsikringsbeløpByggning(Double.parseDouble(villa_beløpBygning.getText()));
            villa.setForsikringsbeløpInnbo(Double.parseDouble(villa_beløpInnbo.getText()));

            villa.setPremieAnnum(villa.prisPrÅr(200));
            villa.setForsikringsSum(villa.calculateForsikringssum());

        } catch (InvalidNumberFormatException e) {
            AlertHelper.createAlert(Alert.AlertType.ERROR, "Ugyldig byggeår", e.getMessage());
        } catch (IllegalAccessException e) {
        }
        return villa;
    }

    private Forsikring createReiseForsikring() {
        Reise travelPolicy = null;

        try{
            Inputvalidator.checkIfValidNumber(reise_forsikringsSum.getText());

            travelPolicy = new Reise();

            travelPolicy.setBetingelser("Betingelser shmetingelser");
            travelPolicy.setAvtaleOpprettet(LocalDate.now());
            travelPolicy.setForsikrNr(MainApp.getSelectedKunde().getForsikrNr());
            travelPolicy.setOmraade(reise_forsikringOmraade.getText().replace(","," "));
            travelPolicy.setForsikringsSum(Double.parseDouble(reise_forsikringsSum.getText().replace(","," ")));
            travelPolicy.setPremieAnnum(travelPolicy.prisPrÅr());

        } catch (InvalidNumberFormatException e) {
            AlertHelper.createAlert(Alert.AlertType.INFORMATION, "Feil input", e.getMessage());
        } catch (IllegalAccessException e) {
            AlertHelper.createAlert(Alert.AlertType.INFORMATION, "Feil input", e.getMessage());
        }
        return travelPolicy;
    }

    private Forsikring createBåtForsikring() {
        Båt båtForsikring = null;

        try {
            Inputvalidator.checkIfValidNumber(båt_lengde.getText().replace(","," "));
            Inputvalidator.checkIfValidNumber(båt_årsmodell.getText().replace(","," "));
            Inputvalidator.checkIfValidNumber(båt_forsikringssum.getText().replace(","," "));
            Inputvalidator.checkIfValidNumber(båt_effekt.getText().replace(","," "));

            båtForsikring = new Båt();

            båtForsikring.setBetingelser("Betingelser shmetingelser");
            båtForsikring.setAvtaleOpprettet(LocalDate.now());
            båtForsikring.setForsikrNr(MainApp.getSelectedKunde().getForsikrNr());
            båtForsikring.setEier(båt_eier.getText().replace(","," "));
            båtForsikring.setTypeBåt(båt_typebåt.getText().replace(","," "));
            båtForsikring.setRegNr(båt_regNr.getText().replace(","," "));
            båtForsikring.setEffekt(båt_effekt.getText().replace(","," "));
            båtForsikring.setModell(båt_modell.getText().replace(","," "));
            båtForsikring.setLengde(Integer.parseInt(båt_lengde.getText().replace(","," ")));
            båtForsikring.setÅrsmodell(Integer.parseInt(båt_årsmodell.getText().replace(","," ")));
            båtForsikring.setMotorType(båt_motorType.getText().replace(","," "));
            båtForsikring.setForsikringsSum(Double.parseDouble(båt_forsikringssum.getText().replace(","," ")));
            båtForsikring.setPremieAnum(båtForsikring.prisPrÅr(200));

        } catch (InvalidNumberFormatException e) {
            AlertHelper.createAlert(Alert.AlertType.INFORMATION, "Feil input", e.getMessage());
        } catch (IllegalAccessException e) {
            AlertHelper.createAlert(Alert.AlertType.INFORMATION, "Feil input", e.getMessage());
        }
        return båtForsikring;
    }


    private Fritidsbolig createFritidsBolig() {

        Fritidsbolig fritidsbolig = new Fritidsbolig();

        try {
            Inputvalidator.checkIfValidNumber(fritid_byggeår.getText().replace(","," "));
            Inputvalidator.checkIfValidNumber(fritid_areal.getText().replace(","," "));
            Inputvalidator.checkIfValidNumber(fritid_beløpInnbo.getText().replace(","," "));
            Inputvalidator.checkIfValidNumber(fritid_beløpBygning.getText().replace(","," "));

            fritidsbolig.setForsikrNr(MainApp.getSelectedKunde().getForsikrNr());
            fritidsbolig.setBetingelser("Betingelser shmetingelser");
            fritidsbolig.setAvtaleOpprettet(LocalDate.now());
            fritidsbolig.setAdresse(fritid_adresse.getText().replace(","," "));
            fritidsbolig.setByggeaar(Integer.parseInt(fritid_byggeår.getText().replace(","," ")));
            fritidsbolig.setBoligtype(fritid_boligtype.getText().replace(","," "));
            fritidsbolig.setByggemateriale(fritid_byggemateriale.getText().replace(","," "));
            fritidsbolig.setStandard(fritid_standard.getText().replace(","," "));
            fritidsbolig.setAreal(Integer.parseInt(fritid_areal.getText().replace(","," ")));
            fritidsbolig.setForsikringsbeløpByggning(Double.parseDouble(fritid_beløpBygning.getText().replace(","," ")));
            fritidsbolig.setForsikringsbeløpInnbo(Double.parseDouble(fritid_beløpInnbo.getText().replace(","," ")));

            fritidsbolig.setPremieAnnum(fritidsbolig.prisPrÅr(200));
            fritidsbolig.setForsikringsSum(fritidsbolig.calculateForsikringssum());

        } catch (InvalidNumberFormatException e) {
            AlertHelper.createAlert(Alert.AlertType.INFORMATION, "Ugyldig inndata", "Ett eller flere felter inneholder ugyldige data");
            return null;
        } catch (IllegalAccessException e) {
            AlertHelper.createAlert(Alert.AlertType.INFORMATION, "Feil input", e.getMessage());
        }
        return fritidsbolig;
    }
}
