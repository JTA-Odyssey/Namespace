package com.jtaodyssey.namespace.ui.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jtaodyssey.namespace.notification.JTANotification;
import com.jtaodyssey.namespace.notification.JTANotificationObserver;
import com.jtaodyssey.namespace.notification.ToUINotifier;
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
            swapScene("Home", savePasswordButton);
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
