package com.programutvikling.controller;

import com.programutvikling.models.viewChanger.ViewChanger;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class ClientsController {

    @FXML AnchorPane clientsInclude;

    @FXML
    private void refresh() {}

    @FXML
    private void back() {
        ViewChanger vc = new ViewChanger();
        vc.setView(clientsInclude, "startpage", "views/startpage.fxml");
    }
}
