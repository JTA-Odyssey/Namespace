package com.jtaodyssey.namespace.ui.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jtaodyssey.namespace.components.BasicRegistration;
import com.jtaodyssey.namespace.components.LoggedInUser;
import com.jtaodyssey.namespace.notification.*;
import com.jtaodyssey.namespace.services.JTACachedUser;
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
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileEdit implements Initializable, JTANotificationObserver
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
    private JFXTextField aliasField;
    @FXML
    private JFXTextField firstNameField;
    @FXML
    private JFXTextField lastNameField;

    // *************
    // * Button(s) *
    // *************

    @FXML
    private JFXButton changeProfilePictureButton;
    @FXML
    private JFXButton changeUsernameButton;
    @FXML
    private JFXButton changePasswordButton;
    @FXML
    private JFXButton returnToProfileButton;
    @FXML
    private JFXButton saveChangesButton;

    // ************
    // * Shape(s) *
    // ************

    @FXML
    private Circle profilePictureCircle;

    // ***************
    // * Constructor *
    // ***************


    // **************
    // * Initialize *
    // **************

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        JTACachedUser cachedUser = LoggedInUser.getInstance().getUser();

        usernameLabel.setText(cachedUser.getUsername());
        aliasField.setText(cachedUser.getUser().getAlias());
        firstNameField.setText(cachedUser.getUser().getFirstName());
        lastNameField.setText(cachedUser.getUser().getLastName());


        String profilePicture = new File(String.format("images/default-1.png")).toURI().toString();
        profilePictureCircle.setFill(new ImagePattern(new Image(profilePicture)));
        profilePictureCircle.setEffect(new DropShadow(+25d, 0d, +2d, Color.BLACK));
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
    public void onClickChangeProfilePicture()
    {

    }

    @FXML
    public void onClickChangeUsername()
    {
        swapScene("Home", changeUsernameButton);
    }

    @FXML
    public void onClickChangePassword()
    {
        swapScene("Home", changePasswordButton);
    }

    @FXML
    public void onClickReturnToProfile()
    {
        swapScene("Home", returnToProfileButton);
    }

    @FXML
    public void onClickSaveChanges()
    {
        String firstName = firstNameField.getText();
        String lastName  = lastNameField.getText();
        String alias     = aliasField.getText();

        if(!firstName.equals("") && lastName.equals(""))
        {
            String id       = LoggedInUser.getInstance().getUser().getUser().getId();
            String username = LoggedInUser.getInstance().getUser().getUsername();
            String password = LoggedInUser.getInstance().getUser().getPassword();

            // TO - FIX - Add alias to reg
            FromUINotifier.getInstance().notify(
                    new UpdateUserNotification(new BasicRegistration(firstName, lastName, username, password, id)));

            swapScene("Home", saveChangesButton);
        }

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
            Home controller = loader.getController();

            if(button.equals(changeUsernameButton))
            {
                controller.onClickShowProfileEditUsername();
            }
            else if(button.equals(changePasswordButton))
            {
                controller.onClickShowProfileEditPassword();
            }
            else
            {
                controller.onClickShowProfile();
            }

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
