package com.programutvikling.controller;

import com.programutvikling.models.data.kunde.Kunde;
import com.programutvikling.mainapp.MainApp;
import com.programutvikling.models.exceptions.InvalidAddressException;
import com.programutvikling.models.exceptions.InvalidNameFormatException;
import com.programutvikling.models.exceptions.InvalidNumberFormatException;
import com.programutvikling.models.filehandlers.writer.CsvWriter;
import com.programutvikling.models.filehandlers.writer.FileWriter;
import com.programutvikling.models.filehandlers.writer.JobjWriter;
import com.programutvikling.models.inputhandlers.Inputvalidator;
import com.programutvikling.models.utils.helpers.AlertHelper;
import com.programutvikling.models.utils.helpers.ClientNrHelper;
import com.programutvikling.models.viewChanger.ViewChanger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;

public class AddClientController {


    @FXML
    BorderPane rootPane;

    @FXML private TextField fx_fornavn;
    @FXML private TextField fx_etternavn;
    @FXML private TextField fx_forsikringsnummer;
    @FXML private TextField fx_fakturaadresse;

    @FXML
    public void initialize() {
        fx_forsikringsnummer.setEditable(false);
        try {
            fx_forsikringsnummer.setText(Integer.toString(new ClientNrHelper().appendClient()));

        } catch (FileNotFoundException e) {
            AlertHelper.createAlert(Alert.AlertType.ERROR, "Fatal feil", "Finner ikke registerfil, kan ikke opprette kunde.");
            abort();
        }
    }


    @FXML
    private void saveClient(ActionEvent event) {

        System.out.println("SAVECLIENT");

        Kunde kunde = new Kunde();
        try {
            kunde.setKundeOpprettet(LocalDate.now());
            Inputvalidator.checkValidNameFormat(fx_fornavn.getText().replace(","," "));
            kunde.setFornavn(fx_fornavn.getText().replace(","," "));
            Inputvalidator.checkValidNameFormat(fx_etternavn.getText().replace(","," "));
            kunde.setEtternavn(fx_etternavn.getText().replace(","," "));
            kunde.setForsikrNr(Integer.parseInt(fx_forsikringsnummer.getText().replace(","," ")));
            kunde.setFakturaadresse(fx_fakturaadresse.getText().replace(","," "));


            File file = FileWriter.getFile();
            if (FileWriter.getExtension(file).equals(".csv")) new CsvWriter().writeObjectDataToFile(file, kunde);
            else new JobjWriter().writeObjectDataToFile(file, kunde);


        } catch (InvalidNameFormatException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            AlertHelper.createAlert(Alert.AlertType.ERROR,"Invalid name", "Wrong name, plz try again");
        } catch (InvalidNumberFormatException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } catch (InvalidAddressException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        //Legger til kunde i arrayliste som bor i mainapp

        ArrayList<Kunde> list = MainApp.getClientList();
        for (Kunde k : list) {
            if (k.toString().equals(kunde.toString())) return;
        }
        list.add(kunde);
        abort();
    }


    @FXML
    private void abort() {
        ViewChanger vc = new ViewChanger();
        vc.setView(rootPane, "startpage", "views/mainPage.fxml");
    }

}
