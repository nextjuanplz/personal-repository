/*
 * Karin Whiting
 * COP 3330 Object Oriented Programming
 * University of Central Florida
 */
package h2app;

import java.sql.*;
import java.util.logging.*;
import javafx.application.Application;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

// when interacting with databases our goal is to CRUD
// CREATE
// READ
// UPDATE
// DELETE

public class H2app extends Application {
  private static final Logger logger = Logger.getLogger(H2app.class.getName());
  private static final String[] SAMPLE_NAME_DATA = { "John", "Jill", "Jack", "Jerry" };

  public static void main(String[] args) 
  { 
      launch(args); 
  }

  @Override public void start(Stage stage) {
    // UI control that displays a list of data
    // similar to swing's JTextArea
    final ListView<String> nameView = new ListView();

    // same as a JButton in swing
    final Button fetchNames = new Button("Fetch names from the database");
    
    // this is the same as JButton.addActionListener() in swing
    fetchNames.setOnAction(new EventHandler<ActionEvent>() 
    {
        // same as ActionListener's actionPerformed()
        @Override public void handle(ActionEvent event) 
        {
            // call method and pass UI control ListView
            fetchNamesFromDatabaseToListView(nameView);
        }
    });

    // another button 
    final Button clearNameList = new Button("Clear the name list");
    
    // this is the same as JButton.addActionListener
    clearNameList.setOnAction(new EventHandler<ActionEvent>() 
    {
        // same as ActionListener's actionPerformed()
        @Override public void handle(ActionEvent event) 
        {
            // remove data from the ListView UI control
            nameView.getItems().clear();
        }
    });

    // create a layout mananger
    VBox layout = new VBox(10);
    // set the style, similar to CSS
    layout.setStyle("-fx-background-color: cornsilk; -fx-padding: 15;");
    // everything part of the UI control VBox are the children
    layout.getChildren().setAll(
      HBoxBuilder.create().spacing(10).children(
        fetchNames, 
        clearNameList    
      ).build(),      
      nameView
    );
    // setting the dimensions like in swing
    layout.setPrefHeight(200);

    // set up the scene
    stage.setScene(new Scene(layout));
    // show the UI
    stage.show();
  }

  private void fetchNamesFromDatabaseToListView(ListView listView) {
    // call method getConnection in the class to connect to the database
      try (Connection con = getConnection()) {
        // check the database to see if the schema actually exists 
          if (!schemaExists(con)) 
          {
            // if it doesn't exist , create it!
            createSchema(con);
            // then populate the database table
            populateDatabase(con);
        }
          // populate the UI control ListView with the data from the database
          // by calling method fetchNames()
      listView.setItems(fetchNames(con));
    } catch (SQLException | ClassNotFoundException ex) 
    {
        logger.log(Level.SEVERE, null, ex);
    }
  }

  private Connection getConnection() throws ClassNotFoundException, SQLException {
    // using the logger to track application progress for the user
    logger.info("Getting a database connection");
    // just like in swing, getting the appropriate driver for the database
    Class.forName("org.postgresql.Driver");
    // just like in swing, establishing the credentials to connect to the database
    return DriverManager.getConnection("jdbc:postgresql://localhost:5432/sample", "postgres", "cop3330");
  }

  private void createSchema(Connection con) throws SQLException {
    logger.info("Creating schema");
    Statement st = con.createStatement();
    String table = "create table employee(id integer, name varchar(64))";
    st.executeUpdate(table);
    logger.info("Created schema");
  }

  private void populateDatabase(Connection con) throws SQLException {
    logger.info("Populating database");      
    Statement st = con.createStatement();      
    int i = 1;
    for (String name: SAMPLE_NAME_DATA) {
        //insert into employee values(2, 'Jill');
        String insert = "insert into employee values(" + i + ",'" + name + "');";
        logger.info("insert statement " + insert);    
//        st.executeUpdate("insert into employee values(" + i + ",'" + name + "');");
        st.executeUpdate(insert);
        i++;
    }
    logger.info("Populated database");
  }
 /**
  * schemaExists: check if the desired table exists in the database
  * @param con
  * @return boolean
  * 
  */
  private boolean schemaExists(Connection con) {
    logger.info("Checking for Schema existence");      
    try 
    {
        Statement st = con.createStatement();      
        st.executeQuery("select count(*) from employee");
        logger.info("Schema exists");      
    } 
    catch (SQLException ex) 
    {
      logger.info("Existing DB not found will create a new one");
      return false;
    }

    return true;
  }

  private ObservableList<String> fetchNames(Connection con) throws SQLException {
    logger.info("Fetching names from database");
    ObservableList<String> names = FXCollections.observableArrayList();

    Statement st = con.createStatement();      
    ResultSet rs = st.executeQuery("select name from employee");

    while (rs.next()) 
    {
      names.add(rs.getString("name"));
    }

    logger.info("Found " + names.size() + " names");

    return names;
  }
}