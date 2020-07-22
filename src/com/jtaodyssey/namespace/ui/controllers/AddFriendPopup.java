package com.jtaodyssey.namespace.ui.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jtaodyssey.namespace.components.JTAContact;
import com.jtaodyssey.namespace.notification.AddContactNotification;
import com.jtaodyssey.namespace.notification.FromUINotifier;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

public class AddFriendPopup implements Initializable
{
    // ************
    // * Label(s) *
    // ************

    @FXML
    private Label errorCheckLabel;

    // *************
    // * Button(s) *
    // *************

    @FXML
    private JFXButton addFriend;

    // *****************
    // * Text Field(s) *
    // *****************

    @FXML
    private JFXTextField friendUsernameField;
    @FXML
    private JFXTextField idField;

    // ***************
    // * Constructor *
    // ***************


    // **************
    // * Initialize *
    // **************

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        errorCheckLabel.setVisible(false);
    }

    // ***********************
    // * Notification Update *
    // ***********************


    // ***************
    // * Function(s) *
    // ***************

    private boolean isUUID(String id)
    {
        try
        {
            UUID uuid = UUID.fromString(id);
        }
        catch (IllegalArgumentException exception)
        {
            return false;
        }
        return true;
    }

    public void onClickCloseFriendMenu()
    {
        String username = friendUsernameField.getText();
        String id = idField.getText();

        if(!username.equals("") && isUUID(id))
        {
            Stage stage;

            FromUINotifier.getInstance().notify(new AddContactNotification(new JTAContact("", "", "", username, id, "")));

            stage = (Stage) addFriend.getScene().getWindow();
            stage.close();
        }

        friendUsernameField.setText("");
        errorCheckLabel.setVisible(true);
    }


}
