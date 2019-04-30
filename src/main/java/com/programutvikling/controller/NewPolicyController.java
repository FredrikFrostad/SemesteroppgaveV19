package com.programutvikling.controller;

import com.programutvikling.mainapp.MainApp;
import com.programutvikling.models.data.forsikring.Forsikring;
import com.programutvikling.models.data.forsikring.Fritidsbolig;
import com.programutvikling.models.data.forsikring.VillaInnbo;
import com.programutvikling.models.data.kunde.Kunde;
import com.programutvikling.models.exceptions.InvalidNumberFormatException;
import com.programutvikling.models.filehandlers.writer.JobjWriter;
import com.programutvikling.models.inputhandlers.Inputvalidator;
import com.programutvikling.models.utils.helpers.AlertHelper;
import com.programutvikling.models.viewChanger.ViewChanger;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import java.io.File;
import java.io.IOException;

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
            villa_adresse,
            villa_type,
            villa_byggår,
            villa_boligtype,
            villa_byggmaterialer,
            villa_standar,
            villa_areal,
            villa_forsikringsbeløp,
            villa_forsikringsbeløpinbo,
            båt_eier,
            båt_type,
            båt_regNr,
            båt_typebåt,
            båt_modell,
            båt_lengde,
            båt_årsmodell,
            båt_motorType,
            båt_effekt,
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

        if (active.getId().equals("fritidsPage")) {
            forsikring = createFritidsBolig();
        }/*else if(active.getId().equals("bilPage")){
            forsikring = createBilForsikring();
        }*/else if(active.getId().equals("båtPage")){
            forsikring = createBåtForsikring();
        }else if(active.getId().equals("reisePage")){
            forsikring = createReiseForsikring();
        }else if(active.getId().equals("villaPage")){
            forsikring = createVillaForsikring();
        }

        Kunde k = MainApp.getSelectedKunde();
        k.getForsikringer().add(forsikring);

    }

    private Forsikring createVillaForsikring() {
        VillaInnbo villa = new VillaInnbo();

        try{
            Inputvalidator.checkIfValidNumber(fritid_byggeår.getText());
            Inputvalidator
        }
    }

    private Forsikring createReiseForsikring() {
    }

    private Forsikring createBåtForsikring() {
    }

    private Forsikring createBilForsikring() {
    }

    private Fritidsbolig createFritidsBolig() {
        boolean validField = false;
        Fritidsbolig f = new Fritidsbolig();

        try {
            Inputvalidator.checkIfValidNumber(fritid_byggeår.getText());
            Inputvalidator.checkIfValidNumber(fritid_areal.getText());
            Inputvalidator.checkIfValidNumber(fritid_beløpInnbo.getText());
            Inputvalidator.checkIfValidNumber(fritid_beløpBygning.getText());

            f.setAdresse(fritid_adresse.getText());
            f.setByggeaar(Integer.parseInt(fritid_byggeår.getText()));
            f.setBoligtype(fritid_boligtype.getText());
            f.setByggemateriale(fritid_byggemateriale.getText());
            f.setStandard(fritid_standard.getText());
            f.setAreal(Integer.parseInt(fritid_areal.getText()));
            f.setForsikringsbeløpByggning(Double.parseDouble(fritid_beløpBygning.getText()));
            f.setForsikringsbeløpInnbo(Double.parseDouble(fritid_beløpInnbo.getText()));

        } catch (InvalidNumberFormatException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Feil inndata");
            alert.setContentText(e.getMessage());
        }

        return f;
    }

}
