package com.programutvikling.controller;

import com.programutvikling.mainapp.MainApp;
import com.programutvikling.models.data.ObjectType;
import com.programutvikling.models.data.forsikring.Forsikring;
import com.programutvikling.models.data.kunde.Kunde;
import com.programutvikling.models.exceptions.InvalidFileFormatException;
import com.programutvikling.models.filehandlers.ExtensionHandler;
import com.programutvikling.models.filehandlers.reader.CsvObjectBuilder;
import com.programutvikling.models.filehandlers.reader.CsvReader;
import com.programutvikling.models.filehandlers.reader.FileReader;
import com.programutvikling.models.filehandlers.reader.JobjReader;
import com.programutvikling.models.filehandlers.writer.JobjWriter;
import com.programutvikling.models.utils.helpers.AlertHelper;
import com.programutvikling.models.utils.helpers.DbImportHelper;
import com.programutvikling.models.viewChanger.ViewChanger;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
    private TableColumn<Forsikring, String> overviewCol1;

    @FXML
    private TableColumn<Forsikring, ObjectType> overviewCol2;

    @FXML
    private TableView<Kunde> clientTable;

    @FXML
    private TableColumn<Kunde, String> kundeCol1, kundeCol2, kundeCol3;

    @FXML
    private TextField k_fornavn, k_etternavn, k_forsNr, k_adr, k_opDato;

    @FXML
    private TextField selectedKundeField;


    @FXML
    private void initialize() {
        k_forsNr.setEditable(false);
        k_opDato.setEditable(false);
        selectedKundeField.setEditable(false);

        initClientTable();
        initDb();
        refreshTable();
    }


    /**
     * Initialises column names and valueproperties for the Client tableview
     */
    private void initClientTable() {

        kundeCol1.setCellValueFactory(new PropertyValueFactory<>("forsikrNr"));
        kundeCol2.setCellValueFactory(new PropertyValueFactory<>("fornavn"));
        kundeCol3.setCellValueFactory(new PropertyValueFactory<>("etternavn"));
    }


    private void initForsikringsTable() {
        overviewCol1.setCellValueFactory(new PropertyValueFactory<>("type"));
        overviewCol2.setCellValueFactory(new PropertyValueFactory<>("premieAnnum"));

        if (!MainApp.getSelectedKunde().getForsikringer().isEmpty()) {
            System.out.println("Loading forsikringer");
            tableOverviewForsikring.getItems().addAll(MainApp.getSelectedKunde().getForsikringer());
        } else {
            System.out.println("loading forsikringer failed");
        }

    }


    // TODO: fjernes når denne ikke trengs mere
    @FXML
    private void TEST() {
        System.out.println("Valgt kunde er: " + MainApp.getSelectedKunde().getFornavn() + " " + MainApp.getSelectedKunde().getEtternavn());
        System.out.println("Antall elementer i forsikringsliste er: " + MainApp.getSelectedKunde().getForsikringer().size());
        for (Forsikring f : MainApp.getSelectedKunde().getForsikringer()) System.out.println(f);
    }



    /**
     * When a row containing client data is selected, the corresponding client object is set as activive in MainApp.
     * A string describing the selected clien object is also set in the Insurance policies tab.
     */
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
        if (tabForsikring.isSelected()) {
            System.out.println("EVENT FORSIKRING TRIGGERED!!");
            initForsikringsTable();
        }
        else if (tabKunder.isSelected()) {
            System.out.println("EVENT KUNDER TRIGGERED");
        }
        else if (tabSkademeldinger.isSelected()) {
            System.out.println("EVENT SKADEMELDINGER TRIGGERED");
        }
    }


    /**
     * Methode for populating textfields with client data
     * @param k The client objects containing the data
     */
    private void populateClientFields(Kunde k) {
        k_fornavn.setText(k.getFornavn());
        k_etternavn.setText(k.getEtternavn());
        k_forsNr.setText(Integer.toString(k.getForsikrNr()));
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


    /**
     * Method loading clientdata stored to file
     * @param event is conumed
     */
    @FXML
    private void loadKunde(ActionEvent event) {
        ArrayList<Kunde> list = MainApp.getClientList();

        try {
            File file = FileReader.getFile();
            if (ExtensionHandler.getExtension(file).equals(".csv")) {

                for (String[] s : (ArrayList<String[]>)new CsvReader().readDataFromFile(file)) {
                    Kunde k = (Kunde)new CsvObjectBuilder().buildObjectFromString(s);
                    if (!list.contains(k)) list.add(k);
                }
            }
            else list.add((Kunde)new JobjReader().readDataFromFile(file));

        } catch (FileNotFoundException e){
            e.printStackTrace();
            AlertHelper.createAlert(Alert.AlertType.ERROR, "Finner ikke fil", e.getMessage());
        } catch (InvalidFileFormatException e) {
            e.printStackTrace();
            AlertHelper.createAlert(Alert.AlertType.ERROR, "Ukjent filformat", e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            AlertHelper.createAlert(Alert.AlertType.ERROR,"Kan ikke lese fra fil", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            AlertHelper.createAlert(Alert.AlertType.ERROR,"En feil har oppstått", e.getMessage());
        }
        refreshTable();
    }

    @FXML
    private void saveChanges(ActionEvent event) {
        Kunde k = MainApp.getSelectedKunde();
        k.setFornavn(k_fornavn.getText());
        k.setEtternavn(k_etternavn.getText());
        refreshTable();
    }

    @FXML
    private void saveChangesToFile() {
        File file = new File(MainApp.getSelectedKunde().getFilePath());

        try {
            if (ExtensionHandler.getExtension(file).equals(".jobj")) {
                new JobjWriter().writeObjectDataToFile(file, MainApp.getSelectedKunde());
            }

        } catch (InvalidFileFormatException e) {
            AlertHelper.createAlert(Alert.AlertType.ERROR, "Feil!", "Kan ikke lagre endringer, finner ikke fil");
        } catch (IOException e) {
            AlertHelper.createAlert(Alert.AlertType.ERROR, "Feil ved lagring til fil", e.getMessage());
        }

    }

    /**
     * Method for refreshing the kunde tableview. This method is public to enable other screens to
     * refresh the tableview if a change has happened.
     */
    @FXML
    public void refreshTable() {
        clientTable.getItems().clear();
        clientTable.getItems().addAll(MainApp.getClientList());
    }

    /**
     * Loads db data into program
     */
    private void initDb() {
        DbImportHelper importer = new DbImportHelper();
        importer.importDbFromCsv();
    }


}
