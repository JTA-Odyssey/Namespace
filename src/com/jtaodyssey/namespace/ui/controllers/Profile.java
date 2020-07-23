package com.jtaodyssey.namespace.ui.controllers;

import com.jfoenix.controls.JFXButton;
import com.jtaodyssey.namespace.components.LoggedInUser;
import com.jtaodyssey.namespace.notification.JTANotification;
import com.jtaodyssey.namespace.notification.JTANotificationObserver;
import com.jtaodyssey.namespace.notification.ToUINotifier;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Profile implements Initializable, JTANotificationObserver
{
    // ****************
    // * Container(s) *
    // ****************

    @FXML
    private GridPane displayFriendsGrid;

    // *************
    // * Button(s) *
    // *************

    @FXML
    private JFXButton editProfileButton;
    @FXML
    private JFXButton viewAllFriendsButton;

    // ************
    // * Label(s) *
    // ************

    @FXML
    private Label usernameLabel;
    @FXML
    private Label aliasLabel;
    @FXML
    private Label fullNameLabel;

    // ************
    // * Shape(s) *
    // ************

    @FXML
    private Circle profilePictureCircle;

    // ***************
    // * Constructor *
    // ***************

    public Profile()
    {
        ToUINotifier.getInstance().addObserver(this);
    }

    // **************
    // * Initialize *
    // **************

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        JTACachedUser cachedUser = LoggedInUser.getInstance().getUser();

        usernameLabel.setText(cachedUser.getUsername());
        aliasLabel.setText(cachedUser.getUser().getAlias());
        fullNameLabel.setText(cachedUser.getUser().getFirstName() + " " + cachedUser.getUser().getLastName());

        // Temporary way of displaying profile icon
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
    public void onClickEditProfile()
    {
        swapScene("Home", editProfileButton);
    }

    @FXML
    public void onClickViewAllFriends()
    {

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
            crtl.onClickShowProfileEdit();

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
