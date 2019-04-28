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
import com.programutvikling.models.utils.helpers.AlertHelper;
import com.programutvikling.models.utils.helpers.DbImportHelperCsv;
import com.programutvikling.models.utils.helpers.FormatPolicyTableHelper;
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
    private void initialize() {
        initClientTable();
        initForsikringsTable();
        //initDb();
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
        try
        {
            File file = FileWriter.getFile();
            if (ExtensionHandler.getExtension(file).equals(".jobj")) {
                JobjWriter writer = new JobjWriter();
                writer.writeObjectDataToFile(file, MainApp.getClientList());
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
    }

    /**
     * Imports data objects from either jobj or csv files
     */
    @FXML private void importFromFile() {
        try {
            File file = FileReader.getFile();
            if (ExtensionHandler.getExtension(file).equals(".jobj")) {
                JobjReader reader = new JobjReader();
                ArrayList<Kunde> list = (ArrayList<Kunde>) reader.readDataFromFile(file);
                for (Kunde k : list) {
                    if (!MainApp.getClientList().contains(k)) MainApp.getClientList().add(k);

                }
            }

        }catch (FileNotFoundException e) {
            AlertHelper.createAlert(Alert.AlertType.ERROR,"Import feilet", e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            AlertHelper.createAlert(Alert.AlertType.ERROR, "En feil har oppstått", e.getMessage());
        }
        refreshTable();
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
        tableOverviewForsikring.getItems().clear();
        if (MainApp.getSelectedKunde() != null) {
            Kunde k = MainApp.getSelectedKunde();
            tableOverviewForsikring.getItems().addAll(k.getForsikringer());
        }
    }

    /**
     * Loads db data into program
     */
    private void initDb() {
        DbImportHelperCsv importer = new DbImportHelperCsv();
        importer.importDbFromCsv();
    }


}

    /*
    @FXML
    private void loadKunde(ActionEvent event) {
        ArrayList<Kunde> list = MainApp.getClientList();

        try {
            File file = FileReader.getFile();
            if (ExtensionHandler.getExtension(file).equals(".csv")) {

                CsvReader csvReader = new CsvReader(file);
                Thread thread = new Thread(csvReader);
                thread.start();
                thread.join();
                for (String[] s : (ArrayList<String[]>)csvReader.getReturnValue()) {
                    Kunde k = (Kunde)new CsvObjectBuilder().buildObjectFromString(s);
                    if (!list.contains(k)) list.add(k);
                }
            }
            else {
                //makeing a new jobjReader object to read in the file, starts thread,
                JobjReader jobjReader = new JobjReader(file);
                Thread thread = new Thread(jobjReader);
                thread.start();
                thread.join();

                if (!list.contains((Kunde)jobjReader.getReturnValue())){
                    System.out.println("HAR SJEKKET AT KUNDE IKKE LIGGER I LISTE FRA FØR");
                    list.add((Kunde)jobjReader.getReturnValue());
                }
                else{
                    AlertHelper.createAlert(Alert.AlertType.ERROR, "Man kan ikke load inn et objekt som allerede eksisterer, throw exception", "Man kan ikke load inn et objekt som allerede eksisterer, throw exception");
                }
            }

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
    */
