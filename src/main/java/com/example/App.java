package com.example;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        View view = new View();
        Controller controller = new Controller(view);
        view.setController(controller);

        view.start(stage);
    }

    public static void main(String[] args) {
        launch();
    }

}