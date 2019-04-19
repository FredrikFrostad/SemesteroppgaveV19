package com.programutvikling.controller;

import com.programutvikling.mainapp.MainApp;
import com.programutvikling.models.data.kunde.Kunde;
import com.programutvikling.models.exceptions.InvalidFileFormatException;
import com.programutvikling.models.filehandlers.FileHandler;
import com.programutvikling.models.filehandlers.reader.CsvReader;
import com.programutvikling.models.filehandlers.reader.FileReader;
import com.programutvikling.models.filehandlers.reader.JobjReader;
import com.programutvikling.models.viewChanger.ViewChanger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class redesignTestController {

    @FXML
    BorderPane rootPane;

    @FXML
    ListView<Kunde> clientList;

    @FXML
    TableView table;

    @FXML
    public void initialize() {
    }

    @FXML
    private void nyForsikring(ActionEvent event) {}

    @FXML
    private void loadKunde(ActionEvent event) {
        ArrayList<Kunde> list = MainApp.getClientList();

        try {
            File file = FileReader.getFile();
            if (FileHandler.getExtension(file).equals(".csv")) {
                list.add((Kunde)new CsvReader().readDataFromFile(file));
            }
            else list.add((Kunde)new JobjReader().readDataFromFile(file));

        } catch (FileNotFoundException e){
            e.printStackTrace();
            e.getMessage();
        } catch (InvalidFileFormatException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        refresh(event);
    }

    @FXML
    private void newClient(ActionEvent event) {
        ViewChanger vc = new ViewChanger();
        vc.setView(rootPane, "mainPage", "views/newClient.fxml");
    }

    @FXML
    private void refresh(ActionEvent event) {
        clientList.getItems().removeAll(MainApp.getClientList());
        clientList.getItems().addAll(MainApp.getClientList());
    }



}
