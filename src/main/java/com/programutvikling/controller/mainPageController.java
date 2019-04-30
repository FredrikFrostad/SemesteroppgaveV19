package com.programutvikling.controller;

import com.programutvikling.mainapp.MainApp;
import com.programutvikling.models.data.ObjectType;
import com.programutvikling.models.data.forsikring.Forsikring;
import com.programutvikling.models.data.kunde.Kunde;
import com.programutvikling.models.exceptions.InvalidFileFormatException;
import com.programutvikling.models.filehandlers.ExtensionHandler;
import com.programutvikling.models.filehandlers.reader.FileReader;
import com.programutvikling.models.filehandlers.reader.JobjReader;
import com.programutvikling.models.filehandlers.writer.FileWriter;
import com.programutvikling.models.filehandlers.writer.JobjWriter;
import com.programutvikling.models.utils.helpers.*;
import com.programutvikling.models.viewChanger.ViewChanger;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;


public class mainPageController {

    @FXML
    private BorderPane rootPane;

    @FXML
    private Tab tabForsikring, tabKunder, tabSkademeldinger;

    //TODO: generate, format and display data for these fields
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

    //Todo: generate data for unused fields, also these fields must be uneditable!!
    @FXML
    private TextField k_fornavn, k_etternavn, k_forsNr, k_adr, k_opDato, policyCountField, yearlyAmountField;

    @FXML
    private TextField selectedKundeField;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Text progressText;

    private File threadfile;


    @FXML
    private void initialize() {
        initClientTable();
        initForsikringsTable();
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

    /**
     * Initialises column names and valueproperties for the insurance overview tableview
     */
    private void initForsikringsTable() {
        overviewCol1.setCellValueFactory(new PropertyValueFactory<>("type"));
        overviewCol2.setCellValueFactory(new PropertyValueFactory<>("premieAnnum"));
    }


    // TODO: fjernes når denne ikke trengs mere
    @FXML
    private void TEST() {
        /*
        System.out.println("Valgt kunde er: " + MainApp.getSelectedKunde().getFornavn() + " " + MainApp.getSelectedKunde().getEtternavn());
        System.out.println("Antall elementer i forsikringsliste er: " + MainApp.getSelectedKunde().getForsikringer().size());
        for (Forsikring f : MainApp.getSelectedKunde().getForsikringer()) System.out.println(f);
        */
        for (int i = 0; i < 1E6; i++) {
            MainApp.getClientList().add(new Kunde("Test" + Integer.toString(i), "Testesen" + Integer.toString(i), i + 500, "Fakturaadresse"));
        }
        refreshTable();
    }



    /**
     * When a row containing client data is selected, the corresponding client object is set as activive in MainApp.
     * A string describing the selected clien object is also set in the Insurance policies tab.
     */
    @FXML
    private void selectClient() {
        Kunde k = clientTable.getSelectionModel().getSelectedItem();
        if (k != null) {
            MainApp.setSelectedKunde(k);
            populateClientFields(k);
            selectedKundeField.setText(k.getForsikrNr() + ": " + k.getFornavn() + " " + k.getEtternavn());
        }

    }

    /**
     * When a policy is selected, the corrasponding data is displayed in a single row tableview.
     * The columns of the tableview are generated on demand by the FormatPolicyHelper class, based
     * on the type enum of the data object.
     */
    @FXML
    private void selectPolicy() {
        if (tableOverviewForsikring.getSelectionModel().getSelectedItem() != null) {
            Forsikring f = tableOverviewForsikring.getSelectionModel().getSelectedItem();
            tableDetailsForsikring.getColumns().clear();
            FormatPolicyTableHelper.formatCollumns(this, tableDetailsForsikring, f);
            tableDetailsForsikring.getItems().clear();
            tableDetailsForsikring.getItems().add(f);
        }
    }

    
    /**
     * Method for reacting to a selectionchange in this views tabpane. The method response is
     * based on the tab selected
     */
    @FXML
    private void tabChanged() {
        if (tabForsikring.isSelected()) {
            System.out.println("EVENT FORSIKRING TRIGGERED!!");
            refreshTable();
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

    /**
     * Exports all data objects as a single jobj file or several csv files
     */
    @FXML
    private void exportToFile() {
        try {
            threadfile = FileWriter.getFile();
        } catch (Exception e) {
            e.printStackTrace();
            AlertHelper.createAlert(Alert.AlertType.ERROR, "Export feilet", e.getMessage());
        }

        Service<Void> threadService = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        try
                        {
                            if (ExtensionHandler.getExtension(threadfile).equals(".jobj")) {
                                JobjWriter writer = new JobjWriter();
                                writer.writeObjectDataToFile(threadfile, MainApp.getClientList());
                            }
                        } catch (InvalidFileFormatException e) {
                            AlertHelper.createAlert(Alert.AlertType.ERROR, "Export feilet", e.getMessage());
                            e.printStackTrace();
                        } catch (IOException e) {
                            AlertHelper.createAlert(Alert.AlertType.ERROR, "Export feilet", e.getMessage());
                            e.printStackTrace();
                        } catch (Exception e) {
                            AlertHelper.createAlert(Alert.AlertType.ERROR, "Export feilet", e.getMessage());
                            e.printStackTrace();
                        }
                        return null;
                    }
                };
            }
        };
        threadService.start();
    }

    /**
     * Imports data objects from either jobj or csv files.
     * The file import is threaded  to keep the gui responsive
     */
    @FXML private void importFromFile() {
        try {
            threadfile = FileReader.getFile();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Service<Void> thread = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        try {
                            System.out.println("Starting file import task!");
                            if (ExtensionHandler.getExtension(threadfile).equals(".jobj")) {
                                JobjReader reader = new JobjReader();

                                ArrayList<Kunde> list = (ArrayList<Kunde>) reader.readDataFromFile(threadfile);
                                ArrayList<Kunde> clientList = MainApp.getClientList();
                                for (Kunde k : list) {
                                    if (!clientList.contains(k)) {
                                        clientList.add(k);
                                    }
                                }
                            } else {
                                // This implementation is fragile, and only works on files eksported using the
                                // exportToFile method, insuring that the naming scheme of the exported csv files
                                // are kept intact.
                                DbImportHelperCsv importer = new DbImportHelperCsv();
                                importer.importDbFromCsv(threadfile.getParent());
                            }
                        } catch (Exception e) {
                            AlertHelper.createAlert(Alert.AlertType.ERROR, "En feil har oppstått", e.getMessage());
                        }
                        System.out.println("File import task completed");
                        refreshTable();
                        return null;
                    }
                };
            }
        };
        thread.start();
    }

    @FXML
    private void nyForsikring(ActionEvent event) {
        Kunde k = clientTable.getSelectionModel().getSelectedItem();
        MainApp.setSelectedKunde(k);
        noCustomerSelected(k);

        ViewChanger vc = new ViewChanger();
        vc.setView(rootPane, "newPolicy", "views/newPolicy.fxml");
    }

    @FXML
    private void nySkademelding(ActionEvent event){
        Kunde k = clientTable.getSelectionModel().getSelectedItem();
        MainApp.setSelectedKunde(k);
        noCustomerSelected(k);

        ViewChanger vc = new ViewChanger();
        vc.setView(rootPane, "newInjuryRepoert", "views/newInjuryReport.fxml");



    }
    private void noCustomerSelected(Kunde k){
        if (k == null) {
            AlertHelper.createAlert(Alert.AlertType.ERROR, "Kunder ikke valgt", "Vennligst velg en kunde først");
            return;
        }
    }

    /**
     * Changes to the view for adding a client.
     * @param event
     */
    @FXML
    private void newClient(ActionEvent event) {
        ViewChanger vc = new ViewChanger();
        vc.setView(rootPane, "newClient", "views/newClient.fxml");
    }


    @FXML
    private void saveChanges(ActionEvent event) {
        Kunde k = MainApp.getSelectedKunde();
        k.setFornavn(k_fornavn.getText());
        k.setEtternavn(k_etternavn.getText());
        refreshTable();
    }

    @FXML
    private void deleteClient() {
        Kunde k = MainApp.getSelectedKunde();
        MainApp.setSelectedKunde(null);
        MainApp.getClientList().remove(k);
        refreshTable();
    }

    @FXML
    private void saveChangesToFile() {

    }

    /**
     * Method for refreshing the kunde tableview. This method is public to enable other screens to
     * refresh the tableview if a change has happened.
     */
    @FXML
    public void refreshTable() {
        clientTable.getItems().clear();
        clientTable.getItems().addAll(MainApp.getClientList());
        tableOverviewForsikring.getItems().clear();
        if (MainApp.getSelectedKunde() != null) {
            Kunde k = MainApp.getSelectedKunde();
            tableOverviewForsikring.getItems().addAll(k.getForsikringer());
        }
    }

    //TODO: testfunksjon fjern når ferdig med den!
    @FXML
    private void testfunksjon(ActionEvent event){
        System.out.println(MainApp.getSelectedKunde().getSkademeldinger().get(0).toString());
    }

    /**
     * Loads db data into program
     */
    private void initDb() {
        DbImportHelperCsv importer = new DbImportHelperCsv();
        importer.importDbFromCsv(null);
    }
}
