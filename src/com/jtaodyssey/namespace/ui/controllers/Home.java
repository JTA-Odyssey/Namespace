package com.jtaodyssey.namespace.ui.controllers;

import com.jfoenix.controls.JFXButton;
import com.jtaodyssey.namespace.notification.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class Home implements Initializable, JTANotificationObserver
{
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
    // * Variable(s) *
    // ***************

    private String currentScene = "";

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
    public void onClickShowChatMenu()
    {
        if(!currentScene.equals("ChatMenu"))
        {
            FxmlLoader object = new FxmlLoader();
            Pane view = object.getPage("ChatMenu");
            viewScreen.setCenter(view);
            currentScene = "ChatMenu";
        }
    }

    // On "Profile icon" clicked show profile in scene window
    @FXML
    public void onClickShowProfile()
    {
        if(!currentScene.equals("Profile"))
        {
            FxmlLoader object = new FxmlLoader();
            Pane view = object.getPage("Profile");
            viewScreen.setCenter(view);
            currentScene = "Profile";
        }
    }

    @FXML
    public void onClickShowProfileEdit()
    {
        if(!currentScene.equals("ProfileEdit"))
        {
            FxmlLoader object = new FxmlLoader();
            Pane view = object.getPage("ProfileEdit");
            viewScreen.setCenter(view);
            currentScene = "ProfileEdit";
        }
    }

    @FXML
    public void onClickShowProfileEditUsername()
    {
        if(!currentScene.equals("ProfileEditUsername"))
        {
            FxmlLoader object = new FxmlLoader();
            Pane view = object.getPage("ProfileEditUsername");
            viewScreen.setCenter(view);
            currentScene = "ProfileEditUsername";
        }
    }

    @FXML
    public void onClickShowProfileEditPassword()
    {
        if(!currentScene.equals("ProfileEditPassword"))
        {
            FxmlLoader object = new FxmlLoader();
            Pane view = object.getPage("ProfileEditPassword");
            viewScreen.setCenter(view);
            currentScene = "ProfileEditPassword";
        }
    }

    // On "X" clicked this function will terminate the program.
    @FXML
    public void OnCloseApplicationClicked()
    {
        ToUINotifier.getInstance().removeObserver(this);
        FromUINotifier.getInstance().notify(new ExitNotification());
        Platform.exit();
        System.exit(0);
    }

    public BorderPane getViewScreen()
    {
        return viewScreen;
    }
}
