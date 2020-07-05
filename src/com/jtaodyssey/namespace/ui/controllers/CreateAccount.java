package com.jtaodyssey.namespace.ui.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateAccount implements Initializable
{
    // ***********
    // * Buttons *
    // ***********

    @FXML
    private JFXButton createAccountButton;
    @FXML
    private JFXButton returnToLoginButton;
    @FXML
    private JFXButton closeApplicationButton;

    // **************
    // * TextFields *
    // **************

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

    // On "X" clicked this function will terminate the program.
    @FXML
    public void OnCloseApplicationClicked(MouseEvent event)
    {
        // Implement the code to terminate the program here.
    }

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
}
