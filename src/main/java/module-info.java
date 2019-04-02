module hellofx {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.programutvikling.mainapp to javafx.fxml;
    exports com.programutvikling.mainapp;
}