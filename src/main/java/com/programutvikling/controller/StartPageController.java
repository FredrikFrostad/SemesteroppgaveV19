package com.programutvikling.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class StartPageController {

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

    }

    @FXML
    private void newPolicy(ActionEvent event) {

    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        label.setText("Morra di er mann!");
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getClassLoader().getResource("views/scene2.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene2 = new Scene(parent);
        Stage stage = (Stage)startpageparent.getParent().getScene().getWindow();
        stage.setScene(scene2);

    }
}
