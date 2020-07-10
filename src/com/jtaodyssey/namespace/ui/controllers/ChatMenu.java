package com.jtaodyssey.namespace.ui.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.net.URL;
import java.util.ResourceBundle;

public class ChatMenu implements Initializable
{
    // ****************
    // * Container(s) *
    // ****************

    @FXML
    private ScrollPane chatScrollPane;
    @FXML
    private VBox chatBox;
    @FXML
    private TextArea chatArea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        // This code segment is where messages are being sent to be displayed in the scroll pane VBox
        chatArea.setOnKeyPressed(keyEvent ->
        {
            // On "ENTER" or "RETURN" clicked the message will be sent
            if (keyEvent.getCode() == KeyCode.ENTER || keyEvent.getCode() == KeyCode.CANCEL)
            {
                // This code is ugly af but works for the time being
                String text = chatArea.getText();
                Text textMessage = new Text(text);
                textMessage.setFill(Color.WHITE);
                TextFlow tempFlow = new TextFlow();
                tempFlow.getChildren().add(textMessage);
                HBox hBox = new HBox(12);
                Label label = new Label("User: ");
                hBox.setPadding(new Insets(20,0,0,30));
                hBox.getChildren().addAll(label, tempFlow);

                if(!text.equals(""))
                {
                    chatBox.getChildren().addAll(hBox);
                }

                chatArea.setText("");
            }
        });
    }


//    @FXML
//    public void sendMessage()
//    {
//
//        String text = chatArea.getText();
//        Text textMessage = new Text(text);
//        textMessage.setFill(Color.WHITE);
//        TextFlow tempFlow = new TextFlow();
//        tempFlow.getChildren().add(textMessage);
//        HBox hBox = new HBox(12);
//        Label label = new Label("User: ");
//        hBox.setPadding(new Insets(0,0,0,30));
//        hBox.getChildren().addAll(label, tempFlow);
//
//
//
//        if(!text.equals(""))
//        {
//            chatBox.getChildren().addAll(hBox);
//        }
//
//        chatArea.setText("");
//
//
//
//
//    }

}
