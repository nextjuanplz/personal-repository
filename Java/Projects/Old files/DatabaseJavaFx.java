/*Juan Manuel Alzate Vanegas
 * COP 3330
 * DatabaseJavaFx
 * 8/2/2017
 */
package databasejavafx;

import dataModel.FilmDAO;
import inputOutput.ConnectionData;
import inputOutput.PostgreSQLConnect;
import inputOutput.XmlParser;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Juan Manuel Alzate Vanegas
 */

public class DatabaseJavaFx extends Application {
    
    //Constants
    private static final int COLS = 4;
    private static final String[] COL_LABELS = {"Title","Description",
                                                "Rental Rate", "Rating"};
    private static final int RENTAL_RATE_INDEX = 2;
    private static final int MIN_WIDTH = 400;
    private static final int PREF_HEIGHT = 500;
    private static final String STAGE_TITLE = "Films for Rent";
    private static final String BUTTON_TEXT = "Fetch films from database";
    private static final String PROPERTIES_XML = "inputOutput/properties.xml";
    
    //Fields
    private final Logger logger;
    private ObservableList<FilmDAO> list;
    
    //Constructor
    public DatabaseJavaFx(){
        //Instantiate logger
        this.logger = Logger.getLogger(DatabaseJavaFx.class.getName());
        //Observable List
        this.list = FXCollections.observableArrayList();
    }
    
    //Methods
    @Override
    public void start(Stage primaryStage) {
        //Initialize table
        TableView table = new TableView();
        table.setEditable(false);
        Label label = new Label("Films");
        //Initialize columns
        ArrayList<TableColumn> columns = new ArrayList<>();
        int k = 0;
        while (columns.iterator().hasNext()) {
            //Set text
            TableColumn col = new TableColumn(COL_LABELS[k]);
            //Set min width
            col.setMinWidth(MIN_WIDTH);
            
            //Bind data to DAO
            //Rental rate
            if (k == RENTAL_RATE_INDEX) {
                col.setCellValueFactory(new PropertyValueFactory<FilmDAO, Double>(COL_LABELS[k]));
            }
            //All others
            else {
                col.setCellValueFactory(new PropertyValueFactory<FilmDAO, String>(COL_LABELS[k]));
            }
            
            //Add to ArrayList
            columns.add(col);
           
            //Go onto next one
            k++;
            columns.iterator().next();
        }
        
        //Add to table
        table.getColumns().addAll(columns);
        
        //Instantiate button
        Button btn = new Button(BUTTON_TEXT);
        
        //Event Handler
        btn.setOnAction((ActionEvent event) -> {
            //Fetch data
            fetchData(table);
        }
        );
                    
        //Instantiate Scene with Group LM
        Scene scene = new Scene(new Group());

        //Instantiate VBox
        VBox vBox = new VBox();
        vBox.setPrefHeight(PREF_HEIGHT);
        vBox.setStyle("-fx-background-color: black;");
        //Add to table
        vBox.getChildren().addAll(label, table);
        //Add to scene
        ((Group) scene.getRoot()).getChildren().addAll(vBox,btn);

        //Set title
        primaryStage.setTitle(STAGE_TITLE);
        //Set scene
        primaryStage.setScene(scene);
        //Display UI
        primaryStage.show();   
    }
    
    //Methods    
    public void fetchData(TableView table) {
            //Try to connect
            try { 
                //Successful connections
                Connection connection = getConnection();
                table.setItems(fetchFilms(connection));
            }
            //Catch exceptions 
            catch (SQLException | ClassNotFoundException ex) {
                //Log them
                logger.log(Level.SEVERE, ex.toString());
            }                   
        }

        private Connection getConnection() throws ClassNotFoundException, SQLException {
            //Instantiate XML Parser
            XmlParser xmlParser = new XmlParser(PROPERTIES_XML);

            //Create SQL connection
            ConnectionData connectionData = xmlParser.getConnectionData();
            PostgreSQLConnect sqlConnect = new PostgreSQLConnect(connectionData);

            //Get connection
            return (sqlConnect.getConnect());         
        }

        private ObservableList<FilmDAO> fetchFilms(Connection con) throws SQLException {
            //Instantiate list
            ObservableList<FilmDAO> myList = FXCollections.observableArrayList();
            //Query
            String query = "select title, rental_rate, rating, description"
                           + "\n" + "from film" + "\n" + "order by title;";

            Statement statement = con.createStatement();
            ResultSet resSet = statement.executeQuery(query);

            //Check for more entries
            while (resSet.next() == true) {
                //New FilmDAO
                FilmDAO film = new FilmDAO();
                //Call setters for each member
                film.setTitle(resSet.getString(COL_LABELS[0]));
                film.setDescription(resSet.getString(COL_LABELS[1]));                    
                film.setRentalPrice(resSet.getDouble(COL_LABELS[2]));
                film.setRating(resSet.getString(COL_LABELS[3]));
                //Add to list
                myList.add(film);                    
            }

            //Return the list
            return myList;
        }      

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        //Launch
        launch(args);
    }
}
