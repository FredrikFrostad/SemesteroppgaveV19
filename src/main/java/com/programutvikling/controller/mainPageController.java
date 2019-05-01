package com.programutvikling.controller;

import com.programutvikling.mainapp.MainApp;
import com.programutvikling.models.data.ObjectType;
import com.programutvikling.models.data.forsikring.Forsikring;
import com.programutvikling.models.data.kunde.Kunde;
import com.programutvikling.models.data.skademelding.Skademelding;
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
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;


public class mainPageController {

    @FXML
    private BorderPane rootPane;

    @FXML
    private Tab tabForsikring, tabKunder, tabSkademeldinger;

    @FXML
    private TableView<Forsikring> tableOverviewForsikring, tableDetailsForsikring;

    @FXML
    private TableView<Skademelding> tableOverviewSkademeldinger, tableDeatailsSkademelding;

    @FXML
    private TableColumn<Forsikring, String> overviewCol1;

    @FXML
    private TableColumn<Forsikring, ObjectType> overviewCol2;

    @FXML
    private TableColumn<Skademelding, String> skademeldingStringTableColumn1;

    @FXML
    private TableColumn<Skademelding, ObjectType> skademeldingObjectTypeTableColumn2;

    @FXML
    private TableView<Kunde> clientTable;

    @FXML
    private TableColumn<Kunde, String> kundeCol1, kundeCol2, kundeCol3;

    @FXML
    private TextField k_fornavn, k_etternavn, k_forsNr, k_adr, k_opDato, policyCountField, yearlyAmountField;

    @FXML
    private TextField
            selectedKundeFieldForsikring,
            selectedKundeFieldSkademelding;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Text progressText;

    private File threadfile;


    @FXML
    private void initialize() {
        initClientTable();
        initForsikringsTable();
        initSkademeldingsTable();
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
    private void initSkademeldingsTable(){
        skademeldingStringTableColumn1.setCellValueFactory(new PropertyValueFactory<>("type"));
        skademeldingObjectTypeTableColumn2.setCellValueFactory(new PropertyValueFactory<>("skadeDato"));
    }


    // TODO: fjernes når denne ikke trengs mere - FOR TESTING
    @FXML
    private void CLEAR() {
        MainApp.getClientList().clear();
        refreshTable();
    }

    // TODO: fjernes når denne ikke trengs mere - FOR TESTING
    @FXML
    private void BIG() {
        for (int i = 0; i < 1E6; i++) {
            MainApp.getClientList().add(new Kunde("Test" + Integer.toString(i), "Testesen" + Integer.toString(i), i + 500, "Fakturaadresse"));
        }
        refreshTable();
    }

    //TODO: fjern når ferdig
    @FXML
    private void TESTSKADE(){
        System.out.println(MainApp.getSelectedKunde().getSkademeldinger().toString());
        if (MainApp.getSelectedKunde().getSkademeldinger() == null){
            System.out.println("ingen objekter i skademeldinger");
        }
        for (Skademelding s : MainApp.getSelectedKunde().getSkademeldinger()) {
            System.out.println(s.toString());
        }
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
            selectedKundeFieldForsikring.setText(k.getForsikrNr() + ": " + k.getFornavn() + " " + k.getEtternavn());
            selectedKundeFieldSkademelding.setText(k.getForsikrNr() + ": " + k.getFornavn() + " " + k.getEtternavn());
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
    @FXML
    private void selectedInjuryReport(){
        if (tableOverviewSkademeldinger.getSelectionModel().getSelectedItem() != null){
            Skademelding skademelding = tableOverviewSkademeldinger.getSelectionModel().getSelectedItem();
            tableDeatailsSkademelding.getColumns().clear();
            FormatInjuryReportTableHelper.formatSkademelding(tableDeatailsSkademelding, this);
            tableDeatailsSkademelding.getItems().clear();
            tableDeatailsSkademelding.getItems().add(skademelding);
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
        policyCountField.setText(String.valueOf(k.getNmbrOfPolicies()));
        yearlyAmountField.setText(String.valueOf(k.getYearlyPremium()));
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
                                System.out.println("Writing jobj");
                                JobjWriter writer = new JobjWriter();
                                writer.writeObjectDataToFile(threadfile, MainApp.getClientList());
                                System.out.println("DONE");
                            } else {
                                DbExportHelperCsv exporter = new DbExportHelperCsv(threadfile.getAbsolutePath());
                                exporter.exportDbAsCsv();
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
                                MainApp.getClientList().addAll(list);
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
        vc.setView(rootPane, "newInjuryReport", "views/newInjuryReport.fxml");
    }

    /**
     * Checks if a client is selected for operations that rewuire this to be done.
     * @param k selected client object or null if nothing is selected.
     */
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
        k.setFornavn(k_fornavn.getText().replace(","," "));
        k.setEtternavn(k_etternavn.getText().replace(","," "));
        k.setFakturaadresse(k_adr.getText().replace(","," "));
        refreshTable();
    }

    @FXML
    private void deleteClient() {
        Kunde k = MainApp.getSelectedKunde();
        Optional<ButtonType> result = AlertHelper.createOptionAlert(Alert.AlertType.WARNING, "Bekreft sletting",
                "Er du sikker på at du vil slette kunde " + k.getFornavn() + " " + k.getEtternavn() + "?",
                "Slett Kunde ", "Avbryt");

        if (result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            MainApp.setSelectedKunde(null);
            MainApp.getClientList().remove(k);
            refreshTable();
        }
    }

    @FXML
    private void deletePolicy() {
        if (tableOverviewForsikring.getSelectionModel().getSelectedItem() != null) {
            Forsikring f = tableOverviewForsikring.getSelectionModel().getSelectedItem();

            Optional<ButtonType> result = AlertHelper.createOptionAlert(Alert.AlertType.WARNING, "Bekreft sletting",
                        "Er du sikker på at du vil slette forsikring " + f.getType() + ", opprettet: " + f.getAvtaleOpprettet() + "?",
                            "Slett Forsikring", "Avbryt");
            if (result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                MainApp.getSelectedKunde().getForsikringer().remove(f);
                tableDetailsForsikring.getColumns().clear();
                tableDetailsForsikring.getItems().clear();
                populateClientFields(MainApp.getSelectedKunde());
                refreshTable();
            }
        }
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
        tableOverviewSkademeldinger.getItems().clear();
        if (MainApp.getSelectedKunde() != null){
            Kunde k = MainApp.getSelectedKunde();
            tableOverviewSkademeldinger.getItems().addAll(k.getSkademeldinger());
        }
    }


    /**
     * Loads db data into program
     */
    private void initDb() {
        DbImportHelperCsv importer = new DbImportHelperCsv();
        try {
            importer.importDbFromCsv(null);
        } catch (Exception e) {
            e.printStackTrace();
            AlertHelper.createAlert(Alert.AlertType.ERROR, "Kritisk feil", "Feil ved importering av database. " +
                    "Kan ikke garantere dataintegritet.");
        }
    }
}


