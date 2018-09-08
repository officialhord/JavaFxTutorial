package sample;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.*;


public class Controller {


    public static Connection databaseConnection(){
        try{
            Class.forName("org.sqlite.JDBC"); //Here we specify the type of connection we want, this tells the system we
            // want to connect to a SQLite database, would be different if we were conecting to a mysql or oracle database system.

            Connection connect = DriverManager.getConnection("jdbc:sqlite:UserDetails.db"); // here we drive the connection to the database file,
            // to reach a file outside the project source files, the path of the file has to be included with the name.
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
            usernamefield.setEditable(false);
            passwordfield.setEditable(false);
        }
    }


    @FXML
    private Button loginbtn;


    @FXML
    private TextField usernamefield;


    @FXML
    private PasswordField passwordfield;


    Connection connect = null;
    PreparedStatement pst = null;
    ResultSet rst =null;
    private final static IntegerProperty loginattempts = new SimpleIntegerProperty();
    private int maxattempts = 5;

    @FXML
    private Label outputlabel;

    String username = "Official-Hord";
    String password = "Official-Hord";

    @FXML
    public void LoginbtnAct(ActionEvent event){

        connect = databaseConnection();
    String user = usernamefield.getText();
    String pass = passwordfield.getText();

        try{

            String sql = "Select * from Login where Username = ? and Password =?";
            pst = connect.prepareStatement(sql);
            pst.setString(1, user);
            pst.setString(2, pass);
            rst = pst.executeQuery();
            if(rst.next()){

                outputlabel.setText("User details are correct");

            }
            else{

                outputlabel.setText("User details are incorrect");
                loginattempts.set(loginattempts.add(1).get());
                if(loginattempts.get()>2){



                    timer();
                    outputlabel.setText("Please try again");
                    usernamefield.setEditable(true);
                    passwordfield.setEditable(true);


                }
            }
        }catch(SQLException ex){
            ex.printStackTrace();

        }
    }
}

