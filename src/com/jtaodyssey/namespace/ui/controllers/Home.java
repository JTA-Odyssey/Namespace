package com.jtaodyssey.namespace.ui.controllers;

import com.jfoenix.controls.JFXButton;
import com.jtaodyssey.namespace.notification.JTANotification;
import com.jtaodyssey.namespace.notification.JTANotificationObserver;
import com.jtaodyssey.namespace.notification.ToUINotifier;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class Home implements Initializable, JTANotificationObserver
{
    private boolean currentScene = false;

    // ****************
    // * Container(s) *
    // ****************

    @FXML
    private BorderPane viewScreen;

    // ****************
    // * ImageView(s) *
    // ****************

    @FXML
    private ImageView chatMenu;

    // *************
    // * Button(s) *
    // *************

    @FXML
    private JFXButton closeApplicationButton;

    // ***************
    // * Constructor *
    // ***************

    public Home()
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

    // **************************
    // * MouseEvent Function(s) *
    // **************************

    // On "ChatMenu icon" clicked show chat menu in scene window
    @FXML
    public void onClickShowChatMenu(MouseEvent event)
    {
        if(!currentScene)
        {
            FxmlLoader object = new FxmlLoader();
            Pane view = object.getPage("ChatMenu");
            viewScreen.setCenter(view);
            currentScene = true;
        }

    }

    // On "X" clicked this function will terminate the program.
    @FXML
    public void OnCloseApplicationClicked()
    {
        ToUINotifier.getInstance().removeObserver(this);
        Platform.exit();
        System.exit(0);
    }
}
