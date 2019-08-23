/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system32;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import javax.swing.JOptionPane;

/**
 *
 * @author pc-29
 */
public class FXMLDocumentController implements Initializable {
    Connection conn=null;
    private Connection conexion;
    
    String driver = "org.postgresql.Driver";
    String ruta = "jdbc:postgresql://172.16.0.20:5432/bpaleta";
    String user = "user1";
    String password = "123456";
    
    @FXML
    private ObservableList<ObservableList> data;
    @FXML
    private Label label;
    private TextField sabor;
    @FXML
    private Button button;
    @FXML
    private TableView Tabla1;
    @FXML
    private TextField tamanio;
    @FXML
    private TextField precio;
    @FXML
    private TextField cantidad;
    
    @FXML
    private void handleButtonAction(ActionEvent event)throws ClassNotFoundException, SQLException {
    }
    public void handleButtonActionC(ActionEvent event) throws ClassNotFoundException, SQLException {
        Connection c ;
        Tabla1.getColumns().clear();
          data = FXCollections.observableArrayList();
          try{
            Class.forName(driver);
            Connection conn=(Connection) DriverManager.getConnection(ruta,user,password);
            //SQL FOR SELECTING ALL OF CUSTOMER
            String SQL = "select * from Paletas where sabor = 'Chocolate'";
            //ResultSet
            ResultSet rs = conn.createStatement().executeQuery(SQL);
     
            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                //We are using non property style for making dynamic table
                final int j = i;                
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {                                                                                              
                        return new SimpleStringProperty(param.getValue().get(j).toString());                        
                    }                    
                });
                Tabla1.getColumns().addAll(col); 
                System.out.println("Column ["+i+"] ");
            }

            while(rs.next()){
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added "+row );
                data.add(row);
            }
            //FINALLY ADDED TO TableView
            Tabla1.setItems(data);
          }catch(Exception e){
              e.printStackTrace();
              System.out.println("Error on Building Data");             
          }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }       
}