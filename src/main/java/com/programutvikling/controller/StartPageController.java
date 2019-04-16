package com.programutvikling.controller;

import com.programutvikling.models.data.kunde.Kunde;
import com.programutvikling.models.filehandlers.reader.FileReader;
import com.programutvikling.models.viewChanger.ViewChanger;
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

    private ArrayList<Kunde> clients;

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

    @FXML
    private void loadClient() {
        try {
            File file = FileReader.getFile();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void test(ActionEvent event) {
        HashMap<String, Scene> map = ViewChanger.getViewMap();
        for (Map.Entry m : map.entrySet()) {
            System.out.println(m.getKey()+ " " + m.getValue());
        }
        System.out.println();


    }

    public void addClient(Kunde kunde) {clients.add(kunde);
    }
}
