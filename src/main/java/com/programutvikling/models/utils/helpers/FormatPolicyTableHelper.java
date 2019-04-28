package com.programutvikling.models.utils.helpers;

import com.programutvikling.mainapp.MainApp;
import com.programutvikling.models.data.ObjectType;
import com.programutvikling.models.data.forsikring.Forsikring;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.util.ArrayList;

public class FormatPolicyTableHelper {

    public static void formatCollumns(TableView<Forsikring> tableView, Forsikring f) {
        ObjectType type = f.getType();

        switch (type) {
            case BÅT:
                formatBoat(tableView);
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

    private static void formatBoat(TableView<Forsikring> tableView) {
        TableColumn<Forsikring,Integer> col1 = new TableColumn<>("Polisenummer");
        col1.setCellValueFactory(new PropertyValueFactory<>("forsikrNr"));

        TableColumn<Forsikring,Double> col2 = new TableColumn<>("Premie");
        col2.setCellValueFactory(new PropertyValueFactory<>("premieAnnum"));

        TableColumn<Forsikring,Double> col3 = new TableColumn<>("Forsikringssum");
        col3.setCellValueFactory(new PropertyValueFactory<>("forsikringsSum"));

        TableColumn<Forsikring,String> col4 = new TableColumn<>("Eier");
        col4.setCellValueFactory(new PropertyValueFactory<>("eier"));

        TableColumn<Forsikring,String> col5 = new TableColumn<>("RegNr");
        col5.setCellValueFactory(new PropertyValueFactory<>("regNr"));

        TableColumn<Forsikring,String> col6 = new TableColumn<>("Båttype");
        col6.setCellValueFactory(new PropertyValueFactory<>("typeBåt"));

        TableColumn<Forsikring,String> col7 = new TableColumn<>("Modell");
        col7.setCellValueFactory(new PropertyValueFactory<>("modell"));

        TableColumn<Forsikring,Integer> col8 = new TableColumn<>("Lengde");
        col8.setCellValueFactory(new PropertyValueFactory<>("lengde"));

        TableColumn<Forsikring,Integer> col9 = new TableColumn<>("Årsmodell");
        col9.setCellValueFactory(new PropertyValueFactory<>("årsmodell"));

        TableColumn<Forsikring,String> col10 = new TableColumn<>("Motor");
        col10.setCellValueFactory(new PropertyValueFactory<>("motorType"));

        TableColumn<Forsikring,String> col11 = new TableColumn<>("Effekt");
        col11.setCellValueFactory(new PropertyValueFactory<>("effekt"));

        tableView.getColumns().addAll(col1, col2, col3, col4, col5,col6, col7, col8, col9, col10, col11);
    }

    private void formatHolidayHome(TableView<Forsikring> tableView) {

    }

    private void formatVilla(TableView<Forsikring> tableView) {

    }

    private void formatTravel(TableView<Forsikring> tableView) {

    }

}
