package com.programutvikling.models.utils.helpers;

import com.programutvikling.controller.mainPageController;
import com.programutvikling.mainapp.MainApp;
import com.programutvikling.models.data.ObjectType;
import com.programutvikling.models.data.forsikring.Båt;
import com.programutvikling.models.data.forsikring.Forsikring;
import com.programutvikling.models.exceptions.InvalidNumberFormatException;
import com.programutvikling.models.inputhandlers.Inputvalidator;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.util.ArrayList;

public class FormatPolicyTableHelper {

    private static  StringConverter<Integer> intConverter = new StringConverter<Integer>() {
        @Override
        public String toString(Integer integer) {
            return Integer.toString(integer);
        }


        @Override
        public Integer fromString(String s) {
            return Integer.parseInt(s);

        }
    };


    private static StringConverter<Double> doubleConverter = new StringConverter<Double>() {
        @Override
        public String toString(Double aDouble) {
            return Double.toString(aDouble);
        }

        @Override
        public Double fromString(String s) {

            return Double.parseDouble(s);
        }
    };


    public static void formatCollumns(mainPageController controller, TableView<Forsikring> tableView, Forsikring f) {
        ObjectType type = f.getType();

        switch (type) {
            case BÅT:
                formatBoat(tableView, controller);
                break;
            case FRITIDSBOLIG:
                break;
            case REISE:
                break;
            case VILLAINNBO:
                break;
            default:
        }
    }


    /**
     * Defines all eventhandlers and cellproperties when a boat insurance policy is displayed
     * @param tableView Parent node to Tablecolumns
     */
    private static void formatBoat(TableView<Forsikring> tableView, mainPageController controller) {
        TableColumn<Forsikring,Integer> col1 = new TableColumn<>("Polisenummer");
        col1.setCellValueFactory(new PropertyValueFactory<>("forsikrNr"));

        TableColumn<Forsikring,Double> col2 = new TableColumn<>("Årspremie");
        col2.setCellValueFactory(new PropertyValueFactory<>("premieAnnum"));
        col2.setCellFactory(TextFieldTableCell.forTableColumn(doubleConverter));
        col2.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Forsikring, Double>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Forsikring, Double> forsikringDoubleCellEditEvent) {
                tableView.getSelectionModel().getSelectedItem().setPremieAnnum(forsikringDoubleCellEditEvent.getNewValue());
                controller.refreshTable();
            }
        });

        TableColumn<Forsikring,Double> col3 = new TableColumn<>("Forsikringssum");
        col3.setCellValueFactory(new PropertyValueFactory<>("forsikringsSum"));
        col3.setCellFactory(TextFieldTableCell.forTableColumn(doubleConverter));
        col3.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Forsikring, Double>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Forsikring, Double> forsikringDoubleCellEditEvent) {
                Båt b = (Båt)tableView.getSelectionModel().getSelectedItem();
                b.setForsikringsSum(forsikringDoubleCellEditEvent.getNewValue());
                controller.refreshTable();
            }
        });


        TableColumn<Forsikring,String> col4 = new TableColumn<>("Eier");
        col4.setCellValueFactory(new PropertyValueFactory<>("eier"));
        col4.setCellFactory(TextFieldTableCell.forTableColumn());
        col4.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Forsikring, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Forsikring, String> forsikringStringCellEditEvent) {
                Båt b = (Båt)tableView.getSelectionModel().getSelectedItem();
                b.setEier(forsikringStringCellEditEvent.getNewValue());
                controller.refreshTable();
            }
        });


        TableColumn<Forsikring,String> col5 = new TableColumn<>("RegNr");
        col5.setCellValueFactory(new PropertyValueFactory<>("regNr"));
        col5.setCellFactory(TextFieldTableCell.forTableColumn());
        col5.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Forsikring, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Forsikring, String> forsikringStringCellEditEvent) {
                Båt b = (Båt)tableView.getSelectionModel().getSelectedItem();
                b.setRegNr(forsikringStringCellEditEvent.getNewValue());
                controller.refreshTable();
            }
        });


        TableColumn<Forsikring,String> col6 = new TableColumn<>("Båttype");
        col6.setCellValueFactory(new PropertyValueFactory<>("typeBåt"));
        col6.setCellFactory(TextFieldTableCell.forTableColumn());
        col6.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Forsikring, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Forsikring, String> forsikringStringCellEditEvent) {
                Båt b = (Båt)tableView.getSelectionModel().getSelectedItem();
                b.setTypeBåt(forsikringStringCellEditEvent.getNewValue());
                controller.refreshTable();
            }
        });

        TableColumn<Forsikring,String> col7 = new TableColumn<>("Modell");
        col7.setCellValueFactory(new PropertyValueFactory<>("modell"));
        col7.setCellFactory(TextFieldTableCell.forTableColumn());
        col7.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Forsikring, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Forsikring, String> forsikringStringCellEditEvent) {
                Båt b = (Båt)tableView.getSelectionModel().getSelectedItem();
                b.setModell(forsikringStringCellEditEvent.getNewValue());
                controller.refreshTable();
            }
        });

        TableColumn<Forsikring,Integer> col8 = new TableColumn<>("Lengde");
        col8.setCellValueFactory(new PropertyValueFactory<>("lengde"));
        col8.setCellFactory(TextFieldTableCell.forTableColumn(intConverter));
        col8.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Forsikring, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Forsikring, Integer> forsikringIntegerCellEditEvent) {
                Båt b = (Båt)tableView.getSelectionModel().getSelectedItem();
                b.setLengde(forsikringIntegerCellEditEvent.getNewValue());
                controller.refreshTable();
            }
        });

        TableColumn<Forsikring,Integer> col9 = new TableColumn<>("Årsmodell");
        col9.setCellValueFactory(new PropertyValueFactory<>("årsmodell"));
        col9.setCellFactory(TextFieldTableCell.forTableColumn(intConverter));
        col9.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Forsikring, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Forsikring, Integer> forsikringIntegerCellEditEvent) {
                Båt b = (Båt)tableView.getSelectionModel().getSelectedItem();
                b.setÅrsmodell(forsikringIntegerCellEditEvent.getNewValue());
                controller.refreshTable();
            }
        });

        TableColumn<Forsikring,String> col10 = new TableColumn<>("Motor");
        col10.setCellValueFactory(new PropertyValueFactory<>("motorType"));
        col10.setCellFactory(TextFieldTableCell.forTableColumn());
        col10.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Forsikring, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Forsikring, String> forsikringStringCellEditEvent) {
                Båt b = (Båt)tableView.getSelectionModel().getSelectedItem();
                b.setMotorType(forsikringStringCellEditEvent.getNewValue());
                controller.refreshTable();
            }
        });

        TableColumn<Forsikring,String> col11 = new TableColumn<>("Effekt");
        col11.setCellValueFactory(new PropertyValueFactory<>("effekt"));
        col11.setCellFactory(TextFieldTableCell.forTableColumn());
        col11.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Forsikring, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Forsikring, String> forsikringStringCellEditEvent) {
                Båt b = (Båt)tableView.getSelectionModel().getSelectedItem();
                b.setEffekt(forsikringStringCellEditEvent.getNewValue());
                controller.refreshTable();
            }
        });

        tableView.getColumns().addAll(col1, col2, col3, col4, col5,col6, col7, col8, col9, col10, col11);
    }

    private void formatHolidayHome(TableView<Forsikring> tableView) {

    }

    private void formatVilla(TableView<Forsikring> tableView) {

    }

    private void formatTravel(TableView<Forsikring> tableView) {

    }


    private static void formatHelper(TableView<Forsikring> tableView, TableColumn column, String colName, String dataField, Boolean editable, String type) {
        column.setText(colName);
        column.setCellValueFactory(new PropertyValueFactory<>(dataField));

        if (editable && type.equals("Double")) {
            column.setCellFactory(TextFieldTableCell.forTableColumn(doubleConverter));
            column.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Forsikring, Double>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Forsikring, Double> forsikringDoubleCellEditEvent) {
                    Forsikring f = tableView.getSelectionModel().getSelectedItem();
                    SelectionModel<Forsikring> editedForsikring = tableView.getSelectionModel();
                }
            });
        }

    }
}
