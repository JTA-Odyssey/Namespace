package com.jtaodyssey.namespace.ui.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class Home implements Initializable
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

    // **************************
    // * Initialize Function(s) *
    // **************************

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

    }

    // **************************
    // * MouseEvent Function(s) *
    // **************************

    // On "ChatMenu icon" clicked show chat menu in scene window
    @FXML
    public void onClickShowChatMenu(MouseEvent event)
    {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("ChatMenu");
        viewScreen.setCenter(view);
    }

    // On "X" clicked this function will terminate the program.
    @FXML
    public void OnCloseApplicationClicked(MouseEvent event)
    {
        Platform.exit();
        System.exit(0);    }
}
