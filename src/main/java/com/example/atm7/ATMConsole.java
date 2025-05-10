package com.example.atm7;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

        public class ATMConsole extends Application {
            private String userName = "Shanthi";
            private String accountNumber = "629473058";
            private String correctPin = "1234";
            private double balance = 1000.0;

            private Stage stage;
            private Scene loginScene, mainScene, historyScene;

            public static void main(String[] args) {
                launch(args);
            }

            @Override
            public void start(Stage primaryStage) {
                stage = primaryStage;
                createLoginScene();
                createMainScene();

                stage.setScene(loginScene);
                stage.setTitle("ATM Console App");
                stage.show();
            }

            private void createLoginScene() {
                Label title = new Label("AUTOMATED TELLER MACHINE (ATM)");
                title.setFont(Font.font(30));
        GridPane.setConstraints(title, 0, 0, 2, 1);
        Label nameLabel = new Label("Username: " + userName);
        Label accountLabel = new Label("Account Number: " + accountNumber);
        PasswordField pinField = new PasswordField();
        pinField.setPromptText("Enter PIN");
        Button loginButton = createButton("Login",Color.SKYBLUE);

        loginButton.setOnAction(e -> {
            if (pinField.getText().equals(correctPin)) {
                stage.setScene(mainScene);
            } else {
                showAlert("Invalid PIN");
            }
        });

        VBox loginLayout = createVBoxWithSpacing(10);
        loginLayout.getChildren().addAll(title,nameLabel, accountLabel, pinField, loginButton);
        loginScene = new Scene(loginLayout, 600, 500);
    }

    private void createMainScene() {
        Label balanceLabel = new Label("Current Balance: $" + balance);
        balanceLabel.setFont(Font.font(24));
        Button depositButton = createButton("Deposit",Color.GREEN);
        Button withdrawButton = createButton("Withdraw",Color.RED);
        Button exitButton = createButton("Exit",Color.GREY);

        depositButton.setOnAction(e -> {
            double amount = promptForAmount("Enter deposit amount:");
            balance += amount;
            showSuccessMessage("Successfully deposited $" + amount);
            balanceLabel.setText("Current Balance: $" + balance);
        });

        withdrawButton.setOnAction(e -> {
            double amount = promptForAmount("Enter withdrawal amount:");
            if (amount > balance) {
                showAlert("Insufficient funds");
            } else {
                balance -= amount;
                showSuccessMessage("Successfully withdrew $" + amount);
                balanceLabel.setText("Current Balance: $" + balance);
            }
        });

        exitButton.setOnAction(e -> {
            showSuccessMessage("Thank you for using the ATM. Goodbye!");
            stage.close();
        });

        VBox mainLayout = createVBoxWithSpacing(10);
        mainLayout.getChildren().addAll(balanceLabel, depositButton, withdrawButton, exitButton);
        mainScene = new Scene(mainLayout, 600, 500);
    }

    private double promptForAmount(String message) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText(message);
        dialog.setContentText("Amount:");
        dialog.showAndWait();
        return Double.parseDouble(dialog.getResult());
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showSuccessMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    private Button createButton(String text, Color color) {
        Button button = new Button(text);
        button.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
        return button;
    }

    private VBox createVBoxWithSpacing(double spacing) {
        VBox vBox = new VBox(spacing);
        vBox.setPadding(new Insets(10));
        return vBox;
    }
}

