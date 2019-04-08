package com.programutvikling.controller;

import com.programutvikling.data.tesklasser.Kunde;
import com.programutvikling.models.viewChanger.ViewChanger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StartPageController {

    private ArrayList<Kunde> clientList;

    @FXML
    AnchorPane startpageparent;

    @FXML
    Label label;

    @FXML
    private void listActive(ActionEvent event) {

    }

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
    private void test(ActionEvent event) {
        HashMap<String, Scene> map = ViewChanger.getViewMap();
        for (Map.Entry m : map.entrySet()) {
            System.out.println(m.getKey()+ " " + m.getValue());
        }
        System.out.println();


    }

    public void addToClientList(Kunde kunde) {
        clientList.add(kunde);
    }
}
