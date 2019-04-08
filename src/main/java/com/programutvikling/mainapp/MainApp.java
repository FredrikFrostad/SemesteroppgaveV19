package com.programutvikling.mainapp;

import com.programutvikling.controller.MainFXMLController;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;


public class MainApp extends Application {

    /**
     * Enum for å sette forskjellige testmoduser under utvikling.
     * Dette for å direkte kunne teste relevante fxml-komponenter uten å måtte ta ensyn til kontrolflyt i GUI
     */
    enum runMode {

        NORMAL,
        TEST_STARTPAGE,
        TEST_ADDCLIENT,
        TEST_ADDPOLICY,
        TEST_FREDRIK,
        TEST_JACOB,
    }

    /**
     * Metode for testing og utvikling.
     * Denne metoden injiserer riktig fxml i fxmlloaderen basert på hvilken testmodus programmet startes i
     * @param mode En enum som definerer type testmodus
     * @return En string som gir path til korrekt FXML dokument
     */
    private String fxmlChooser(runMode mode) {
        String out = null;

        switch (mode) {
            case NORMAL:
                out = "../../../views/scene.fxml";
                break;
            case TEST_ADDCLIENT:
                //out = "../../../views/NOTDEFINED.fxml";
                break;
            case TEST_ADDPOLICY:
                //out = "../../../views/NOTDEFINED.fxml";
                break;
            case TEST_STARTPAGE:
                //out = "../../../views/NOTDEFINED.fxml";
                break;
            case TEST_FREDRIK:
                //out = "../../../views/NOTDEFINED.fxml";
                break;
            case TEST_JACOB:
                //out = "../../../views/NOTDEFINED.fxml";
            default:
                out = null;

        }

        return out;
    }


    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlChooser(runMode.NORMAL)));
        //Parent root = FXMLLoader.load(getClass().getResource("../../../views/scene.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("../../../views/styles.css").toExternalForm());

        stage.setTitle("SemesteroppgaveV2019");
        stage.setScene(scene);
        stage.setMinWidth(1200);
        stage.setMinHeight(800);
        stage.show();
    }



    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
