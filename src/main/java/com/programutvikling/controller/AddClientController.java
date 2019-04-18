package com.programutvikling.controller;

import com.programutvikling.models.data.kunde.Kunde;
import com.programutvikling.mainapp.MainApp;
import com.programutvikling.models.exceptions.InvalidAddressException;
import com.programutvikling.models.exceptions.InvalidNameFormatException;
import com.programutvikling.models.exceptions.InvalidNumberFormatException;
import com.programutvikling.models.filehandlers.reader.CsvReader;
import com.programutvikling.models.filehandlers.reader.FileReader;
import com.programutvikling.models.filehandlers.reader.JobjReader;
import com.programutvikling.models.filehandlers.writer.CsvWriter;
import com.programutvikling.models.filehandlers.writer.FileWriter;
import com.programutvikling.models.filehandlers.writer.JobjWriter;
import com.programutvikling.models.inputhandler.Inputhandler;
import com.programutvikling.models.viewChanger.ViewChanger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.IOException;

public class AddClientController {

    @FXML AnchorPane secondpageparent;

    @FXML private TextField fx_fornavn;
    @FXML private TextField fx_etternavn;
    @FXML private TextField fx_forsikringsnummer;
    @FXML private TextField fx_fakturaadresse;


    @FXML
    private void saveClient(ActionEvent event) {

        System.out.println("SAVECLIENT");

        Kunde kunde = new Kunde();
        try {
            Inputhandler.checkValidNameFormat(fx_fornavn.getText());
            Inputhandler.checkValidNameFormat(fx_etternavn.getText());
            Inputhandler.checkValidForsikrNr(fx_forsikringsnummer.getText());
            //TODO: Denne metoden funker ikke, fiks - test - moveon
            //Inputhandler.checkValidFakturaAdresse(fx_fakturaadresse.getText());

            File file = FileWriter.getFile();
            if (FileWriter.getExtension(file).equals(".csv")) new CsvWriter().writeDataToFile(file, kunde);
            else new JobjWriter().writeDataToFile(file, kunde);


        } catch (InvalidNameFormatException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
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
            MainApp.getClientList().add(kunde);
    }

    @FXML
    private void loadClient() {
        Kunde kunde = new Kunde();
        try {
            File file = FileReader.getFile();
            if (FileReader.getExtension(file).equals("csv"))kunde = (Kunde)new CsvReader().readDataFromFile(file);
            else kunde = (Kunde)new JobjReader().readDataFromFile(file);

        } catch (Exception e) {
            // TODO: Handle this
        }

        MainApp.getClientList().add(kunde);
    }

    @FXML
    private void abort() {}

    @FXML
    private void abortButton(ActionEvent event) {
        ViewChanger vc = new ViewChanger();
        vc.setView(secondpageparent, "startpage", "views/startpage.fxml");
    }
}
