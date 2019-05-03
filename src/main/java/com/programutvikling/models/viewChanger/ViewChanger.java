package com.programutvikling.models.viewChanger;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author 576
 */
public class ViewChanger {

    // Denne listen skal inneholde instanser av views i applikasjonen
    public static HashMap<String, Scene> viewMap = new HashMap<>();

    /**
     * Dette kan gjøres ved å ha en egen klasse for views (denne) og sørge for at alle nyew views legges i
     * et hashmap / arraylist som sjekkes før instaniering av nytt view. Dersom viewet allerede er instansier,
     * skal vi IKKE laste på nytt via FXMLLoader, men hente inn den kjørende instansen fra liste.
     */

    /**
     * Metode som bytter view i appliksjonen. Dersom viewet allerede er instansiert hentes det ut ifra hashmap.
     * Dersom det ikke er tidligere instansiert lastes det via FXMLloader og legges til hashmapet.
     * @param currentParent Dette er parent i viewet vi bytter fra. Denne trenger vi for få tak i scenen
     * @param viewName Key til hashmap
     * @param path Path til nytt view i resourcemappe
     */
    public void setView(Parent currentParent, String viewName, String path) {
        Stage stage = (Stage)currentParent.getScene().getWindow();

        if (viewMap.containsKey(viewName)) {
            stage.setScene(viewMap.get(viewName));
        } else {

            Parent parent = null;
            try {
                parent = FXMLLoader.load(getClass().getClassLoader().getResource(path));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Scene scene = new Scene(parent);
            //TODO: sjekk at dette virker!!
            scene.getStylesheets().add(getClass().getResource("/views/styles.css").toExternalForm());
            stage.setScene(scene);
            viewMap.putIfAbsent(viewName, scene);
        }
    }

    public void resetView(String viewName) {
        if (viewMap.containsKey(viewName)) viewMap.remove(viewName);
    }

    public HashMap<String, Scene> getViewMap() {
        return viewMap;
    }

}
