package com.programutvikling.mainapp;

import com.programutvikling.models.data.forsikring.Forsikring;
import com.programutvikling.models.data.kunde.Kunde;
import com.programutvikling.models.utils.helpers.ClientNrHelper;
import com.programutvikling.models.filehandlers.reader.CsvObjectBuilder;
import com.programutvikling.models.filehandlers.reader.CsvReader;
import com.programutvikling.models.utils.helpers.DbExportHelperCsv;
import com.programutvikling.models.utils.osType.OSType;
import com.programutvikling.models.viewChanger.ViewChanger;
import javafx.application.Application;
import static javafx.application.Application.launch;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.Optional;


public class MainApp extends Application {

    private static File projectFilePath;
    private static File databaseFilePath;
    private static File userSaveFilepath;
    private static final String PROJECTFOLDER = "SemesteroppgaveV19";
    private static ArrayList<Kunde> clientList;
    private static Kunde selectedKunde = null;

    @Override
    public void start(Stage stage) throws Exception {

        clientList = new ArrayList<>();

        // This method is called to create a project folder for the applications files
        // To change the location of the project folder, change the PROJECTFOLDER variable in this class
        findOSTypeAndCreateProjectFolder();

        // Eventhandler for program exit
        initOnExitHandler(stage);

        //make register file
        new ClientNrHelper().init();

        //Initialise testData
        initTestObjects();

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
            databaseFilePath = new File(projectFilePath + File.separator + "Database");
            userSaveFilepath = new File(projectFilePath + File.separator + "UserData");
            {
                if (projectFilePath.exists()) {
                    System.out.println("Mappen finnes allerede: " + projectFilePath);
                } else {
                    System.out.println("Oppretter mappe: " + projectFilePath);
                    boolean isPathCreated = false;

                    if (projectFilePath.mkdirs() && databaseFilePath.mkdir() && userSaveFilepath.mkdir() ) {
                        isPathCreated = true;
                    }
                    if (isPathCreated) {
                        System.out.println("Mappa er laget!");
                    }
                }
            }
        }
    }

    /**
     * This method is only here for loading dummy data into the application to make it
     * easier to evaluate the applications functionality.
     */
    private void initTestObjects() {
        // Create testdata if database is empty
        if (databaseFilePath.listFiles().length == 0) {
            System.out.println("No data present, loading evaluation data");
            CsvReader reader = new CsvReader();
            try {

                // Adding dummy clients for evaluation
                ArrayList<String[]> list = reader.readDataFromFile(new File(getClass().getResource("/testObjects/testClients.csv").getFile()));
                for (String[] s : list) {
                    clientList.add((Kunde) new CsvObjectBuilder().buildObjectFromString(s));
                }

                //Adding dummy boat policies for testing
                list = reader.readDataFromFile(new File(getClass().getResource("/testObjects/testBoatPolicies.csv").getFile()));
                readPoliciesFromFile(list);

                // Adding dummy Homeowners policies for evaluation
                list = reader.readDataFromFile(new File(getClass().getResource("/testObjects/testVillaPolicies.csv").getFile()));
                readPoliciesFromFile(list);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void readPoliciesFromFile(ArrayList<String[]> list) {

        Thread thread = new Thread();
        for (String[] s : list) {
            try {
                Forsikring f = (Forsikring) new CsvObjectBuilder().buildObjectFromString(s);
                for (Kunde k : clientList) {
                    if (k.getForsikrNr() == f.getForsikrNr()) {
                        k.getForsikringer().add(f);
                        break;
                        //System.out.println("Added forsikring " + f.getForsikrNr() + " to " + k.toString());
                    }
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void initOnExitHandler(Stage stage) {
        // For catching program exit via OS native close button
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Lagre før lukking");
                alert.setContentText("Lagre endringer før programmet avsluttes?");
                //alert.showAndWait();

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    System.out.println("Stage is closing - writing data to disk");
                    new DbExportHelperCsv().exportDbAsCsv();
                } else {
                    System.out.println("Stage is closing - purging data");
                }
            }
        });
    }

    public static ArrayList<Kunde> getClientList() {
        return clientList;
    }

    public static ArrayList<Forsikring> getInsuranceList;

    public static String getPROJECTFOLDER() {
        return PROJECTFOLDER;
    }

    public static File getDatabaseFilePath() {return databaseFilePath;}

    public static void setSelectedKunde(Kunde selected) {
        MainApp.selectedKunde = selected;
    }

    public static Kunde getSelectedKunde() {
        return selectedKunde;
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
