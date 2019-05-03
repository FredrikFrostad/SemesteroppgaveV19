package com.programutvikling.models.utils.helpers;

import com.programutvikling.controller.MainPageController;
import com.programutvikling.models.data.skademelding.Skademelding;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;

/**
 * @author 576
 */
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

    public static void formatSkademelding(TableView<Skademelding> tableView,
                                          MainPageController controller){
        TableColumn<Skademelding, String> col1 = new TableColumn<>("Type");
        col1.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<Skademelding, String> col2 = new TableColumn<>("forsikringsNr");
        col2.setCellValueFactory(new PropertyValueFactory<>("forsikrNr"));

        TableColumn<Skademelding, String> col3 = new TableColumn<>("Skade nummer");
        col3.setCellValueFactory(new PropertyValueFactory<>("skadeNr"));

        TableColumn<Skademelding, String> col4 = new TableColumn<>("Skadebeskrivelse");
        col4.setCellValueFactory(new PropertyValueFactory<>("skadeBeskrivelse"));

        TableColumn<Skademelding, String> col5 = new TableColumn<>("Kontakt info");
        col5.setCellValueFactory(new PropertyValueFactory<>("kontaktinfoVitner"));

        TableColumn<Skademelding, String> col6 = new TableColumn<>("Takseringsbeløp");
        col6.setCellValueFactory(new PropertyValueFactory<>("takseringsBelop"));

        TableColumn<Skademelding, String> col7 = new TableColumn<>("Utbetalt");
        col7.setCellValueFactory(new PropertyValueFactory<>("utbetaltErstatning"));

        tableView.getColumns().addAll(col1, col2, col3, col4, col5, col6, col7);
    }


}
