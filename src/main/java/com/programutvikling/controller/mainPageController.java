package com.programutvikling.controller;

import com.programutvikling.mainapp.MainApp;
import com.programutvikling.models.data.kunde.Kunde;
import com.programutvikling.models.exceptions.InvalidFileFormatException;
import com.programutvikling.models.filehandlers.FileHandler;
import com.programutvikling.models.filehandlers.reader.CsvObjectBuilder;
import com.programutvikling.models.filehandlers.reader.CsvReader;
import com.programutvikling.models.filehandlers.reader.FileReader;
import com.programutvikling.models.filehandlers.reader.JobjReader;
import com.programutvikling.models.viewChanger.ViewChanger;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.BorderPane;
import javafx.util.StringConverter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class mainPageController {

    @FXML
    BorderPane rootPane;

    @FXML
    ListView<Kunde> clientList;

    @FXML
    TableView table;

    @FXML
    TextField k_fornavn, k_etternavn, k_forsNr, k_adr, k_opDato;

    @FXML
    public void initialize() {
        k_forsNr.setEditable(false);
        k_opDato.setEditable(false);
    }

    @FXML
    private void selectClient() {
        Kunde k = clientList.getSelectionModel().getSelectedItem();
        MainApp.setSelectedKunde(k);
        k_fornavn.setText(k.getFornavn());
        k_etternavn.setText(k.getEtternavn());
        k_forsNr.setText(k.getForsikrNr());
        k_adr.setText(k.getFakturaadresse());
        k_opDato.setText(k.getKundeOpprettet().toString());

    }

    @FXML
    private void nyForsikring(ActionEvent event) {
        Kunde k = clientList.getSelectionModel().getSelectedItem();
        MainApp.setSelectedKunde(k);
        if (k == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Kunde ikke valgt");
            alert.setContentText("Vennligst velg en kunde først");
            alert.showAndWait();
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
                    list.add((Kunde)new CsvObjectBuilder().buildObjectFromString(s));
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
        refresh(event);
    }

    @FXML
    private void saveChanges(ActionEvent event) {
        Kunde k = clientList.getSelectionModel().getSelectedItem();
        k.setFornavn(k_fornavn.getText());
        k.setEtternavn(k_etternavn.getText());
        refresh(event);
    }

    @FXML
    private void refresh(ActionEvent event) {

        clientList.setCellFactory(kundeListView -> {
            TextFieldListCell<Kunde> cell = new TextFieldListCell<>();
            cell.setConverter(new StringConverter<Kunde>() {
                @Override
                public String toString(Kunde kunde) {
                    return kunde.getFornavn() + " " +
                            kunde.getEtternavn() + " " + kunde.getFakturaadresse();
                }

                @Override
                public Kunde fromString(String s) {
                    Kunde kunde = cell.getItem();
                    return kunde;
                }
            });
            return cell;
        });

        clientList.getItems().removeAll(MainApp.getClientList());
        clientList.getItems().addAll(MainApp.getClientList());
    }

    private void loadStoredClients() {

    }





}
