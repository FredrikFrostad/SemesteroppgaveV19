package com.programutvikling.models.utils.helpers;

import com.programutvikling.controller.MainPageController;
import com.programutvikling.models.data.skademelding.Skademelding;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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


    public static void formatCollumns(MainPageController controller, TableView<Skademelding> tableView,
                                      Skademelding skademelding){

    }
    public static void formatSkademelding(TableView<Skademelding> tableView,
                                          MainPageController controller){
        TableColumn<Skademelding, String> col4 = new TableColumn<>("");

    }


}
