module com.company.tp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.company.tp to javafx.fxml, javafx.graphics;
    exports com.company.tp to javafx.fxml, javafx.graphics;
    opens Controllers to javafx.fxml, javafx.graphics;
    exports Controllers to javafx.fxml, javafx.graphics;
    opens Models to javafx.fxml, javafx.graphics;
    exports Models to javafx.fxml, javafx.graphics;


}