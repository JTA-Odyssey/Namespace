package com.jtaodyssey.namespace.ui.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateAccount implements Initializable
{
    // *************
    // * Button(s) *
    // *************

    @FXML
    private JFXButton createAccountButton;
    @FXML
    private JFXButton returnToLoginButton;
    @FXML
    private JFXButton closeApplicationButton;

    // ****************
    // * TextField(s) *
    // ****************

    @FXML
    private JFXTextField firstNameField;
    @FXML
    private JFXTextField lastNameField;
    @FXML
    private JFXTextField usernameField;
    @FXML
    private JFXPasswordField passwordField;
    @FXML
    private JFXPasswordField confirmPasswordField;

    // ***************
    // * Variable(s) *
    // ***************

    private double xOffset;
    private double yOffset;

    // **************************
    // * Initialize Function(s) *
    // **************************

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        RequiredFieldValidator validator = new RequiredFieldValidator();

        firstNameField.getValidators().add(validator);
        lastNameField.getValidators().add(validator);
        usernameField.getValidators().add(validator);
        passwordField.getValidators().add(validator);
        confirmPasswordField.getValidators().add(validator);

        // Set message to be displayed if no information is entered
        // and a new entry is clicked
        validator.setMessage("* Required Information *");

        // Will output the validator message if no information is entered in "First Name" field
        // and a new entry is clicked
        firstNameField.focusedProperty().addListener((observableValue, aBoolean, t1) ->
        {
            if(!t1)
            {
                firstNameField.validate();
            }
        });

        // Will output the validator message if no information is entered in "Last Name" field
        // and a new entry is clicked
        lastNameField.focusedProperty().addListener((observableValue, aBoolean, t1) ->
        {
            if(!t1)
            {
                lastNameField.validate();
            }
        });

        // Will output the validator message if no information is entered in "Username" field
        // and a new entry is clicked
        usernameField.focusedProperty().addListener((observableValue, aBoolean, t1) ->
        {
            if(!t1)
            {
                usernameField.validate();
            }
        });

        // Will output the validator message if no information is entered in "Password" field
        // and a new entry is clicked
        passwordField.focusedProperty().addListener((observableValue, aBoolean, t1) ->
        {
            if(!t1)
            {
                passwordField.validate();
            }
        });

        // Will output the validator message if no information is entered in "Confirm Password" field
        // and a new entry is clicked
        confirmPasswordField.focusedProperty().addListener((observableValue, aBoolean, t1) ->
        {
            if(!t1)
            {
                confirmPasswordField.validate();
            }
        });

    }

    // **************************
    // * MouseEvent Function(s) *
    // **************************

    // On "Create Account" clicked this function will create a new account and error check for invalid credentials.
    @FXML
    public void OnCreateAccountClicked(MouseEvent event)
    {
        // Implement the code to create a new account here.
    }

    // On "Return to Login" clicked this function will return the user to the login screen.
    @FXML
    public void OnReturnToLoginClicked(MouseEvent event)
    {
        // Implement the code to return back to the login screen here.
    }

    // On "X" clicked this function will terminate the program.
    @FXML
    public void OnCloseApplicationClicked(MouseEvent event)
    {
        Platform.exit();
        System.exit(0);
    }

    // This function is used to swap scenes between the "Create Account" scene or "Home" scene
    public void swapScene(String fileName, Button button)
    {
        Parent root = null;
        try
        {
            root = FXMLLoader.load(getClass().getResource("/com/jtaodyssey/namespace/ui/fxml/" + fileName + ".fxml"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        Scene scene  = new Scene(root);
        Stage window = (Stage)(button).getScene().getWindow();

        // This event allows the user to move the window wherever if the mouse is dragged in the scene boundaries
        root.setOnMousePressed(event ->
        {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        root.setOnMouseDragged(event ->
        {
            window.setX(event.getScreenX() - xOffset);
            window.setY(event.getScreenY() - yOffset);
        });

        Platform.runLater(()->
        {
            window.setScene(scene);
            window.show();
        });
    }
}
