package com.programutvikling.controller;

import com.programutvikling.mainapp.MainApp;
import com.programutvikling.models.data.forsikring.Forsikring;
import com.programutvikling.models.data.kunde.Kunde;
import com.programutvikling.models.exceptions.InvalidFileFormatException;
import com.programutvikling.models.filehandlers.FileHandler;
import com.programutvikling.models.filehandlers.reader.CsvObjectBuilder;
import com.programutvikling.models.filehandlers.reader.CsvReader;
import com.programutvikling.models.filehandlers.reader.FileReader;
import com.programutvikling.models.filehandlers.reader.JobjReader;
import com.programutvikling.models.utils.helpers.AlertHelper;
import com.programutvikling.models.viewChanger.ViewChanger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


public class mainPageController {

    @FXML
    private BorderPane rootPane;

    @FXML
    private Tab tabForsikring, tabKunder, tabSkademeldinger;

    @FXML
    private TableView<Forsikring> tableOverviewForsikring, tableDetailsForsikring;

    @FXML
    private TableView<Kunde> clientTable;

    @FXML
    private TableColumn<Kunde, String> kundeCol1, kundeCol2, kundeCol3;

    @FXML
    private TextField k_fornavn, k_etternavn, k_forsNr, k_adr, k_opDato;

    @FXML
    private TextField selectedKundeField;

    @FXML
    public void initialize() {
        k_forsNr.setEditable(false);
        k_opDato.setEditable(false);
        selectedKundeField.setEditable(false);

        initClientTable();
    }

    // TODO: Denne metoden kan sløyfes dersom dette flyttes inn i fxml-fila via scenebuilder
    private void initClientTable() {

        kundeCol1.setCellValueFactory(new PropertyValueFactory<>("forsikrNr"));
        kundeCol2.setCellValueFactory(new PropertyValueFactory<>("fornavn"));
        kundeCol3.setCellValueFactory(new PropertyValueFactory<>("etternavn"));
    }

    // TODO: Denne metoden kan sløyfes dersom dette flyttes inn i fxml-fila via scenebuilder
    private void initForsikringsTable() {
        TableColumn<Forsikring, String> forsikringCol1 = new TableColumn<>();
        TableColumn<Forsikring, String> forsikringCol2 = new TableColumn<>();
        TableColumn<Forsikring, String> forsikringCol3 = new TableColumn<>();
    }



    @FXML
    private void selectClient() {
        Kunde k = clientTable.getSelectionModel().getSelectedItem();
        MainApp.setSelectedKunde(k);
        populateClientFields(k);
        selectedKundeField.setText(k.getForsikrNr() + ": " + k.getFornavn() + " " + k.getEtternavn());
    }


    /**
     * Method for reacting to a selectionchange in this views tabpane. The method response is
     * based on the tab selected
     */
    @FXML
    private void tabChanged() {
        if (tabForsikring.isSelected()) System.out.println("EVENT FORSIKRING TRIGGERED!!");
        else if (tabKunder.isSelected()) System.out.println("EVENT KUNDER TRIGGERED");
        else if (tabSkademeldinger.isSelected()) System.out.println("EVENT SKADEMELDINGER TRIGGERED");
    }


    /**
     * Methode for populating textfields with client data
     * @param k The client objects containing the data
     */
    private void populateClientFields(Kunde k) {
        k_fornavn.setText(k.getFornavn());
        k_etternavn.setText(k.getEtternavn());
        k_forsNr.setText(k.getForsikrNr());
        k_adr.setText(k.getFakturaadresse());
        k_opDato.setText(k.getKundeOpprettet().toString());
    }

    @FXML
    private void nyForsikring(ActionEvent event) {
        Kunde k = clientTable.getSelectionModel().getSelectedItem();
        MainApp.setSelectedKunde(k);
        if (k == null) {
            AlertHelper.createAlert(Alert.AlertType.ERROR, "Kunder ikke valgt", "Vennligst velg en kunde først");
            return;
        }
        ViewChanger vc = new ViewChanger();
        vc.setView(rootPane, "newPolicy", "views/newPolicy.fxml");
    }



    @FXML
    private void newClient(ActionEvent event) {
        ViewChanger vc = new ViewChanger();
        vc.setView(rootPane, "newClient", "views/newClient.fxml");
    }

    //TODO: TILBAKEMELDING TIL KUNDE DERSOM FEIL
    @FXML
    private void loadKunde(ActionEvent event) {
        ArrayList<Kunde> list = MainApp.getClientList();

        try {
            File file = FileReader.getFile();
            if (FileHandler.getExtension(file).equals(".csv")) {

                for (String[] s : (ArrayList<String[]>)new CsvReader().readDataFromFile(file)) {
                    Kunde k = (Kunde)new CsvObjectBuilder().buildObjectFromString(s);
                    if (!list.contains(k)) list.add(k);
                }
            }
            else list.add((Kunde)new JobjReader().readDataFromFile(file));

        } catch (FileNotFoundException e){
            e.printStackTrace();
            e.getMessage();
        } catch (InvalidFileFormatException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        refreshTable(event);
    }

    @FXML
    private void saveChanges(ActionEvent event) {
        Kunde k = MainApp.getSelectedKunde();
        k.setFornavn(k_fornavn.getText());
        k.setEtternavn(k_etternavn.getText());
        refreshTable(event);
    }

    @FXML
    private void saveChangesToFile() {
        File file = new File(MainApp.getSelectedKunde().getFilePath());

        try {
            FileHandler.getExtension(file);
        } catch (InvalidFileFormatException e) {
            AlertHelper.createAlert(Alert.AlertType.ERROR, "Feil!", "Kan ikke lagre endringer, finner ikke fil");
        }

    }

    @FXML
    private void refreshTable(ActionEvent event) {
        clientTable.getItems().clear();
        clientTable.getItems().addAll(MainApp.getClientList());
    }



}
