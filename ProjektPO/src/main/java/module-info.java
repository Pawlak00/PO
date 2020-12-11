module org.myproject {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.myproject to javafx.fxml;
    exports org.myproject;
}