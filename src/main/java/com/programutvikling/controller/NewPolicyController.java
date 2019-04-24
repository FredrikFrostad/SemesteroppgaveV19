package com.programutvikling.controller;

import com.programutvikling.mainapp.MainApp;
import com.programutvikling.models.data.forsikring.*;
import com.programutvikling.models.exceptions.InvalidAddressException;
import com.programutvikling.models.exceptions.InvalidEmailException;
import com.programutvikling.models.exceptions.InvalidNameFormatException;
import com.programutvikling.models.exceptions.InvalidNumberFormatException;
import com.programutvikling.models.inputhandlers.Inputvalidator;
import com.programutvikling.models.utils.helpers.AlertHelper;
import com.programutvikling.models.viewChanger.ViewChanger;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

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
    private TextField fritid_adresse,
            fritid_byggeår,
            fritid_boligtype,
            fritid_byggemateriale,
            fritid_standard,
            fritid_areal,
            fritid_beløpBygning,
            fritid_beløpInnbo,

            villa_adresse,
            villa_byggeår,
            villa_boligtype,
            villa_byggemateriale,
            villa_standard,
            villa_areal,
            villa_beløpBygning,
            villa_beløpInnbo,

            båt_eier,
            båt_regNr,
            båt_typeBåt,
            båt_modell,
            båt_lengde,
            båt_årsmodell,
            båt_motorType,
            båt_effekt,


            bil_eier,
            bil_regNr,
            bil_typeBil,
            bil_modell,
            bil_lengde,
            bil_årsmodell,
            bil_motorType,
            bil_effekt,

            reise_forsikringOmraade,
            reise_forsikringsSum;


    private Node active;

    @FXML
    private void initialize() {
        active = togglePage;

    }

    @FXML
    private void next() {
        if (active.getId().equals("togglePage")) {
            active.setVisible(false);

            if (bil.isSelected()) {
                active = bilPage;
            } else if (båt.isSelected()) {
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
            registrerForsikring();
            ViewChanger vc = new ViewChanger();
            vc.resetView("newPolicy");
            vc.setView(newPolicyRoot, "mainPage", "views/mainPage.fxml");
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
        }
    }

    private void registrerForsikring() {
        Forsikring forsikring = null;

        if (active.getId().equals(fritidsPage)) {
            forsikring = createFritidsBolig();
        } else if (active.getId().equals(villaPage)) {
            forsikring = createVillaInnbo();
        } else if (active.getId().equals(reisePage)) {
            forsikring = createReise();
        } else if (active.getId().equals(bilPage)) {
            forsikring = createBil();
        } else if (active.getId().equals(båtPage)) {
            forsikring = createBåt();
        }


        MainApp.getSelectedKunde().getForsikringer().add(forsikring);
    }

    private Fritidsbolig createFritidsBolig() {
        boolean validField = false;
        Fritidsbolig f = new Fritidsbolig();

        try {

            if (Inputvalidator.checkIfValidNumber(fritid_byggeår.getText())) {
                f.setByggeaar(Integer.parseInt(fritid_byggeår.getText()));
            }

            else if (Inputvalidator.checkIfValidNumber(fritid_areal.getText())){
                f.setAreal(Integer.parseInt(fritid_areal.getText()));
            }
            else if (Inputvalidator.checkIfValidNumber(fritid_beløpInnbo.getText())){
                f.setForsikringsbeløpInnbo(Double.parseDouble(fritid_beløpInnbo.getText()));
            }
            else if (Inputvalidator.checkIfValidNumber(fritid_beløpBygning.getText())){
                f.setForsikringsbeløpByggning(Double.parseDouble(fritid_beløpBygning.getText()));
            }

            else if (Inputvalidator.checkValidFakturaAdresse(fritid_adresse.getText())){
                f.setAdresse(fritid_adresse.getText());
            }

            else if (Inputvalidator.checkValidNameFormat(fritid_boligtype.getText())) {
                f.setBoligtype(fritid_boligtype.getText());
            }
            else if (Inputvalidator.checkValidNameFormat(fritid_byggemateriale.getText())) {
                f.setByggemateriale(fritid_byggemateriale.getText());
            }
            else if(Inputvalidator.checkValidNameFormat(fritid_standard.getText())){
                    f.setStandard(fritid_standard.getText());

            }



        } catch (InvalidNumberFormatException e) {
            e.printStackTrace();
            AlertHelper.createAlert(Alert.AlertType.ERROR, "Ikke gyldig tall", e.getMessage());
        } catch (InvalidAddressException e) {
            e.printStackTrace();
            AlertHelper.createAlert(Alert.AlertType.ERROR, "Ikke gyldig fakturaadresse", e.getMessage());
        } catch (InvalidNameFormatException e) {
            e.printStackTrace();
            AlertHelper.createAlert(Alert.AlertType.ERROR, "Ikke gyldig navn", e.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
            AlertHelper.createAlert(Alert.AlertType.ERROR, "Noe gikk galt!", e.getMessage());
        }

        return f;
    }

    private VillaInnbo createVillaInnbo() {
        boolean validField = false;
        VillaInnbo v = new VillaInnbo();

        try {
            if (Inputvalidator.checkIfValidNumber(villa_byggeår.getText())) {
                v.setByggeÅr(Integer.parseInt(villa_byggeår.getText()));
            }

            else if (Inputvalidator.checkIfValidNumber(villa_areal.getText())){
                v.setAreal(Integer.parseInt(villa_areal.getText()));
            }
            else if (Inputvalidator.checkIfValidNumber(villa_beløpInnbo.getText())){
                v.setBeløpInnbo(Integer.parseInt(villa_beløpInnbo.getText()));
            }
            else if (Inputvalidator.checkValidNameFormat(villa_beløpBygning.getText())){
                v.setBeløpBygning(Integer.parseInt(villa_beløpBygning.getText()));
            }

            else if (Inputvalidator.checkValidFakturaAdresse(villa_adresse.getText())){
                v.setAdresse(villa_adresse.getText());
            }

            else if (Inputvalidator.checkValidNameFormat(villa_boligtype.getText())) {
                v.setBoligType(villa_boligtype.getText());
            }
            else if (Inputvalidator.checkValidNameFormat(villa_byggemateriale.getText())) {
                v.setByggemateriale(villa_byggemateriale.getText());
            }
            else if(Inputvalidator.checkValidNameFormat(villa_standard.getText())){
                v.setStandard(villa_standard.getText());

            }

        }

        catch (InvalidNumberFormatException e) {
            e.printStackTrace();
            AlertHelper.createAlert(Alert.AlertType.ERROR, "Ikke gyldig tall", e.getMessage());
        } catch (InvalidAddressException e) {
            e.printStackTrace();
            AlertHelper.createAlert(Alert.AlertType.ERROR, "Ikke gyldig fakturaadresse", e.getMessage());
        } catch (InvalidNameFormatException e) {
            e.printStackTrace();
            AlertHelper.createAlert(Alert.AlertType.ERROR, "Ikke gyldig navn", e.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
            AlertHelper.createAlert(Alert.AlertType.ERROR, "Noe gikk galt!", e.getMessage());
        }


        return v;
    }

    private Reise createReise() {
        boolean validField = false;
        Reise r = new Reise();

        try {
            if (Inputvalidator.checkValidNameFormat(reise_forsikringOmraade.getText())) {
                r.setforsikringOmraade(reise_forsikringOmraade.getText());
            }

            else if (Inputvalidator.checkIfValidNumber(reise_forsikringsSum.getText())){
                r.setForsikringsSum(Integer.parseInt(reise_forsikringsSum.getText()));
            }

        }
        catch (InvalidNumberFormatException e) {
            e.printStackTrace();
            AlertHelper.createAlert(Alert.AlertType.ERROR, "Ikke gyldig tall", e.getMessage());
        }
        catch (InvalidNameFormatException e) {
            e.printStackTrace();
            AlertHelper.createAlert(Alert.AlertType.ERROR, "Ikke gyldig navn", e.getMessage());
        }
        catch(Exception e){
            e.printStackTrace();
            AlertHelper.createAlert(Alert.AlertType.ERROR, "Noe gikk galt!", e.getMessage());
        }



        return r;
    }

    private Båt createBåt() {
        boolean validField = false;
        Båt b = new Båt();

        try {
            if (Inputvalidator.checkValidNameFormat(båt_eier.getText())) {
                b.setEier((båt_eier.getText()));
            }

            else if (Inputvalidator.checkIfValidNumber(båt_regNr.getText())){
                b.setRegNr(båt_regNr.getText());
            }
            else if (Inputvalidator.checkValidNameFormat(båt_typeBåt.getText())){
                b.setTypeBåt(båt_typeBåt.getText());
            }
            else if (Inputvalidator.checkValidNameFormat(båt_modell.getText())){
                b.setModell((båt_modell.getText()));
            }

            else if (Inputvalidator.checkIfValidNumber(båt_lengde.getText())){
                b.setLengde(Integer.parseInt(båt_lengde.getText()));
            }

            else if (Inputvalidator.checkIfValidNumber(båt_årsmodell.getText())) {
                b.setÅrsmodell(Integer.parseInt(båt_årsmodell.getText()));
            }
            else if (Inputvalidator.checkValidNameFormat(båt_motorType.getText())) {
                b.setMotorType(båt_motorType.getText());
            }
            else if(Inputvalidator.checkValidNameFormat(båt_effekt.getText())){
                b.setEffekt(båt_effekt.getText());

            }


        } catch (InvalidNumberFormatException e) {
            e.printStackTrace();
            AlertHelper.createAlert(Alert.AlertType.ERROR, "Ikke gyldig tall", e.getMessage());
        } catch (InvalidNameFormatException e) {
            e.printStackTrace();
            AlertHelper.createAlert(Alert.AlertType.ERROR, "Ikke gyldig navn", e.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
            AlertHelper.createAlert(Alert.AlertType.ERROR, "Noe gikk galt!", e.getMessage());
        }


        return b;
    }


        private Bil createBil() {
        boolean validField = false;
        Bil bi = new Bil();

        try {

            if (Inputvalidator.checkValidNameFormat(bil_eier.getText())) {
                bi.setEier((bil_eier.getText()));
            }
            else if (Inputvalidator.checkIfValidNumber(bil_regNr.getText())){
                bi.setRegNr(bil_regNr.getText());
            }
            else if (Inputvalidator.checkValidNameFormat(bil_typeBil.getText())){
                bi.setTypeBil(bil_typeBil.getText());
            }
            else if (Inputvalidator.checkValidNameFormat(bil_modell.getText())){
                bi.setModell(bil_modell.getText());
            }
            else if (Inputvalidator.checkIfValidNumber(bil_lengde.getText())){
                bi.setLengde(Integer.parseInt(bil_lengde.getText()));
            }
            else if (Inputvalidator.checkIfValidNumber(bil_årsmodell.getText())) {
                bi.setÅrsmodell(Integer.parseInt(bil_årsmodell.getText()));
            }
            else if (Inputvalidator.checkValidNameFormat(bil_motorType.getText())) {
                bi.setMotorType(bil_motorType.getText());
            }
            else if(Inputvalidator.checkValidNameFormat(bil_effekt.getText())){
                bi.setEffekt(bil_effekt.getText());
            }


        } catch (InvalidNumberFormatException e) {
            e.printStackTrace();
            AlertHelper.createAlert(Alert.AlertType.ERROR, "Ikke gyldig tall", e.getMessage());
        } catch (InvalidNameFormatException e) {
            e.printStackTrace();
            AlertHelper.createAlert(Alert.AlertType.ERROR, "Ikke gyldig navn", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            AlertHelper.createAlert(Alert.AlertType.ERROR, "Noe gikk galt!", e.getMessage());
        }


            return bi;
    }









}
