package com.programutvikling.models.utils.helpers;

import com.programutvikling.controller.mainPageController;
import com.programutvikling.models.data.ObjectType;
import com.programutvikling.models.data.forsikring.Båt;
import com.programutvikling.models.data.forsikring.Forsikring;
import com.programutvikling.models.data.forsikring.Fritidsbolig;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;

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

        formatCommonCells(tableView, controller);

        switch (type) {
            case BÅT:
                formatBoat(tableView, controller);
                break;
            case FRITIDSBOLIG:
                formatHomeowner(tableView, controller);
                break;
            case VILLAINNBO:
                formatHomeowner(tableView, controller);
                break;
            case REISE:
                formatTravel(tableView, controller);
                break;
            default:
        }
    }

    /**
     * Defines all eventhandlers and cellvalueproperties for columns common to all policies
     * @param tableView Parent node to Tablecolumns
     * @param controller This is used to refresh the view if an editevent is triggered
     */
    private static void formatCommonCells(TableView<Forsikring> tableView, mainPageController controller) {
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

        tableView.getColumns().addAll(col1, col2, col3);
    }

    /**
     * Defines all eventhandlers and cellproperties when a boat insurance policy is displayed
     * @param tableView Parent node to Tablecolumns
     */
    private static void formatBoat(TableView<Forsikring> tableView, mainPageController controller) {
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

        tableView.getColumns().addAll(col4, col5,col6, col7, col8, col9, col10, col11);
    }

    /**
     * Defines all eventhandlers and cellvalueproperties when a Homeowners policy is selected.
     * @param tableView
     * @param controller
     */
    private static void formatHomeowner(TableView<Forsikring> tableView, mainPageController controller) {
        TableColumn<Forsikring,String> col4 = new TableColumn<>("Adresse");
        col4.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        col4.setCellFactory(TextFieldTableCell.forTableColumn());
        col4.setOnEditCommit(forsikringStringCellEditEvent -> {
            Fritidsbolig f = (Fritidsbolig)tableView.getSelectionModel().getSelectedItem();
            f.setAdresse(forsikringStringCellEditEvent.getNewValue());
            controller.refreshTable();
        });

        TableColumn<Forsikring,Integer> col5 = new TableColumn<>("ByggeÅr");
        col5.setCellValueFactory(new PropertyValueFactory<>("byggeaar"));
        col5.setCellFactory(TextFieldTableCell.forTableColumn(intConverter));
        col5.setOnEditCommit(forsikringIntegerCellEditEvent -> {
            Fritidsbolig f = (Fritidsbolig)tableView.getSelectionModel().getSelectedItem();
            f.setByggeaar(forsikringIntegerCellEditEvent.getNewValue());
            controller.refreshTable();
        });

        TableColumn<Forsikring,String> col6 = new TableColumn<>("Boligtype");
        col6.setCellValueFactory(new PropertyValueFactory<>("boligtype"));
        col6.setCellFactory(TextFieldTableCell.forTableColumn());
        col6.setOnEditCommit(forsikringStringCellEditEvent -> {
            Fritidsbolig f = (Fritidsbolig)tableView.getSelectionModel().getSelectedItem();
            f.setBoligtype(forsikringStringCellEditEvent.getNewValue());
            controller.refreshTable();
        });

        TableColumn<Forsikring,String> col7 = new TableColumn<>("Materiale");
        col7.setCellValueFactory(new PropertyValueFactory<>("byggemateriale"));
        col7.setCellFactory(TextFieldTableCell.forTableColumn());
        col7.setOnEditCommit(forsikringStringCellEditEvent -> {
            Fritidsbolig f = (Fritidsbolig)tableView.getSelectionModel().getSelectedItem();
            f.setByggemateriale(forsikringStringCellEditEvent.getNewValue());
            controller.refreshTable();
        });

        TableColumn<Forsikring,String> col8 = new TableColumn<>("Standard");
        col8.setCellValueFactory(new PropertyValueFactory<>("standard"));
        col8.setCellFactory(TextFieldTableCell.forTableColumn());
        col8.setOnEditCommit(forsikringStringCellEditEvent -> {
            Fritidsbolig f = (Fritidsbolig)tableView.getSelectionModel().getSelectedItem();
            f.setStandard(forsikringStringCellEditEvent.getNewValue());
            controller.refreshTable();
        });

        TableColumn<Forsikring,Integer> col9 = new TableColumn<>("areal");
        col9.setCellValueFactory(new PropertyValueFactory<>("areal"));
        col9.setCellFactory(TextFieldTableCell.forTableColumn(intConverter));
        col9.setOnEditCommit(forsikringIntegerCellEditEvent -> {
            Fritidsbolig f = (Fritidsbolig)tableView.getSelectionModel().getSelectedItem();
            f.setAreal(forsikringIntegerCellEditEvent.getNewValue());
            controller.refreshTable();
        });

        TableColumn<Forsikring,Double> col10 = new TableColumn<>("Beløp Bygning");
        col10.setCellValueFactory(new PropertyValueFactory<>("forsikringsbeløpByggning"));
        col10.setCellFactory(TextFieldTableCell.forTableColumn(doubleConverter));
        col10.setOnEditCommit(forsikringDoubleCellEditEvent -> {
            Fritidsbolig f = (Fritidsbolig)tableView.getSelectionModel().getSelectedItem();
            f.setForsikringsbeløpByggning(forsikringDoubleCellEditEvent.getNewValue());
            controller.refreshTable();
        });

        TableColumn<Forsikring,Double> col11 = new TableColumn<>("Beløp Innbo");
        col11.setCellValueFactory(new PropertyValueFactory<>("forsikringsbeløpInnbo"));
        col11.setCellFactory(TextFieldTableCell.forTableColumn(doubleConverter));
        col11.setOnEditCommit(forsikringDoubleCellEditEvent -> {
            Fritidsbolig f = (Fritidsbolig)tableView.getSelectionModel().getSelectedItem();
            f.setForsikringsbeløpInnbo(forsikringDoubleCellEditEvent.getNewValue());
            controller.refreshTable();
        });

        tableView.getColumns().addAll(col4, col5, col6, col7, col8, col9, col10, col11);
    }

    private static void formatTravel (TableView <Forsikring> tableView, mainPageController controller) {

    }

}
