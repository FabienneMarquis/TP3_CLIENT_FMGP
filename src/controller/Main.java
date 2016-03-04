package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


/**
 * Created by 1494778 on 2016-02-22.
 */
public class Main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/vue/VueMenu.fxml"));
        primaryStage.setTitle("TP3 - Fabienne Marquis et Gabriel Pariat");
        Image icone = new Image("/ressource/snowflake.png");
        primaryStage.getIcons().add(icone);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
