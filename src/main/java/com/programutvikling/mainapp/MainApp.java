package com.programutvikling.mainapp;

import com.programutvikling.models.data.kunde.Kunde;
import com.programutvikling.models.utils.helpers.ClientNrHelper;
import com.programutvikling.models.utils.osType.OSType;
import com.programutvikling.models.viewChanger.ViewChanger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;


public class MainApp extends Application {

    private File projectFilePath;
    private static final String PROJECTFOLDER = "SemesteroppgaveV19";

    private static ArrayList<Kunde> clientList = new ArrayList<>();

    private static Kunde selectedKunde = null;



    public static ArrayList<Kunde> getClientList() {
        return clientList;
    }

    public static String getPROJECTFOLDER() {
        return PROJECTFOLDER;
    }

    public static void setSelectedKunde(Kunde selected) {
        MainApp.selectedKunde = selected;
    }

    public static Kunde getSelectedKunde() {
        return selectedKunde;
    }


    enum runMode {

        NORMAL,
        TEST_STARTPAGE,
        TEST_ADDCLIENT,
        TEST_ADDPOLICY,
        TEST_FREDRIK,
        TEST_JACOB;

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
                out = "/views/startpage.fxml";
                break;
            case TEST_ADDCLIENT:
                //out = "/views/NOTDEFINED.fxml";
                break;
            case TEST_ADDPOLICY:
                //out = "/views/NOTDEFINED.fxml";
                break;
            case TEST_STARTPAGE:
                //out = "/views/NOTDEFINED.fxml";
                break;
            case TEST_FREDRIK:
                //out = "/views/NOTDEFINED.fxml";
                break;
            case TEST_JACOB:
                //out = "/views/NOTDEFINED.fxml";
            default:
                out = null;

        }

        return out;
    }

    /**
     * Enum for å sette forskjellige testmoduser under utvikling.
     * Dette for å direkte kunne teste relevante fxml-komponenter uten å måtte ta ensyn til kontrolflyt i GUI
     */



    @Override
    public void start(Stage stage) throws Exception {

        // This method is called to create a project folder for the applications files
        // To change the location of the project folder, change the PROJECTFOLDER variable in this class
        findOSTypeAndCreateProjectFolder();

        //This method is called here to insure the file for auto-incrementing client numbers are created
        new ClientNrHelper().init();

        //Parent root = FXMLLoader.load(getClass().getResource(fxmlChooser(runMode.NORMAL)));
        Parent root = FXMLLoader.load(getClass().getResource("/views/mainPage.fxml"));


        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/views/styles.css").toExternalForm());

        // Legger til view i viewmap for å forhindre senere instansiering
        ViewChanger.viewMap.putIfAbsent("mainPage", scene);

        stage.setTitle("SemesteroppgaveV2019");
        stage.setScene(scene);
        stage.show();
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
