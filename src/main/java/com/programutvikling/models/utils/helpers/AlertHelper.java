package com.programutvikling.models.utils.helpers;

import javafx.scene.control.Alert;

public class AlertHelper {

    public static void createAlert(Alert.AlertType type, String title, String msg) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
