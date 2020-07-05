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

public class Login implements Initializable
{
    // ***********
    // * Buttons *
    // ***********

    @FXML
    private JFXButton loginButton;
    @FXML
    private JFXButton createAccountButton;
    @FXML
    private JFXButton closeApplicationButton;

    // **************
    // * TextFields *
    // **************

    @FXML
    private JFXTextField usernameField;
    @FXML
    private JFXPasswordField passwordField;

    // **************************
    // * Initialize Function(s) *
    // **************************

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        RequiredFieldValidator validator = new RequiredFieldValidator();

        usernameField.getValidators().add(validator);
        passwordField.getValidators().add(validator);

        // Set message to be displayed if no information is entered
        // and a new entry is clicked
        validator.setMessage("* Required Information *");

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

    }

    // **************************
    // * MouseEvent Function(s) *
    // **************************

    // On "Login" clicked this function will verify login credentials and log the user in.
    @FXML
    public void OnLoginClicked(MouseEvent event)
    {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if(!username.equals("") && !password.equals(""))
        {
            // Implement code to log into the application here
        }
        else
        {
            // If a field is missing then output validation messages
        }

    }

    // On "Create Account" clicked this function will swap scenes to the "CreateAccount" scene.
    @FXML
    public void OnCreateAccountClicked(MouseEvent event)
    {
        // Implement code to swap scenes from "Login" to "CreateAccount"
    }

    // On "X" clicked this function will terminate the program.
    @FXML
    public void OnCloseApplicationClicked(MouseEvent event)
    {
        // Implement the code to terminate the program here.
    }
}
