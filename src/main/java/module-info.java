module com.example.demo12 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.jsoup;


    opens com.example.demo12 to javafx.fxml;
    exports com.example.demo12;
}