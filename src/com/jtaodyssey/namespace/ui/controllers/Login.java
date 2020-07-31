package com.jtaodyssey.namespace.ui.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import com.jtaodyssey.namespace.Main;
import com.jtaodyssey.namespace.components.AuthStatus;
import com.jtaodyssey.namespace.components.JTALogin;
import com.jtaodyssey.namespace.notification.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Login implements Initializable, JTANotificationObserver
{
    // *************
    // * Button(s) *
    // *************

    @FXML
    private JFXButton loginButton;
    @FXML
    private JFXButton createAccountButton;
    @FXML
    private JFXButton closeApplicationButton;

    // ****************
    // * TextField(s) *
    // ****************

    @FXML
    private JFXTextField usernameField;
    @FXML
    private JFXPasswordField passwordField;

    // ************
    // * Label(s) *
    // ************

    @FXML
    private Label errorCheckLabel;

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

    public Login()
    {
        ToUINotifier.getInstance().addObserver(this);
    }

    // **************
    // * Initialize *
    // **************

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        loginButton.getStyleClass().add("buttonGradients");
        createAccountButton.getStyleClass().add("buttonGradients");

        String profilePicture = new File(String.format("images/LoginLogo.jpeg")).toURI().toString();
        logoRectangle.setFill(new ImagePattern(new Image(profilePicture)));
        logoRectangle.setEffect(new DropShadow(+25d, 0d, +2d, Color.BLACK));

        usernameField.setOnKeyPressed(keyEvent ->
        {
            if(keyEvent.getCode() == KeyCode.TAB)
            {
                passwordField.isFocused();
                passwordField.setOnKeyPressed(keyEvent1 ->
                {
                    if(keyEvent1.getCode() == KeyCode.ENTER || keyEvent1.getCode() == KeyCode.CANCEL)
                    {
                        OnLoginClicked();
                    }
                });
            }
        });

        errorCheckLabel.setVisible(false);

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
        if(status.getStatusType().equals("login"))
        {
            if(status.isValidated())
            {
                swapScene("Home", loginButton);
            }
            else
            {
                errorCheckLabel.setVisible(true);
                passwordField.clear();
            }
        }
    }

    // ***************
    // * Function(s) *
    // ***************

    // On "Login" clicked this function will verify login credentials and log the user in.
    @FXML
    public void OnLoginClicked()
    {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if(!username.equals("") && !password.equals(""))
        {
            FromUINotifier.getInstance().notify(new AuthNotification(new JTALogin(username, password)));
        }
    }

    // On "Create Account" clicked this function will swap scenes to the "CreateAccount" scene.
    @FXML
    public void OnCreateAccountClicked()
    {
        swapScene("CreateAccount", createAccountButton);
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/jtaodyssey/namespace/ui/fxml/" + fileName + ".fxml"));
            root = loader.load();
            Scene scene  = new Scene(root);
            Stage window = (Stage)(button).getScene().getWindow();
            scene.setFill(Color.TRANSPARENT);

            if(button.equals(loginButton))
            {
            Home crtl = loader.getController();
            crtl.onClickShowChatMenu();
            }

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
                ToUINotifier.getInstance().removeObserver(this);
                window.setScene(scene);
                window.show();
            });
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
