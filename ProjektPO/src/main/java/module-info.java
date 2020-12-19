module org.myproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires guava;
    opens org.myproject to javafx.fxml;
    exports org.myproject;
}