package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import org.bson.Document;


public class View extends Application {
    private Controller controller;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void start(Stage stage) {
        Label idLabel = new Label("ID:");
        Label nameLabel = new Label("Name:");
        Label ageLabel = new Label("Age:");
        Label cityLabel = new Label("City:");

        TextField idTextField = new TextField();
        TextField nameTextField = new TextField();
        TextField ageTextField = new TextField();
        TextField cityTextField = new TextField();

        Button addButton = new Button("Add");
        Button updateButton = new Button("Update");
        Button deleteButton = new Button("Delete");
        Button readButton = new Button("Read");

        idTextField.setPrefWidth(200);
        idTextField.setPrefHeight(40);
        nameTextField.setPrefWidth(200);
        nameTextField.setPrefHeight(40);
        ageTextField.setPrefWidth(200);
        ageTextField.setPrefHeight(40);
        cityTextField.setPrefWidth(200);
        cityTextField.setPrefHeight(40);

        addButton.setPrefWidth(200);
        addButton.setPrefHeight(40);
        updateButton.setPrefWidth(200);
        updateButton.setPrefHeight(40);
        deleteButton.setPrefWidth(200);
        deleteButton.setPrefHeight(40);
        readButton.setPrefWidth(200);
        readButton.setPrefHeight(40);

        GridPane gridPane = new GridPane();

        gridPane.add(idLabel, 0, 0);
        gridPane.add(idTextField, 1, 0);
        gridPane.add(nameLabel, 0, 1);
        gridPane.add(nameTextField, 1, 1);
        gridPane.add(ageLabel, 0, 2);
        gridPane.add(ageTextField, 1, 2);
        gridPane.add(cityLabel, 0, 3);
        gridPane.add(cityTextField, 1, 3);

        gridPane.add(addButton, 0, 4);
        gridPane.add(readButton, 1, 4);
        gridPane.add(deleteButton, 1, 5);
        gridPane.add(updateButton, 0, 5);

        Scene scene = new Scene(gridPane, 400, 200);

        addButton.setOnAction(e -> controller.add(nameTextField.getText(), ageTextField.getText(), cityTextField.getText()));
        updateButton.setOnAction(e -> controller.update(idTextField.getText(), nameTextField.getText(), ageTextField.getText(), cityTextField.getText()));
        deleteButton.setOnAction(e -> controller.delete(idTextField.getText()));
        readButton.setOnAction(e -> controller.read(idTextField.getText()));

        stage.setScene(scene);
        stage.setTitle("MongoDB CRUD Operations");
        stage.setResizable(false);
        stage.show();
    }

    public void display(AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void showData(Document document) {
        Alert alert = new Alert(AlertType.INFORMATION, "Document found");
        alert.setContentText("Found document: " + document.toJson());
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }

}
