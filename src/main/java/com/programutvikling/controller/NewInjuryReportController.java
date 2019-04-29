package com.programutvikling.controller;

import com.programutvikling.mainapp.MainApp;
import com.programutvikling.models.data.kunde.Kunde;
import com.programutvikling.models.data.skademelding.Skademelding;
import com.programutvikling.models.exceptions.InvalidNumberFormatException;
import com.programutvikling.models.inputhandlers.Inputvalidator;
import com.programutvikling.models.utils.helpers.AlertHelper;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class NewInjuryReportController {

    @FXML
    BorderPane newInjuryRoot;
    @FXML
    StackPane stack;

    @FXML
    DatePicker skadeDato;

    @FXML
    private TextField
            typeSkade,
            takseringsbeløp;
    @FXML
    private TextArea
            skadeBeskrivelse,
            kontaktinfoVitner;

    private int skadeNr;



    public void registererSkademelding(){
        Kunde k = MainApp.getSelectedKunde();
        skadeNr = k.getForsikrNr();
        Skademelding skademelding = createSkademelding();

        k.getSkademeldinger().add(skademelding);

    }

    /**
     * må lages en bedre håndtering for hvordan man gjør feil testingen
     * @return
     */
    public Skademelding createSkademelding(){

        Skademelding skademelding = new Skademelding();

        try{
            Inputvalidator.checkIfValidNumber(takseringsbeløp.getText());

            skademelding.setKontaktinfoVitner(kontaktinfoVitner.getText());
            skademelding.setSkadeBeskrivelse(skadeBeskrivelse.getText());
            skademelding.setSkadeNr(skadeNr);
            skademelding.setTypeSkade(typeSkade.getText());
            //skademelding.setTakseringsBelop();
            //skademelding.setUtbetaltErstatning();

        } catch (InvalidNumberFormatException e) {
            e.printStackTrace();
            AlertHelper.createAlert(Alert.AlertType.ERROR, "Feil data", "Fyll inn riktig data i riktig datafelt");
        }
        return skademelding;
    }


}
