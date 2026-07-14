package Project4;

/*
 * Class: CMSC203 CRN 40398
 * Instructor: Professor Farnaz Eivazi
 * Description: JavaFX GUI for the Property Management
 * application. Allows the user to add properties,
 * display all properties, and calculate total rent.
 * Due: 7/13/2026
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming
 * assignment independently. I have not copied the code
 * from a student or any source. I have not given my code
 * to any student.
 *
 * Print your Name here: Rick Banerjee
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PropertyAppFX extends Application {

    private ManagementCompany company =
            new ManagementCompany("Campus Realty", "123-45-6789");

    @Override
    public void start(Stage primaryStage) {

        Label titleLabel = new Label("Property Management Application");

        TextField propertyNameField = new TextField();
        propertyNameField.setPromptText("Property Name");

        TextField cityField = new TextField();
        cityField.setPromptText("City");

        TextField rentField = new TextField();
        rentField.setPromptText("Rent Amount");

        TextField ownerField = new TextField();
        ownerField.setPromptText("Owner");

        Button addButton = new Button("Add Property");
        Button showButton = new Button("Show All Properties");
        Button totalButton = new Button("Show Total Rent");
        Button clearButton = new Button("Clear");

        TextArea outputArea = new TextArea();
        outputArea.setEditable(false);
        outputArea.setPrefHeight(200);

        addButton.setOnAction(e -> {

            try {

                String propertyName = propertyNameField.getText().trim();
                String city = cityField.getText().trim();
                double rent = Double.parseDouble(rentField.getText().trim());
                String owner = ownerField.getText().trim();

                Property property =
                        new Property(propertyName, city, rent, owner);

                int result = company.addProperty(property);

                if (result == -1) {
                    outputArea.setText(
                            "Cannot add property.\nMaximum of "
                                    + ManagementCompany.MAX_PROPERTIES
                                    + " properties reached.");
                } else {
                    outputArea.setText(
                            "Property added successfully!\n\n"
                                    + property);
                }

                propertyNameField.clear();
                cityField.clear();
                rentField.clear();
                ownerField.clear();

            } catch (NumberFormatException ex) {

                outputArea.setText(
                        "Error: Rent must be a valid number.");

            } catch (Exception ex) {

                outputArea.setText(
                        "Error adding property.");

            }

        });

        showButton.setOnAction(e -> {
            outputArea.setText(company.toString());
        });

        totalButton.setOnAction(e -> {
            outputArea.setText(
                    "Total Monthly Rent: $"
                            + String.format("%.2f",
                            company.totalRent()));
        });

        clearButton.setOnAction(e -> {

            propertyNameField.clear();
            cityField.clear();
            rentField.clear();
            ownerField.clear();
            outputArea.clear();

        });

        VBox root = new VBox(10);

        root.setPadding(new Insets(15));

        root.getChildren().addAll(
                titleLabel,
                propertyNameField,
                cityField,
                rentField,
                ownerField,
                addButton,
                showButton,
                totalButton,
                clearButton,
                outputArea
        );

        Scene scene = new Scene(root, 420, 500);

        primaryStage.setTitle("Property Management");

        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
