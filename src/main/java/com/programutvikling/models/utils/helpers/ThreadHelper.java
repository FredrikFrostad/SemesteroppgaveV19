package com.programutvikling.models.utils.helpers;

import com.programutvikling.mainapp.MainApp;
import com.programutvikling.models.data.kunde.Kunde;
import com.programutvikling.models.filehandlers.ExtensionHandler;
import com.programutvikling.models.filehandlers.reader.JobjReader;
import com.programutvikling.models.utils.dbHandlers.DbImportHandlerCsv;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;

import java.io.File;
import java.util.ArrayList;

public class ThreadHelper {

    public void importFileThread(File threadfile) {
        Service<Void> threadService = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        try {
                        System.out.println("Starting file import task!");
                        if (ExtensionHandler.getExtension(threadfile).equals(".jobj")) {
                            JobjReader reader = new JobjReader();
                            ArrayList<Kunde> list = (ArrayList<Kunde>) reader.readDataFromFile(threadfile);
                            MainApp.getClientList().addAll(list);
                        } else {
                            // This implementation is fragile, and only works on files eksported using the
                            // exportToFile method, insuring that the naming scheme of the exported csv files
                            // are kept intact.
                            DbImportHandlerCsv importer = new DbImportHandlerCsv();
                            importer.importDbFromCsv(threadfile.getParent());
                        }
                    } catch (Exception e) {
                        AlertHelper.createAlert(Alert.AlertType.ERROR, "En feil har oppstått", e.getMessage());
                    }
                        System.out.println("File import task completed");
                        return null;
                    }
                };
            }
        };
    }
}
