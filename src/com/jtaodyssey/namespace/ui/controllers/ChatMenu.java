package com.jtaodyssey.namespace.ui.controllers;

import com.jfoenix.controls.JFXButton;
import com.jtaodyssey.namespace.components.JTATextMessage;
import com.jtaodyssey.namespace.components.LoggedInUser;
import com.jtaodyssey.namespace.notification.*;
import com.jtaodyssey.namespace.services.JTACachedUser;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ChatMenu implements Initializable, JTANotificationObserver
{
    private String currentChannelID = "";

    // *************
    // * Button(s) *
    // *************

    @FXML
    private JFXButton createNewMessageButton;

    // ****************
    // * Container(s) *
    // ****************

    @FXML
    private ScrollPane chatScrollPane;

    @FXML
    private VBox chatBox;

    @FXML
    private ScrollPane channelScrollPane;

    @FXML
    private VBox channelBox;


    // ****************
    // * Text Area(s) *
    // ****************

    @FXML
    private TextArea chatArea;

    @FXML
    private TextField channelNameField;

    // ***************
    // * Constructor *
    // ***************

    public ChatMenu()
    {
        ToUINotifier.getInstance().addObserver(this);
    }

    // **************
    // * Initialize *
    // **************

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        channelScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        chatArea.setOnKeyPressed(keyEvent ->
        {
            if(keyEvent.getCode() == KeyCode.ENTER || keyEvent.getCode() == KeyCode.CANCEL)
            {
                String text = chatArea.getText().trim();

                if(!text.equals("") && !currentChannelID.equals(""))
                {
                    JTACachedUser cachedUser = LoggedInUser.getInstance().getUser();
                    JTANotification notification = new OutgoingMessageNotification(new JTATextMessage(text, cachedUser.getUser()), currentChannelID);
                    FromUINotifier.getInstance().notify(notification);
                }

                chatArea.setText("");
            }
        });


    }

    // ***********************
    // * Notification Update *
    // ***********************

    @Override
    public void update(JTANotification notification)
    {
        if(notification instanceof IncomingMessageNotification)
        {
            String channel = ((IncomingMessageNotification) notification).getChannel();
            JTATextMessage message = (JTATextMessage) notification.readPayload();

            System.out.println(message);

            Platform.runLater(()-> formatMessage(message));
        }
    }

    // ************************
    // * ChatMenu Function(s) *
    // ************************

    // TO-DO - Add username/User to format message to output the username of the person who sent the message
    public void formatMessage(JTATextMessage text)
    {
        // Creating & Formatting Text
        Text textMessage = new Text(text.getMessage());
        textMessage.setFill(Color.WHITE);
        textMessage.getStyleClass().add("textMessage");

        // Creating & Formatting temporary TextFlow
        TextFlow tempFlow = new TextFlow();
        // Add username to TextFlow here
//        if(!this.username.equals(username))
//        {
//            Text txtName = new Text(username + "\n");
//            txtName.getStyleClass().add("txtName");
//            tempFlow.getChildren().add(txtName);
//        }

        tempFlow.getChildren().add(textMessage);
        tempFlow.setMaxWidth(200);

        // Creating & Formatting primary TextFlow
        TextFlow flow = new TextFlow(tempFlow);

        // Creating HBox (text message box)
        HBox textMessageBox = new HBox(12);

        // Creating Profile Picture Icon
        Circle img = new Circle(32, 32, 16);

        try
        {
            String path = new File(String.format("/Users/jeffreyadams/Desktop/Namespace/src/com/jtaodyssey/namespace/ui/images/testProfilePicture.jpeg")).toURI().toString();
            img.setFill(new ImagePattern(new Image(path)));
        }
        catch (Exception e)
        {
            String path = new File("com/jtaodyssey/namespace/ui/images/test3.png").toURI().toString();
            img.setFill(new ImagePattern(new Image(path)));

            e.printStackTrace();
        }

        // Formatting ImageView
        img.getStyleClass().add("imageView");

//        if(!this.username.equals(username))
//        {
//            tempFlow.getStyleClass().add("tempFlowReceiver");
//            flow.getStyleClass().add("textFlowReceiver");
//            chatBox.setAlignment(Pos.TOP_LEFT);
//            textMessageBox.setAlignment(Pos.CENTER_LEFT);
//            textMessageBox.getChildren().add(img);
//            textMessageBox.getChildren().add(flow);
//        }
//        else
//        {
//            tempFlow.getStyleClass().add("tempFlowSender");
//            flow.getStyleClass().add("textFlowSender");
//            textMessageBox.setAlignment(Pos.BOTTOM_RIGHT);
//            textMessageBox.getChildren().add(flow);
//            textMessageBox.getChildren().add(img);
//        }

        JTACachedUser cachedUser = LoggedInUser.getInstance().getUser();
        String myID = cachedUser.getUser().getId();



        if(text.getUserID() == null || !text.getUserID().equals(myID))
        {
            tempFlow.getStyleClass().add("tempFlowReceiver");
            flow.getStyleClass().add("textFlowReceiver");
            chatBox.setAlignment(Pos.TOP_LEFT);
            textMessageBox.setAlignment(Pos.CENTER_LEFT);
            textMessageBox.getChildren().add(img);
            textMessageBox.getChildren().add(flow);
        }
        else
        {
            tempFlow.getStyleClass().add("tempFlowSender");
            flow.getStyleClass().add("textFlowSender");
            textMessageBox.setAlignment(Pos.BOTTOM_RIGHT);
            textMessageBox.getChildren().add(flow);
            textMessageBox.getChildren().add(img);
        }


        textMessageBox.getStyleClass().add("textMessageBox");

        chatBox.getChildren().addAll(textMessageBox);
    }

    @FXML
    public void createNewMessageOnClick()
    {

        String channel = channelNameField.getText();

        if (!channel.equals("") && !channel.equals(currentChannelID))
        {
            Label channelName = new Label(channel);
            channelName.setMinHeight(36);
            channelName.setMinWidth(130);
            channelName.setPadding(new Insets(0, 0, 0, 5));
            channelName.getStyleClass().add("channelLabel");


            // Creating Profile Picture Icon
            Circle img = new Circle(30, 30, 15);

            HBox channelHBox = new HBox();
            channelHBox.setAlignment(Pos.CENTER_LEFT);

            try
            {
                String path = new File(String.format("/Users/jeffreyadams/Desktop/Namespace/src/com/jtaodyssey/namespace/ui/images/testProfilePicture.jpeg")).toURI().toString();
                img.setFill(new ImagePattern(new Image(path)));
            }
            catch (Exception e)
            {
                String path = new File("Namespace/src/com/jtaodyssey/namespace/ui/images/test3.png").toURI().toString();
                img.setFill(new ImagePattern(new Image(path)));
            }
            channelHBox.setOnMousePressed(event ->
            {
                handleChannelEvent(event);
            });
            channelHBox.getChildren().add(img);
            channelHBox.getChildren().add(channelName);
            channelBox.getChildren().add(channelHBox);

            currentChannelID = channel;
            loadMessages();
        }
        channelNameField.setText("");
    }

    private void handleChannelEvent(MouseEvent mouseEvent)
    {
        HBox box = (HBox) mouseEvent.getSource();

        String channel = ((Label) box.getChildren().get(1)).getText();

        if(!currentChannelID.equals(channel))
        {
            currentChannelID = channel;
            loadMessages();
        }


    }

    private void loadMessages()
    {
        chatBox.getChildren().clear();

        JTACachedUser cachedUser = LoggedInUser.getInstance().getUser();
        String channel = currentChannelID;
        List<JTATextMessage> messageList = cachedUser.getMessages(channel);

        for(JTATextMessage m : messageList)
        {
            formatMessage(m);
        }
    }

}
