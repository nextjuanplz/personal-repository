
package userInterface;

import com.sun.rowset.CachedRowSetImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.sql.RowSetListener;
import javax.sql.rowset.CachedRowSet;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 *
 * @author kwhiting
 */
public class CoffeesTableModel implements TableModel {

  CachedRowSet coffeesRowSet; // The ResultSet to interpret
  ResultSetMetaData metadata; // Additional information about the results
  int numcols, numrows; // How many rows and columns in the table

    /**
     *
     * @return
     */
    public CachedRowSet getCoffeesRowSet() {
    return coffeesRowSet;
  }

    /**
     *
     * @param rowSetArg
     * @throws SQLException
     */
    public CoffeesTableModel(CachedRowSet rowSetArg) throws SQLException {

    this.coffeesRowSet = rowSetArg;
    this.metadata = this.coffeesRowSet.getMetaData();
    numcols = metadata.getColumnCount();

    // Retrieve the number of rows.
    this.coffeesRowSet.beforeFirst();
    this.numrows = 0;
    while (this.coffeesRowSet.next()) {
      this.numrows++;
    }
    this.coffeesRowSet.beforeFirst();
  }

    /**
     *
     * @param listener
     */
    public void addEventHandlersToRowSet(RowSetListener listener) {
    this.coffeesRowSet.addRowSetListener(listener);
  }

    /**
     *
     * @param coffeeName
     * @param supplierID
     * @param price
     * @param sales
     * @param total
     * @throws SQLException
     */
    public void insertRow(String coffeeName, int supplierID, float price,
                        int sales, int total) throws SQLException {

    try {
      this.coffeesRowSet.moveToInsertRow();
      this.coffeesRowSet.updateString("COF_NAME", coffeeName);
      this.coffeesRowSet.updateInt("SUP_ID", supplierID);
      this.coffeesRowSet.updateFloat("PRICE", price);
      this.coffeesRowSet.updateInt("SALES", sales);
      this.coffeesRowSet.updateInt("TOTAL", total);
      this.coffeesRowSet.insertRow();
      this.coffeesRowSet.moveToCurrentRow();
    } catch (SQLException e) {
      JDBCTutorialUtilities.printSQLException(e);
    }
  }

    /**
     *
     */
    public void close() {
    try {
      coffeesRowSet.getStatement().close();
    } catch (SQLException e) {
      JDBCTutorialUtilities.printSQLException(e);
    }
  }

  /** Automatically close when we're garbage collected */
  protected void finalize() {
    close();
  }

  /** Method from interface TableModel; returns the number of columns
     * @return  */

  public int getColumnCount() {
    return numcols;
  }

    /** Method from interface TableModel; returns the number of rows
     * @return  */

  public int getRowCount() {
    return numrows;
  }

  /** Method from interface TableModel; returns the column name at columnIndex
   *  based on information from ResultSetMetaData
     * @param column
     * @return 
   */

  public String getColumnName(int column) {
    try {
      return this.metadata.getColumnLabel(column + 1);
    } catch (SQLException e) {
      return e.toString();
    }
  }

  /** Method from interface TableModel; returns the most specific superclass for
   *  all cell values in the specified column. To keep things simple, all data
   *  in the table are converted to String objects; hence, this method returns
   *  the String class.
     * @param column
     * @return 
   */

  public Class getColumnClass(int column) {
    return String.class;
  }

  /** Method from interface TableModel; returns the value for the cell specified
   *  by columnIndex and rowIndex. TableModel uses this method to populate
   *  itself with data from the row set. SQL starts numbering its rows and
   *  columns at 1, but TableModel starts at 0.
     * @param rowIndex
     * @param columnIndex
     * @return 
   */

  public Object getValueAt(int rowIndex, int columnIndex) {

    try {
      this.coffeesRowSet.absolute(rowIndex + 1);
      Object o = this.coffeesRowSet.getObject(columnIndex + 1);
      if (o == null)
        return null;
      else
        return o.toString();
    } catch (SQLException e) {
      return e.toString();
    }
  }

    /** Method from interface TableModel; returns true if the specified cell
     *  is editable. This sample does not allow users to edit any cells from
     *  the TableModel (rows are added by another window control). Thus,
     *  this method returns false.
     * @param rowIndex
     * @param columnIndex
     * @return 
     */

  public boolean isCellEditable(int rowIndex, int columnIndex) {
    return false;
  }

  // Because the sample does not allow users to edit any cells from the
  // TableModel, the following methods, setValueAt, addTableModelListener,
  // and removeTableModelListener, do not need to be implemented.

    /**
     *
     * @param value
     * @param row
     * @param column
     */

  public void setValueAt(Object value, int row, int column) {
    System.out.println("Calling setValueAt row " + row + ", column " + column);
  }

    /**
     *
     * @param l
     */
    public void addTableModelListener(TableModelListener l) {
  }

    /**
     *
     * @param l
     */
    public void removeTableModelListener(TableModelListener l) {
  }

  }
