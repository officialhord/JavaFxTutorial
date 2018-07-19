package sample;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        BorderPane root = new BorderPane();
Text newText = new Text("Top Text");
root.setTop(newText);



        Text lefttext = new Text("Left Text");
        root.setLeft(lefttext);

        VBox righttext = new VBox(10);
        righttext.setPadding(new Insets(10));
        Rectangle rect = new Rectangle(20,20);
        righttext.getChildren().add(rect);

        Rectangle rect1 = new Rectangle(30,20);
        righttext.getChildren().add(rect1);
        root.setRight(righttext);

HBox bottom = new HBox(10);
bottom.setPadding(new Insets(10,4,20,10));
        Rectangle rectangle = new Rectangle(20,20);
        bottom.getChildren().add(rectangle);

        Rectangle rectangle1 = new Rectangle(30,20);
        bottom.getChildren().add(rectangle1);
root.setBottom(bottom);


GridPane grid = new GridPane();
grid.setPadding(new Insets(10));//the spacing between the elements and the border of the gridpane

grid.setHgap(10); //The spacing between the columns

grid.setVgap(10);// The spacing between the rows

        ColumnConstraints column1 = new ColumnConstraints(100);
        ColumnConstraints column2 = new ColumnConstraints(100, 200, 300);
column2.setHgrow(Priority.ALWAYS);

grid.getColumnConstraints().addAll(column1,column2);
GridPane.setHalignment(rect1, HPos.RIGHT);
grid.add(rect1,1,0);

root.setCenter(grid);

        Scene newScene =new Scene(root, 500, 300, Color.WHITE);




        primaryStage.setTitle("Hello World");
        primaryStage.setScene(newScene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
