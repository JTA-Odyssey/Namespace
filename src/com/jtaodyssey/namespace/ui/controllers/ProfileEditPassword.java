package com.jtaodyssey.namespace.ui.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jtaodyssey.namespace.components.AuthStatus;
import com.jtaodyssey.namespace.components.BasicRegistration;
import com.jtaodyssey.namespace.components.LoggedInUser;
import com.jtaodyssey.namespace.notification.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileEditPassword implements Initializable, JTANotificationObserver
{
    // ************
    // * Label(s) *
    // ************

    @FXML
    private Label usernameLabel;

    // *****************
    // * Text Field(s) *
    // *****************

    @FXML
    private JFXTextField oldPasswordField;
    @FXML
    private JFXTextField newPasswordField;
    @FXML
    private JFXTextField confirmPasswordField;

    // *************
    // * Button(s) *
    // *************
    @FXML
    private JFXButton savePasswordButton;
    @FXML
    private JFXButton returnToProfileButton;


    // ***************
    // * Constructor *
    // ***************

    public ProfileEditPassword()
    {
        ToUINotifier.getInstance().addObserver(this);
    }

    // **************
    // * Initialize *
    // **************

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

    }

    // ***********************
    // * Notification Update *
    // ***********************

    @Override
    public void update(JTANotification notification)
    {
        if (notification instanceof AuthStatusNotification) {
            AuthStatusNotification auth = (AuthStatusNotification) notification;
            validatePasswordSaved((AuthStatus)auth.readPayload());
        }
    }

    private void validatePasswordSaved(AuthStatus status) {
        if (status.getStatusType().equals("update"))
        {
            if (status.isValidated())
            {
                swapScene("Home", savePasswordButton);
            }
            else
            {
                // notify that there was an error saving the password
                // THIS IS NOT A RESULT OF PASSWORD NOT BEING DIFFERENT
                // SINCE OUT LOGIC ISNT CHECKING THAT. could change later
            }
        }
    }


    // ***************
    // * Function(s) *
    // ***************

    @FXML
    public void onClickSavePassword()
    {
        String password        = oldPasswordField.getText();
        String newPassword     = newPasswordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if(!password.equals("") && !newPassword.equals("") && !confirmPassword.equals(""))
        {
            String userPassword = LoggedInUser.getInstance().getUser().getPassword();

            if(password.equals(userPassword) && newPassword.equals(confirmPassword))
            {
                String id = LoggedInUser.getInstance().getUser().getUser().getId();
                String firstName = LoggedInUser.getInstance().getUser().getUser().getFirstName();
                String lastName = LoggedInUser.getInstance().getUser().getUser().getLastName();
                String username = LoggedInUser.getInstance().getUser().getUsername();

                FromUINotifier.getInstance().notify(new UpdateUserNotification(
                        new BasicRegistration(firstName, lastName, username, newPassword, id)));
            }
            else
            {
                // error check label show
            }
        }
    }

    @FXML
    public void onClickReturnToProfile()
    {
        swapScene("Home", returnToProfileButton);
    }

    // This function is used to swap scenes
    public void swapScene(String fileName, Button button)
    {
        Parent root = null;
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/jtaodyssey/namespace/ui/fxml/" + fileName + ".fxml"));
            root = loader.load();
            Scene scene  = new Scene(root);
            Stage window = (Stage)(button).getScene().getWindow();

            Home crtl = loader.getController();
            crtl.onClickShowProfile();

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
