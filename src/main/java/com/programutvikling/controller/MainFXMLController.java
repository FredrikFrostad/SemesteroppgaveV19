package com.programutvikling.controller;

import com.programutvikling.models.osType.OSType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.io.File;

public class MainFXMLController {

    private File projectFilePath;

    public void initialize() {
        // TODO
    }

    @FXML
    private Label label;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Morra di er mann!");
    }


    /**
     * Metoden finner os typen som maskinen kjører på, og oppretter en prosjektmappe under current user home.
     */
    private void findOSTypeAndCreateProjectFolder() {
        String userHome = System.getProperty("user.home");

        if (OSType.getOsType() == OSType.OS.WINDOWS || OSType.getOsType() == OSType.OS.MAC) {
            projectFilePath = new File(userHome + File.separator + "SemesteroppgaveGruppe8");
            {
                if (projectFilePath.exists()) {
                    System.out.println("Mappen finnes allerede: " + projectFilePath); //TODO: Fjerne testkode ved endelig implementering
                } else {
                    System.out.println("Oppretter mappe: " + projectFilePath); //TODO: Fjerne testkode ved endelig implementering
                    boolean isPathCreated = projectFilePath.mkdirs();

                    if (isPathCreated) {
                        System.out.println("Mappa er laget!"); //TODO: Fjerne testkode ved endelig implementering
                    }
                }
            }
        }
    }
}
