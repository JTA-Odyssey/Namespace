package com.jtaodyssey.namespace.ui.controllers;

import com.jtaodyssey.namespace.Main;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class FxmlLoader implements Initializable
{
    private Pane view;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

    }

    public Pane getPage(String fileName)
    {
        try
        {
            URL fileUrl = Main.class.getResource("/com/jtaodyssey/namespace/ui/fxml/" + fileName + ".fxml");
            if(fileUrl == null)
            {
                throw new java.io.FileNotFoundException("FXML file can't be found");
            }

            view = new FXMLLoader().load(fileUrl);
        }
        catch(Exception e)
        {
            System.out.println("No page " + fileName + " please check FxmlLoader.");
        }

        return view;
    }
}
