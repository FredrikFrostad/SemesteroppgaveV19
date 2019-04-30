package com.programutvikling.models.utils.helpers;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class AlertHelper {

    public static void createAlert(Alert.AlertType type, String title, String msg) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public static Optional<ButtonType> createOptionAlert(Alert.AlertType type, String title, String msg, String okButton, String cancelButton) {
        ButtonType ok = new ButtonType(okButton, ButtonBar.ButtonData.OK_DONE);
        ButtonType cancel = new ButtonType(cancelButton, ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(type,msg, ok, cancel);
        alert.setTitle(title);

        return alert.showAndWait();
    }
}
