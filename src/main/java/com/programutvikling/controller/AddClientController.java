package com.programutvikling.controller;

import com.programutvikling.models.data.kunde.Kunde;
import com.programutvikling.mainapp.MainApp;
import com.programutvikling.models.filehandlers.writer.JobjWriter;
import com.programutvikling.models.viewChanger.ViewChanger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
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

        MainApp.getClientList().add(kunde);
    }

    @FXML
    private void abortButton(ActionEvent event) {
        ViewChanger vc = new ViewChanger();
        vc.setView(secondpageparent, "startpage", "views/startpage.fxml");
    }
}
