package com.programutvikling.models.utils.helpers;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Region;

import java.util.Optional;

public class AlertHelper {

    /**
     * Helper method for easily creating alerts to be shown to the user
     * @param type Type of alert
     * @param title Title of alert box
     * @param msg String containig message to user
     */
    public static void createAlert(Alert.AlertType type, String title, String msg) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.showAndWait();
    }

    /**
     * Helper method for easily creating Optionalerts to be shown to the user.
     * Method creates an option alert with two buttons: Cancel and OK
     * @param type Type of alert
     * @param title Title of alert bos
     * @param msg String containing message to user
     * @param okButton String containing OK-button text
     * @param cancelButton String containing CANCEL-button text
     * @return
     */
    public static Optional<ButtonType> createOptionAlert(Alert.AlertType type, String title, String msg, String okButton, String cancelButton) {
        ButtonType ok = new ButtonType(okButton, ButtonBar.ButtonData.OK_DONE);
        ButtonType cancel = new ButtonType(cancelButton, ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(type,msg, ok, cancel);
        alert.setTitle(title);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

        return alert.showAndWait();
    }
}
