module com.example {
    requires transitive javafx.graphics;
    requires transitive javafx.controls;
    requires javafx.fxml;
    
    requires org.mongodb.driver.core;
    requires transitive org.mongodb.bson;
    requires org.mongodb.driver.sync.client;

    opens com.example to javafx.fxml;
    exports com.example;
}
