package com.programutvikling.models.utils.helpers;

import com.programutvikling.controller.MainPageController;
import com.programutvikling.mainapp.MainApp;
import com.programutvikling.models.data.kunde.Kunde;
import com.programutvikling.models.filehandlers.ExtensionHandler;
import com.programutvikling.models.filehandlers.reader.CsvObjectBuilder;
import com.programutvikling.models.filehandlers.reader.CsvReader;
import com.programutvikling.models.filehandlers.reader.JobjReader;
import com.programutvikling.models.filehandlers.writer.CsvWriter;
import com.programutvikling.models.filehandlers.writer.JobjWriter;
import com.programutvikling.models.utils.dbHandlers.DbExportHandlerCsv;
import com.programutvikling.models.utils.dbHandlers.DbImportHandlerCsv;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import java.io.File;
import java.util.ArrayList;

/**
 * @author 576
 */
public class ThreadHelper {

    public void importFileThread(File threadfile, ProgressBar progressBar, Text progressText, MainPageController controller) throws Exception {
        Service<Void> threadService = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        controller.setLock(true);

                        progressBar.setVisible(true);
                        progressText.setText("Loading data");
                        progressText.setVisible(true);

                        System.out.println("Starting file import task!");

                        if (ExtensionHandler.getExtension(threadfile).equals(".jobj")) {
                            JobjReader reader = new JobjReader();
                            ArrayList<Kunde> list = (ArrayList<Kunde>) reader.readDataFromFile(threadfile);
                            MainApp.getClientList().addAll(list);
                        } else {
                            DbImportHandlerCsv importer = new DbImportHandlerCsv();
                            importer.importDbFromCsv(threadfile.getParent());
                        }

                        progressText.setVisible(false);
                        progressBar.setVisible(false);
                        controller.refreshTable();

                        controller.setLock(false);

                        System.out.println("File import task completed");
                        return null;
                    }
                };
            }
        };
        threadService.start();
    }

    public void exportFileThread(File threadfile, ProgressBar progressBar, Text progressText, MainPageController controller) throws Exception {
        Service<Void> threadService = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        controller.setLock(true);
                        progressBar.setVisible(true);
                        progressText.setText("Exporting data");
                        progressText.setVisible(true);

                        System.out.println("Starting file export task!");

                        if (ExtensionHandler.getExtension(threadfile).equals(".jobj")) {
                            JobjWriter writer = new JobjWriter();
                            writer.writeObjectDataToFile(threadfile, MainApp.getClientList());
                        } else {
                            DbExportHandlerCsv exporter = new DbExportHandlerCsv(threadfile.getAbsolutePath());
                            exporter.exportDbAsCsv();
                        }

                        progressText.setVisible(false);
                        progressBar.setVisible(false);
                        controller.setLock(false);

                        System.out.println("File export task completed");
                        return null;
                    }
                };
            }
        };
        threadService.start();
    }

    public void initDbThread(ProgressBar progressBar, Text progressText, MainPageController controller) {
        Service<Void> threadService = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        controller.setLock(true);

                        progressBar.setVisible(true);
                        progressText.setText("Importing database");
                        progressText.setVisible(true);

                        System.out.println("Starting database import task!");

                        new DbImportHandlerCsv().importDbFromCsv(null);

                        progressText.setVisible(false);
                        progressBar.setVisible(false);

                        controller.refreshTable();
                        controller.setLock(false);

                        System.out.println("Database import task completed");
                        return null;
                    }
                };
            }
        };
        threadService.start();
    }

    public void saveDbThread(ProgressBar progressBar, Text progressText, MainPageController controller) {
        Service<Void> threadService = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        controller.setLock(true);

                        progressBar.setVisible(true);
                        progressText.setText("Saving database");
                        progressText.setVisible(true);

                        System.out.println("Starting database save task!");

                        new DbExportHandlerCsv().exportDbAsCsv();

                        progressText.setVisible(false);
                        progressBar.setVisible(false);

                        controller.refreshTable();
                        controller.setLock(false);

                        System.out.println("Database save task completed");
                        return null;
                    }
                };
            }
        };
        threadService.start();
    }

    public void exportObjectThread (File threadfile, Object o, ProgressBar progressBar, Text progressText, MainPageController controller) {
        Service<Void> threadService = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        controller.setLock(true);
                        progressBar.setVisible(true);
                        progressText.setText("Exporting object");
                        progressText.setVisible(true);

                        System.out.println("Starting object export task!");

                        if (ExtensionHandler.getExtension(threadfile).equals(".jobj")) {
                            JobjWriter writer = new JobjWriter();
                            writer.writeObjectDataToFile(threadfile, o);
                        } else {
                            new CsvWriter().writeObjectDataToFile(threadfile, o);
                        }

                        progressText.setVisible(false);
                        progressBar.setVisible(false);
                        controller.setLock(false);

                        System.out.println("Object export task completed");
                        return null;
                    }
                };
            }
        };
        threadService.start();
    }

    public void importObjectThread (File threadfile, ProgressBar progressBar, Text progressText, MainPageController controller) {
        Service<Void> threadService = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        controller.setLock(true);
                        progressBar.setVisible(true);
                        progressText.setText("Importing object");
                        progressText.setVisible(true);

                        System.out.println("Starting object import task!");

                        if (ExtensionHandler.getExtension(threadfile).equals(".jobj")) {
                            try {
                                Kunde k = (Kunde)new JobjReader().readDataFromFile(threadfile);

                                boolean match = false;
                                for (Kunde kunde : MainApp.getClientList()) {
                                    if (kunde.getForsikrNr() == k.getForsikrNr()) {
                                        match = true;
                                    }
                                }
                                if (!match) {
                                    MainApp.getClientList().add(k);
                                }

                            } catch (Exception e) {
                                AlertHelper.createAlert(Alert.AlertType.ERROR, "Feil", "Filen inneholder ikke et kundeobjekt");
                            }

                        } else {
                            try {
                                CsvObjectBuilder builder = new CsvObjectBuilder();
                                Kunde k = (Kunde) builder.buildObjectFromString(new CsvReader().readDataFromFile(threadfile).get(0));

                                boolean match = false;
                                for (Kunde kunde : MainApp.getClientList()) {
                                    if (kunde.getForsikrNr() == k.getForsikrNr()) {
                                        match = true;
                                    }
                                }
                                if (!match) {
                                    MainApp.getClientList().add(k);
                                }

                            } catch (Exception e) {
                                AlertHelper.createAlert(Alert.AlertType.ERROR, "Feil", "Kan ikke laste kunde fra fil");
                            }
                        }

                        progressText.setVisible(false);
                        progressBar.setVisible(false);
                        controller.refreshTable();
                        controller.setLock(false);

                        System.out.println("Object import task completed");
                        return null;
                    }
                };
            }
        };
        threadService.start();
    }
}
