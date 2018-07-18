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
Text text1 = new Text(150,150,"this is the first text");
Font font = new Font("serif", 20);
text1.setFont(font);

DropShadow shadow = new DropShadow();
shadow.setRadius(10);
shadow.setOffsetX(10);
shadow.setOffsetY(10);
shadow.setColor(Color.BLUE);
text1.setEffect(shadow);

Text text2 = new Text(200, 200, "This is the second text");
text2.setFont(font);

        Reflection reflect = new Reflection();
        reflect.setTopOffset(10);
        reflect.setFraction(6);
        text2.setEffect(reflect);
root.getChildren().addAll(text1, text2);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(newScene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
