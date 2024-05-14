package com.example.pacman;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;
import java.sql.*;
import java.util.Arrays;
import java.util.List;

import java.sql.PreparedStatement;
public class MainMenu extends Application {

    private boolean loggedIn = false;
    private String currentUsername = "";

    @Override
    public void start(Stage primaryStage) {
        // Create main window
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 800, 600);

        // Create top menu bar
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setOnAction(e -> exitApplication(primaryStage));
        fileMenu.getItems().add(exitItem);
        menuBar.getMenus().add(fileMenu);
        root.setTop(menuBar);

        // Create main content area
        VBox mainContent = new VBox(20);
        mainContent.setAlignment(Pos.CENTER);
        root.setCenter(mainContent);

        // If no accounts created, show account creation tab
        if (!accountsExist()) {
            mainContent.getChildren().add(createAccountCreationTab());
        } else {
            // Show username at the top
            Label usernameLabel = new Label("Welcome, " + currentUsername);
            mainContent.getChildren().add(usernameLabel);

            // Add play button
            Button playButton = new Button("Play");
            playButton.setOnAction(e -> {
                // Launch Pacman game
                launchPacman(primaryStage);
            });
            mainContent.getChildren().add(playButton);

            // Add leaderboard button
            Button leaderboardButton = new Button("Leaderboard");
            leaderboardButton.setOnAction(e -> {
                // Show leaderboard
                showLeaderboard(primaryStage);
            });
            mainContent.getChildren().add(leaderboardButton);

            // Add account button
            Button accountButton = new Button("Account");
            accountButton.setOnAction(e -> {
                // Show account options
                showAccountOptions(primaryStage);
            });
            mainContent.getChildren().add(accountButton);

            // Add settings button
            Button settingsButton = new Button("Settings");
            settingsButton.setOnAction(e -> {
                // Show settings
                showSettings(primaryStage);
            });
            mainContent.getChildren().add(settingsButton);

            // Add exit button
            Button exitButton = new Button("Exit");
            exitButton.setOnAction(e -> exitApplication(primaryStage));
            mainContent.getChildren().add(exitButton);
        }

        primaryStage.setScene(scene);
        primaryStage.setTitle("Pacman Main Menu");
        primaryStage.show();
    }

    private VBox createAccountCreationTab() {
        VBox accountCreationContent = new VBox(20);
        accountCreationContent.setAlignment(Pos.CENTER);

        // Account creation form elements
        TextField usernameInput = new TextField();
        Button createAccountButton = new Button("Create Account");

        createAccountButton.setOnAction(e -> {
            String username = usernameInput.getText();
            // Create new account in database
            if (createAccount(username)) {
                // Set current username and mark as logged in
                currentUsername = username;
                loggedIn = true;
                // Reload menu to show logged-in state
                start(new Stage());
            } else {
                // Display error message
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Failed to create account.");
                alert.showAndWait();
            }
        });

        // Add elements to tab content
        accountCreationContent.getChildren().addAll(
                new Label("Enter username:"), usernameInput,
                createAccountButton
        );

        return accountCreationContent;
    }

    private void showLeaderboard(Stage primaryStage) {
        // Create leaderboard window
        Stage leaderboardStage = new Stage();
        VBox leaderboardLayout = new VBox(10);
        leaderboardLayout.setPadding(new Insets(20));
        Label titleLabel = new Label("Leaderboard");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        // Example leaderboard data
        List<String> leaderboardData = Arrays.asList(
                "1. John - 5000",
                "2. Alice - 4500",
                "3. Bob - 4000"
        );
        ListView<String> leaderboardListView = new ListView<>(FXCollections.observableArrayList(leaderboardData));

        leaderboardLayout.getChildren().addAll(titleLabel, leaderboardListView);
        Scene leaderboardScene = new Scene(leaderboardLayout, 300, 200);
        leaderboardStage.setScene(leaderboardScene);
        leaderboardStage.setTitle("Leaderboard");
        leaderboardStage.show();
    }

    private void showAccountOptions(Stage primaryStage) {
        // Create account options window
        Stage accountOptionsStage = new Stage();
        VBox accountOptionsLayout = new VBox(10);
        accountOptionsLayout.setPadding(new Insets(20));
        Label titleLabel = new Label("Account Options");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        // Add account option buttons
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> accountOptionsStage.close());

        Button createButton = new Button("Create Account");
        createButton.setOnAction(e -> {
            // Implement create account functionality
        });

        Button editButton = new Button("Edit Account");
        editButton.setOnAction(e -> {
            // Implement edit account functionality
        });

        Button deleteButton = new Button("Delete Account");
        deleteButton.setOnAction(e -> {
            // Implement delete account functionality
        });

        accountOptionsLayout.getChildren().addAll(titleLabel, backButton, createButton, editButton, deleteButton);
        Scene accountOptionsScene = new Scene(accountOptionsLayout, 300, 200);
        accountOptionsStage.setScene(accountOptionsScene);
        accountOptionsStage.setTitle("Account Options");
        accountOptionsStage.show();
    }

    private void showSettings(Stage primaryStage) {
        // Create settings window
        Stage settingsStage = new Stage();
        VBox settingsLayout = new VBox(10);
        settingsLayout.setPadding(new Insets(20));
        Label titleLabel = new Label("Settings");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        // Add settings components
        CheckBox muteCheckBox = new CheckBox("Mute");
        Slider volumeSlider = new Slider(0, 100, 50);
        Button saveButton = new Button("Save");

        settingsLayout.getChildren().addAll(titleLabel, muteCheckBox, volumeSlider, saveButton);
        Scene settingsScene = new Scene(settingsLayout, 300, 200);
        settingsStage.setScene(settingsScene);
        settingsStage.setTitle("Settings");
        settingsStage.show();
    }

    private void launchPacman(Stage primaryStage) {
        // Launch Pacman game
        Pacman pac = new Pacman();
        pac.setVisible(true);
        pac.setTitle("Pacman");
        pac.setSize(380, 420);
        pac.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pac.setLocationRelativeTo(null);
    }

    private boolean accountsExist() {
        try {
            // Establish a database connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pacmanaccounts", "username", "password");

            // Prepare a statement to query the database
            String query = "SELECT COUNT(*) FROM users";
            PreparedStatement stmt = conn.prepareStatement(query);

            // Execute the query
            ResultSet rs = stmt.executeQuery();

            // Get the count of accounts
            rs.next();
            int count = rs.getInt(1);

            // Close resources
            rs.close();
            stmt.close();
            conn.close();

            // Return true if there are accounts, false otherwise
            return count > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            // Return false in case of any error
            return false;
        }
    }

    private boolean createAccount(String username) {
        try {
            // Establish a database connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pacmanaccounts", "username", "password");

            // Prepare a statement to insert into the database
            String query = "INSERT INTO users (username) VALUES (?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);

            // Execute the update
            int rowsInserted = stmt.executeUpdate();

            // Close resources
            stmt.close();
            conn.close();

            // Return true if the account was successfully created, false otherwise
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            // Return false in case of any error
            return false;
        }
    }

    private void exitApplication(Stage primaryStage) {
        // Prompt user before exiting
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to exit?");
        ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(yesButton, noButton);

        // Fade out transition
        FadeTransition fadeOut = new FadeTransition(Duration.millis(500), primaryStage.getScene().getRoot());
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setOnFinished(e -> System.exit(0));

        // Handle user response
        alert.showAndWait().ifPresent(response -> {
            if (response == yesButton) {
                fadeOut.play();
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
