package com.programutvikling.controller;

import com.programutvikling.models.data.forsikring.Forsikring;
import com.programutvikling.models.viewChanger.ViewChanger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

public class redesignTestController {

    @FXML
    BorderPane rootPane;

    @FXML
    TableView table;

    @FXML
    public void initialize() {
    }

     @FXML
    private void saveKunde(ActionEvent event) {}

    @FXML
    private void nyForsikring(ActionEvent event) {}

    @FXML
    private void loadKunde(ActionEvent event) {}

    @FXML
    private void newClient(ActionEvent event) {
        ViewChanger vc = new ViewChanger();
        vc.setView(rootPane, "mainPage", "views/newClient.fxml");
    }



}
