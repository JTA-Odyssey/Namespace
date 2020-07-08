package com.jtaodyssey.namespace;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application
{
    Stage stage = new Stage();
    Parent root;
    double xOffset;
    double yOffset;
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        try
        {
            root = FXMLLoader.load(getClass().getResource("/com/jtaodyssey/namespace/ui/fxml/Login.fxml"));
            Scene scene = new Scene(root);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
            scene.setFill(Color.TRANSPARENT);
            root.setOnMousePressed(event ->
            {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            });
            root.setOnMouseDragged(event ->
            {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void setStage(Stage myStage)
    {
        myStage = stage;
    }


    public static void main(String[] args)
    {
        launch(args);
    }
}
