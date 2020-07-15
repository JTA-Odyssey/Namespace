package com.jtaodyssey.namespace;

import com.jtaodyssey.namespace.communication.PubNubActions;
import com.jtaodyssey.namespace.communication.PubNubReceiver;
import com.jtaodyssey.namespace.components.BasicUser;
import com.jtaodyssey.namespace.components.JTATextMessage;
import com.jtaodyssey.namespace.components.JTAUser;
import com.jtaodyssey.namespace.components.LoggedInUser;
import com.jtaodyssey.namespace.notification.JTANotificationRouter;
import com.jtaodyssey.namespace.services.JTACachedUser;
import com.jtaodyssey.namespace.services.JTAInitializerService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Main extends Application
{
    // ***************
    // * Variable(s) *
    // ***************

    private Parent root;
    private double xOffset;
    private double yOffset;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        try
        {
            root = FXMLLoader.load(getClass().getResource("/com/jtaodyssey/namespace/ui/fxml/Login.fxml"));
            Scene scene = new Scene(root);

            primaryStage.initStyle(StageStyle.TRANSPARENT);
            primaryStage.setScene(scene);
            primaryStage.show();
            scene.setFill(Color.TRANSPARENT);

            root.setOnMousePressed(event ->
            {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            });
            root.setOnMouseDragged(event ->
            {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        PubNubActions.getInstance().subscribe(Arrays.asList("A"));
        PubNubReceiver.getInstance().listen();
        JTANotificationRouter.getInstance().init();
//
//        JTAUser user = new BasicUser("Tucker", "Harvey", "Raft", "tharvey556");
//        user.setId(UUID.randomUUID().toString());
//        JTAInitializerService.getInstance().init(user);
//        JTACachedUser cached = LoggedInUser.getInstance().getUser();
//        List<JTATextMessage> list = cached.getMessages("A");
//        for (JTATextMessage m : list) {
//            System.out.println(m);
//        }
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
