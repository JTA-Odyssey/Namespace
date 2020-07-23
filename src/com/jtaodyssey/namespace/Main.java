package com.jtaodyssey.namespace;

import com.jtaodyssey.namespace.services.JTAInitializerService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
        JTAInitializerService.getInstance().prepare();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}

//todo add the following lines to the initializer
//        PubNubActions.getInstance().subscribe(Arrays.asList("A"));
//        PubNubReceiver.getInstance().listen();
//
//        JTAUser user = new BasicUser("Tucker", "Harvey", "Raft", "tharvey556");
//        user.setId(UUID.randomUUID().toString());
//        JTAInitializerService.getInstance().init(user);
//        JTACachedUser cached = LoggedInUser.getInstance().getUser();
//        List<JTATextMessage> list = cached.getMessages("A");
//        for (JTATextMessage m : list) {
//            System.out.println(m);
//        }