package com.programutvikling.controller;

import com.programutvikling.data.tesklasser.Kunde;
import com.programutvikling.mainapp.MainApp;
import com.programutvikling.models.filehandlers.writer.JobjWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AddClientController {

    @FXML AnchorPane secondpageparent;

    @FXML private TextField fx_fornavn;
    @FXML private TextField fx_mellomnavn;
    @FXML private TextField fx_etternavn;
    @FXML private TextField fx_forsikringsnummer;
    @FXML private TextField fx_fakturaadresse;


    @FXML
    private void addClientButton(ActionEvent event) {

        Kunde kunde = new Kunde(
                fx_fornavn.getText(),
                fx_mellomnavn.getText(),
                fx_etternavn.getText(),
                fx_forsikringsnummer.getText(),
                fx_fakturaadresse.getText()
        );

        //Legger til kunde i arrayliste som bor i mainapp
        MainApp.getClientList().add(kunde);

        JobjWriter writer = new JobjWriter();
        try {
            writer.writeDataToFile(writer.getFile(), kunde);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @FXML
    private void abortButton(ActionEvent event) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getClassLoader().getResource("views/startpage.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene2 = new Scene(parent);
        Stage stage = (Stage)secondpageparent.getParent().getScene().getWindow();
        stage.setScene(scene2);
    }
}
