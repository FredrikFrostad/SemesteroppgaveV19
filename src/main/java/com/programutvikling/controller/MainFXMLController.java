package com.programutvikling.controller;

import com.programutvikling.models.osType.OSType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class MainFXMLController {

    private File projectFilePath;
    private ArrayList<Scene> sceneList;
    private final String PROJECTFOLDER = "SemesteroppgaveV19";


    public void initialize() {
        findOSTypeAndCreateProjectFolder();
    }

    @FXML
    AnchorPane apane;

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
    /**
     * //TODO: Denne metoden skal etterhvert fjernes. Kun brukt for å teste path-hælvete i java
     * Det ser ut som om filpather er litt annerledes enn det jag er vant til i maven. Dersom jeg har forstått
     * det riktig så er resource-path relativ til class-path. I dette tilfellet er classpath lokasjonen til .class-filene
     * i target folderen. Denne genereres av maven når prosjektet bygges. Dersom en resource ikke er tilgjengelig der
     * man forventer å finne den, kan det være en ide å rebuilde prosjektet.
     */
    public void loadNewFXML() {
        try {

            Parent scene2Parent = FXMLLoader.load(getClass().getClassLoader().getResource("views/startpage.fxml"));
            Scene scene2 = new Scene(scene2Parent);
            Stage window = (Stage)apane.getScene().getWindow();
            window.setScene(scene2);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

    public void setChildFXML() {

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
