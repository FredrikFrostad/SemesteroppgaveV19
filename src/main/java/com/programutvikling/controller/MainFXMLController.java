package com.programutvikling.controller;

import com.programutvikling.models.osType.OSType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Path;


public class MainFXMLController {

    private File projectFilePath;
    private final String PROJECTFOLDER = "SemesteroppgaveV19";

    public void initialize() {
        findOSTypeAndCreateProjectFolder();
        System.out.println("Du er her!!!");
    }

    @FXML
    private Label label;

    @FXML
    private void exit() {
        System.exit(0);
    }

    @FXML
    private void save() {}

    @FXML
    private void saveAs() {}

    @FXML
    private void newFile() {}

    @FXML private void open() {}

    @FXML
    private void handleButtonAction(ActionEvent event) {
        try {

            Parent scene2Parent = FXMLLoader.load(getClass().getClassLoader().getResource("views/view2Example.fxml"));
            Scene scene2 = new Scene(scene2Parent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene2);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

    /**
     * Denne metodensignaturen kan fylles med ting som er greie å teste via en knapp.
     * //TODO: Fjern denne metoden så fort den er overflødig
     * @param event
     */
    @FXML
    private void handleTestAction(ActionEvent event) {

    }


    /**
     * Metoden finner os typen som maskinen kjører på, og oppretter en prosjektmappe under current user home.
     */
    private void findOSTypeAndCreateProjectFolder() {
        String userHome = System.getProperty("user.home");

        if (OSType.getOsType() == OSType.OS.WINDOWS ||
                    OSType.getOsType() == OSType.OS.MAC ||
                        OSType.getOsType() == OSType.OS.LINUX) {
            projectFilePath = new File(userHome + File.separator + PROJECTFOLDER);
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
