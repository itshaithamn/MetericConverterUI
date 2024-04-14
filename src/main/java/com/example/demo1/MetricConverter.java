package com.example.demo1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MetricConverter extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Metric Converter");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(10);
        grid.setHgap(10);

        // Labels
        Label inputLabel = new Label("Input:");
        GridPane.setConstraints(inputLabel, 0, 0);

        Label outputLabel = new Label("Output:");
        GridPane.setConstraints(outputLabel, 0, 1);

        // Text Fields
        TextField inputField = new TextField();
        GridPane.setConstraints(inputField, 1, 0);

        TextField outputField = new TextField();
        GridPane.setConstraints(outputField, 1, 1);

        // Combo Boxes
        ComboBox<String> inputUnitBox = new ComboBox<>();
        inputUnitBox.getItems().addAll("Meters", "Centimeters", "Kilometers", "Millimeters");
        inputUnitBox.setValue("Meters"); // Default value
        GridPane.setConstraints(inputUnitBox, 2, 0);

        ComboBox<String> outputUnitBox = new ComboBox<>();
        outputUnitBox.getItems().addAll("Meters", "Centimeters", "Kilometers", "Millimeters");
        outputUnitBox.setValue("Centimeters"); // Default value
        GridPane.setConstraints(outputUnitBox, 2, 1);

        // Convert Button
        Button convertButton = new Button("Convert");
        GridPane.setConstraints(convertButton, 1, 2);

        // Convert operation
        convertButton.setOnAction(e -> {
            try {
                double inputValue = Double.parseDouble(inputField.getText());
                String inputUnit = inputUnitBox.getValue();
                String outputUnit = outputUnitBox.getValue();
                double result = convert(inputValue, inputUnit, outputUnit);
                outputField.setText(String.valueOf(result));
            } catch (NumberFormatException ex) {
                outputField.setText("Invalid input");
            }
        });

        grid.getChildren().addAll(inputLabel, outputLabel, inputField, outputField,
                inputUnitBox, outputUnitBox, convertButton);

        Scene scene = new Scene(grid, 350, 150);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private double convert(double value, String fromUnit, String toUnit) {
        double result = 0;
        switch (fromUnit) {
            case "Meters":
                result = value * getConversionFactor(toUnit);
                break;
            case "Centimeters":
                result = value / getConversionFactor("Centimeters") * getConversionFactor(toUnit);
                break;
            case "Kilometers":
                result = value / getConversionFactor("Kilometers") * getConversionFactor(toUnit);
                break;
            case "Millimeters":
                result = value / getConversionFactor("Millimeters") * getConversionFactor(toUnit);
                break;
        }
        return result;
    }

    private double getConversionFactor(String unit) {
        switch (unit) {
            case "Meters":
                return 1;
            case "Centimeters":
                return 100;
            case "Kilometers":
                return 0.001;
            case "Millimeters":
                return 1000;
            default:
                return 1;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
