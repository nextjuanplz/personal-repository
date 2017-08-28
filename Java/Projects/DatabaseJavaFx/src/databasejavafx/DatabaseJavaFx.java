/*Juan Manuel Alzate Vanegas
 * COP 3330
 * DatabaseJavaFx
 * 8/1/2017
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
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Juan Manuel Alzate Vanegas
 */

public class DatabaseJavaFx extends Application {
    
    //Constants
    private static final int COLS = 4;
    private static final int MIN_WIDTH = 400;
    private static final int PREF_HEIGHT = 600;
    
    //Labels
    private static final String FILM_TITLE = "Films";
    private static final String[] COL_LABELS = {"Title","Description",
                                                "Rental Rate", "Rating"};
    private static final String[] DB_LABELS = {"title","rental_rate",
                                                "rating", "description"};
    private static final String STAGE_TITLE = "Films for Rent";
    private static final String BUTTON_TEXT = "Fetch films from database";
    private static final String PROPERTIES_XML = "inputOutput/properties.xml";
    
    //Fields
    private static final Logger LOGGER = Logger.getLogger(DatabaseJavaFx.class.getName());
    private static final ObservableList<FilmDAO> LIST = FXCollections.observableArrayList();
    
    //Methods
    @Override
    public void start(Stage primaryStage) {
        //Initialize table
        TableView table = new TableView();
        table.setPrefHeight(PREF_HEIGHT);
        table.setMinWidth(MIN_WIDTH * 2.75);
        table.setEditable(false);
        Label label = new Label(FILM_TITLE);
        //Initialize columns
        ArrayList<TableColumn> columns = new ArrayList<>();   
        int k = 0;
        while (k < COLS) {
            //Set text
            TableColumn col = new TableColumn(COL_LABELS[k]);
            //Add to ArrayList
            columns.add(col);     
            //Go onto next one
            k++;
        }
        
        //Bind data to DAO        
        columns.get(0).setCellValueFactory(new PropertyValueFactory<>("title"));
        columns.get(0).setStyle( "-fx-alignment: CENTER;");
        columns.get(0).setMinWidth(MIN_WIDTH/2.75);
        columns.get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        columns.get(1).setStyle( "-fx-alignment: CENTER;");
        columns.get(1).setMinWidth(MIN_WIDTH * 1.75);
        columns.get(2).setCellValueFactory(new PropertyValueFactory<>("rentalPrice"));
        columns.get(2).setStyle( "-fx-alignment: CENTER;");
        columns.get(2).setMinWidth(MIN_WIDTH/8);
        columns.get(3).setCellValueFactory(new PropertyValueFactory<>("rating"));
        columns.get(3).setStyle( "-fx-alignment: CENTER;");
        columns.get(3).setMinWidth(MIN_WIDTH/8);
        
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
        VBox vBox = new VBox(10);
        vBox.setPrefHeight(MIN_WIDTH * 1.75);
        vBox.setStyle("-fx-background-color: black;");
        vBox.setPrefWidth(MIN_WIDTH * 2.75);
        
        //Add to table
        vBox.getChildren().addAll(label, table);
        
        //Add to scene
        ((Group) scene.getRoot()).getChildren().addAll(vBox,btn);

        //Set title
        primaryStage.setTitle(STAGE_TITLE);
        //Set scene
        primaryStage.setScene(scene);
        primaryStage.setHeight(MIN_WIDTH * 1.75);
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
                LOGGER.log(Level.SEVERE, ex.toString());
            }                   
    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        //Instantiate XML Parser
        LOGGER.info("Getting a database connection");
        XmlParser xmlParser = new XmlParser(PROPERTIES_XML);

        //Create SQL connection
        ConnectionData connectionData = xmlParser.getConnectionData();
        PostgreSQLConnect sqlConnect = new PostgreSQLConnect(connectionData);

        //Get connection
        return (sqlConnect.getConnect());         
    }

    private ObservableList<FilmDAO> fetchFilms(Connection con) throws SQLException {

        //Query
        LOGGER.info("Fetching films from database");
        String query = "select title, rental_rate, rating, description"
                       + "\n" + "from film" + "\n" + "order by title;";
        LOGGER.info(query.replace("\n", " "));
        
        Statement statement = con.createStatement();
        ResultSet resSet = statement.executeQuery(query);

        //Check for more entries
        while (resSet.next() == true) {
            //New FilmDAO
            FilmDAO film = new FilmDAO();
            //Call setters for each member
            film.setTitle(resSet.getString(DB_LABELS[0]));
            film.setDescription(resSet.getString(DB_LABELS[3]));                    
            film.setRentalPrice(resSet.getDouble(DB_LABELS[1]));
            film.setRating(resSet.getString(DB_LABELS[2]));
            //Add to list
            LIST.add(film);                    
        }
        
        LOGGER.log(Level.INFO, "Found {0} films", LIST.size());

        //Return the list
        return LIST;
    }      

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        //Launch
        launch(args);
    }
}
