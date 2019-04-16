package com.programutvikling.controller;

import com.programutvikling.mainapp.MainApp;
import com.programutvikling.models.data.kunde.Kunde;
import com.programutvikling.models.exceptions.InvalidFileFormatException;
import com.programutvikling.models.filehandlers.reader.CsvReader;
import com.programutvikling.models.filehandlers.reader.FileReader;
import com.programutvikling.models.filehandlers.reader.JobjReader;
import com.programutvikling.models.viewChanger.ViewChanger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StartPageController {

    @FXML
    AnchorPane startpageparent;

    @FXML
    ListView<Kunde> clientsList;

    @FXML
    private void listClients(ActionEvent event) {
        ViewChanger vc = new ViewChanger();
        vc.setView(startpageparent, "clients", "views/clients.fxml");
    }

    @FXML
    private void newClient(ActionEvent event) {
        ViewChanger vc = new ViewChanger();
        vc.setView(startpageparent, "newClient", "views/newClient.fxml");
    }

    @FXML
    private void newPolicy(ActionEvent event) {

    }

    /**
     * Method for loading a client from file
     */
    @FXML
    private void loadClient() {
        Kunde k = null;
        try {
            File file = FileReader.getFile();
            String ext = FileReader.getExtension(file);

            if (ext.equals(".jobj")) {
                k = (Kunde) new JobjReader().readDataFromFile(file);
            } else if (ext.equals(".csv")) {
                k = (Kunde) new CsvReader().readDataFromFile(file);
            }
        } catch (InvalidFileFormatException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }

        if (k != null) {
            MainApp.getClientList().add(k);
            addClientToListView(k);
        }
    }

    /**
     * This method adds a client to the clientsListView
     * @param k The client to be added
     */
    private void addClientToListView(Kunde k) {
        clientsList.getItems().addAll(k);
    }


    @FXML
    private void test(ActionEvent event) {
        HashMap<String, Scene> map = ViewChanger.getViewMap();
        for (Map.Entry m : map.entrySet()) {
            System.out.println(m.getKey()+ " " + m.getValue());
        }
        System.out.println();


    }
}
