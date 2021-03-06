package com.jtaodyssey.namespace.ui.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import com.jtaodyssey.namespace.components.AuthStatus;
import com.jtaodyssey.namespace.components.BasicRegistration;
import com.jtaodyssey.namespace.components.JTARegistration;
import com.jtaodyssey.namespace.notification.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateAccount implements Initializable, JTANotificationObserver
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
    @FXML
    private Rectangle logoRectangle;

    // ***************
    // * Constructor *
    // ***************

    public CreateAccount()
    {
        ToUINotifier.getInstance().addObserver(this);
    }

    // **************
    // * Initialize *
    // **************

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        String profilePicture = new File(String.format("images/LoginLogo.jpeg")).toURI().toString();
        logoRectangle.setFill(new ImagePattern(new Image(profilePicture)));
        logoRectangle.setEffect(new DropShadow(+25d, 0d, +2d, Color.BLACK));

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

    // ***********************
    // * Notification Update *
    // ***********************

    @Override
    public void update(JTANotification notification)
    {
        if(notification instanceof AuthStatusNotification)
        {
            AuthStatusNotification auth = (AuthStatusNotification) notification;
            handleStatus((AuthStatus)auth.readPayload());
        }
    }

    private void handleStatus(AuthStatus status)
    {
        if(status.getStatusType().equals("registration"))
        {
            if(status.isValidated())
            {
                swapScene("Login", createAccountButton);
            }
            else
            {
                // output error message here
            }
        }
    }

    // **************************
    // * MouseEvent Function(s) *
    // **************************

    // On "Create Account" clicked this function will create a new account and error check for invalid credentials.
    @FXML
    public void OnCreateAccountClicked()
    {
        String firstName       = firstNameField.getText();
        String lastName        = lastNameField.getText();
        String username        = usernameField.getText();
        String password        = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if(!firstName.equals("") && !lastName.equals("") && !username.equals("")
                && !password.equals("") && !confirmPassword.equals(""))
        {
            if(password.equals(confirmPassword))
            {
                BasicRegistration registration = new BasicRegistration(firstName, lastName, username, password);
                FromUINotifier.getInstance().notify(new RegistrationNotification(registration));
            }
        }
    }

    // On "Return to Login" clicked this function will return the user to the login screen.
    @FXML
    public void OnReturnToLoginClicked()
    {
        swapScene("Login", returnToLoginButton);
    }

    // On "X" clicked this function will terminate the program.
    @FXML
    public void OnCloseApplicationClicked()
    {
        FromUINotifier.getInstance().notify(new ExitNotification());
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
        scene.setFill(Color.TRANSPARENT);

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
