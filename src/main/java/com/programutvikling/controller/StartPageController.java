package com.programutvikling.controller;

import com.programutvikling.data.tesklasser.Kunde;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

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

    }

    @FXML
    private void newClient(ActionEvent event) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getClassLoader().getResource("views/newClient.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene2 = new Scene(parent);
        Stage stage = (Stage)startpageparent.getParent().getScene().getWindow();
        stage.setScene(scene2);
    }

    @FXML
    private void newPolicy(ActionEvent event) {

    }

    public void addToClientList(Kunde kunde) {
        clientList.add(kunde);
    }
}
