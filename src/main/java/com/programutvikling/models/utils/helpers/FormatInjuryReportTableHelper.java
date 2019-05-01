package com.programutvikling.models.utils.helpers;

import com.programutvikling.controller.mainPageController;
import com.programutvikling.models.data.ObjectType;
import com.programutvikling.models.data.skademelding.Skademelding;
import javafx.beans.property.Property;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;

public class FormatInjuryReportTableHelper {

    private static StringConverter<Integer> integerStringConverter
            = new StringConverter<Integer>() {
        @Override
        public String toString(Integer integer) {
            return Integer.toString(integer);
        }

        @Override
        public Integer fromString(String s) {
            return Integer.parseInt(s);
        }
    };

    private static StringConverter<Double> doubleConverter =
            new StringConverter<Double>() {
        @Override
        public String toString(Double v) {
            return Double.toString(v);
        }

        @Override
        public Double fromString(String s) {
            return Double.parseDouble(s);
        }
    };


    public static void formatCollumns(mainPageController controller, TableView<Skademelding> tableView,
                                      Skademelding skademelding){

    }
    public static void formatSkademelding(TableView<Skademelding> tableView,
                                          mainPageController controller){
        TableColumn<Skademelding, String> col1 = new TableColumn<>("noe1");
        col1.setCellValueFactory(new PropertyValueFactory<>("noe1.1"));

        TableColumn<Skademelding,Double> col2 = new TableColumn<>("Utbetalt");
        col2.setCellValueFactory(new PropertyValueFactory<>("utbetalt"));
        col2.setCellFactory(TextFieldTableCell.forTableColumn(doubleConverter));
        col2.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Skademelding, Double>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Skademelding, Double> skademeldingDoubleCellEditEvent) {
                tableView.getSelectionModel().getSelectedItem().setUtbetaltErstatning(skademeldingDoubleCellEditEvent.getNewValue());
                controller.refreshTable();
            }
        });

        TableColumn<Skademelding,Double> col3 = new TableColumn<>("Forsikringssum");
        col3.setCellValueFactory(new PropertyValueFactory<>("forsikringsSum"));
        col3.setCellFactory(TextFieldTableCell.forTableColumn(doubleConverter));
        col3.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Skademelding, Double>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Skademelding, Double> skademeldingDoubleCellEditEvent) {
                tableView.getSelectionModel().getSelectedItem().setTakseringsBelop(skademeldingDoubleCellEditEvent.getNewValue());
                controller.refreshTable();
            }
        });

    }


}
