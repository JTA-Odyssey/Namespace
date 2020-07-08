package com.jtaodyssey.namespace.ui.controllers;

import com.jfoenix.controls.JFXButton;
import com.jtaodyssey.namespace.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Home implements Initializable
{
    @FXML
    private BorderPane viewScreen;

    @FXML
    private ImageView chatMenu;

    @FXML
    private JFXButton closeApplicationButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

    }

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
        // Implement the code to terminate the program here.
    }
}
