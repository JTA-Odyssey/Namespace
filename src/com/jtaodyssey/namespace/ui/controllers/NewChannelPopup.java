package com.jtaodyssey.namespace.ui.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class NewChannelPopup implements Initializable
{
    @FXML
    private VBox friendBox;

    @FXML
    private JFXButton createNewMessageButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

    }

//    public void onClickCloseChannelMenu()
//    {
//        String friendUsername = friendUsernameField.getText();
//        String id = idField.getText();
//
//        if(!friendUsername.equals("") && isUUID(id))
//        {
//            if(friendUsername.equals("This is where i will check list of reg users"))
//            {
//                Stage stage;
//
//
//                stage = (Stage) addFriend.getScene().getWindow();
//                stage.close();
//            }
//            else
//            {
//                friendUsernameField.setText("");
//                errorCheckLabel.setVisible(true);
//            }
//        }
//    }
}
