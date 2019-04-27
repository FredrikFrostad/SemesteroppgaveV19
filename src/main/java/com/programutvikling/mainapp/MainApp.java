package com.programutvikling.mainapp;

import com.programutvikling.models.data.kunde.Kunde;
import com.programutvikling.models.filehandlers.reader.CsvObjectBuilder;
import com.programutvikling.models.filehandlers.reader.CsvReader;
import com.programutvikling.models.filehandlers.writer.CsvWriter;
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
import java.io.IOException;
import java.util.ArrayList;


public class MainApp extends Application {

    private static File projectFilePath;
    private static File databaseFilePath;
    private static File userSaveFilepath;
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


    @Override
    public void start(Stage stage) throws Exception {
        findOSTypeAndCreateProjectFolder();

        //make register file
        new ClientNrHelper().init();

        //Initialise testData
        initTestObjects();

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
            databaseFilePath = new File(projectFilePath + File.separator + "Database");
            userSaveFilepath = new File(projectFilePath + File.separator + "UserData");
            {
                if (projectFilePath.exists()) {
                    System.out.println("Mappen finnes allerede: " + projectFilePath); //TODO: Fjerne testkode ved endelig implementering
                } else {
                    System.out.println("Oppretter mappe: " + projectFilePath); //TODO: Fjerne testkode ved endelig implementering
                    boolean isPathCreated = false;

                    if (projectFilePath.mkdirs() && databaseFilePath.mkdir() && userSaveFilepath.mkdir() ) {
                        isPathCreated = true;
                    }
                    if (isPathCreated) {
                        System.out.println("Mappa er laget!"); //TODO: Fjerne testkode ved endelig implementering
                    }
                }
            }
        }
    }

    private void initTestObjects() {
        // Create testdata if database is empty
        if (databaseFilePath.listFiles().length == 0) {
            CsvReader reader = new CsvReader();
            try {
                ArrayList<String[]> kList = reader.readDataFromFile(new File("testKunder.csv"));
                for (String[] s : kList) {
                    clientList.add((Kunde) new CsvObjectBuilder().buildObjectFromString(s));
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
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
