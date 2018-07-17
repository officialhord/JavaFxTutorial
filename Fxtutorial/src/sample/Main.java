package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
      AnchorPane root = new AnchorPane();
Scene newScene = new Scene(root, 500, 275);

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(newScene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
