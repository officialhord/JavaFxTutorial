package sample;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.sql.*;

public class Main extends Application {
private static String user = "Username";
    private static String pass = "Password";
    private final static IntegerProperty loginattempts = new SimpleIntegerProperty();
    private int maxattempts = 5;


Connection connect = null;
PreparedStatement pst = null;
ResultSet rst =null;


    public static Connection databaseConnection(){
        try{
          Class.forName("org.sqlite.JDBC");
          Connection connect = DriverManager.getConnection("jdbc:sqlite:UserDetails.db");
          System.out.print("Database Connected");
return connect;


        }catch(Exception e){

            e.printStackTrace();
            return null;
        }
    }

    private void timer(){

        long start = System.currentTimeMillis();
       long end = start + maxattempts *1000;
        while(System.currentTimeMillis()<end){

        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        VBox root = new VBox();
        root.setPadding(new Insets(10));
        root.setAlignment(Pos.CENTER);
connect = databaseConnection();

        TextField usernamefield = new TextField();
        PasswordField passwordField = new PasswordField();
        Button loginbtn = new Button("Login/Sign IN");
        Label outputlabel = new Label("This displays the output");

        loginbtn.setOnAction(e ->{

            String username = usernamefield.getText();
            String password = passwordField.getText();

try{

    String sql = "Select * from Login where Username = ? and Password =?";
    pst = connect.prepareStatement(sql);
    pst.setString(1, username);
    pst.setString(2, password);
    rst = pst.executeQuery();
    if(rst.next()){

        outputlabel.setText("User details are correct");
    }
    else{

        outputlabel.setText("User details are incorrect");
        loginattempts.set(loginattempts.add(1).get());
        if(loginattempts.get()>2){

            usernamefield.setEditable(false);
            passwordField.setEditable(false);

            timer();
            outputlabel.setText("Please try again");
            usernamefield.setEditable(true);
            passwordField.setEditable(true);


        }
    }
}catch(SQLException ex){
    ex.printStackTrace();

}



        });


        root.getChildren().addAll(usernamefield,passwordField,loginbtn,outputlabel);
        primaryStage.setTitle("Login App");
        primaryStage.setScene(new Scene(root, 300, 150));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
