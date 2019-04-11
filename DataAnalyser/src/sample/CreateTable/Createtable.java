package sample.CreateTable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.DataCenter;

import javax.swing.table.TableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Createtable {

    Connection conn = null;
    PreparedStatement ps = null;


    ObservableList datatypes = FXCollections.observableArrayList("Date", "Numeric", "Text");
    ObservableList columninfo = FXCollections.observableArrayList();
    @FXML
    private ComboBox datatype;
    @FXML
    private TextField tablenametxt;
    @FXML
    private TextField Columnnametxt;
    @FXML
    private Spinner datasizetxt;
    @FXML
    private Button createTable;
    @FXML
    private Button AddRow;
    @FXML
    private Button RemoveRow;
    @FXML
    private TableView<Tableinfo> tableinfoTableView;
    @FXML
    private TableColumn<Tableinfo, String> columnname;
    @FXML
    private TableColumn<Tableinfo, String> columndatatype;
    @FXML
    private TableColumn<Tableinfo, String> columnmaxvalue;

    String Tablenames;

    String tablecreatorsql ="";

    @FXML
    private void AddColumn(ActionEvent evt) throws Exception {
         Tablenames = tablenametxt.getText();
        String ColumnNames = Columnnametxt.getText();
        String ColumnTypes = datatype.getSelectionModel().getSelectedItem().toString();
        int ColumnSizes = Integer.parseInt(datasizetxt.getEditor().getText());


        if (Tablenames.equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Table Name - System Alert");
            alert.setHeaderText("Table name not found\n  \nKindly enter a Table name and Retry");
            alert.setContentText("Click \"OK\" to Continue.");
            alert.showAndWait();
        } else {
            columninfo.add(new Tableinfo(ColumnNames, ColumnTypes, ColumnSizes));
        }

        tablecreatorsql = tablecreatorsql+" "+ColumnNames+" "+ColumnTypes+",";
        columnname.setCellValueFactory(new PropertyValueFactory<>("columnName"));
        columndatatype.setCellValueFactory(new PropertyValueFactory<>("columnType"));
        columnmaxvalue.setCellValueFactory(new PropertyValueFactory<>("columnSize"));
        tableinfoTableView.setItems(columninfo);

    }



    @FXML
    private void createTable(ActionEvent evt) throws Exception {

       String sql = "CREATE TABLE "+Tablenames+" (" + tablecreatorsql + ");";
       int x = sql.lastIndexOf(',');
       sql = charRemoveAt(sql,x);

       try{

          ps  = conn.prepareStatement(sql);
          ps.execute();

       }
       catch(Exception e){
           e.printStackTrace();
       }

    }

    public static String charRemoveAt(String str, int p){
        return str.substring(0,p)+str.substring(p+1);
    }

    public void initialize(){

        datatype.setItems(datatypes);
        conn = DataCenter.DbConnector();

    }
}
