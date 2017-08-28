/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.io.File;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

//import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 *
 * @author Juan
 */
public class DataFile implements IDataFile {
    //Constructor
    public DataFile(String fileName) {
        this.workBook = new XSSFWorkbook();
        this.fileName = fileName;
        readFile();
        createFiles();
    }
    
    //Fields
    private String fileName;
    private Object dataArray[];
    private double avgSpeed;
    private double brakeDur;
    private int numberOfBrakes;
    private int rowSize;
    private XSSFWorkbook workBook;
    
    //Methods
    private void readFile() {
      try {
            //Open Excel File
            FileInputStream excelFile = new FileInputStream(new File(this.fileName));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();

            //Read contents
            while (iterator.hasNext()) {

                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();

                while (cellIterator.hasNext()) {

                    Cell currentCell = cellIterator.next();
                    //getCellTypeEnum shown as deprecated for version 3.15
                    //getCellTypeEnum ill be renamed to getCellType starting from version 4.0
                    
                    
                    //Get cell address of reference point
                    if(currentCell.getContents() == REFERENCE_POINT) {
                        //Save row number to refRow
                        //Save value to refVal
                        //int refRow = currentRow;
                        
                    }
                    
                    //Starting from this row, read column down
                        //Row  0 (A) - frame to time_adj
                        //int i = 0;
                            //while(cellIterator.hasNext()) {
                                //dataArray[i][TIME_ROW] = (cell[refRow + i] - refVal)/60
                                //i++;
                            //}
                            //this.rowSize = i;
                            //i = 0;
                        //Row 8(I) - accel
                            //while(cellIterator.hasNext()) {
                                //dataArray[i][ACCEL_ROW] = cell[refRow + i];
                                //i++;
                            //}
                        //Row 9 - braking
                            //while(cellIterator.hasNext()) {
                                //if(cell[refRow + i] > BRAKE_THRESHOLD) dataArray[i][BRAKE_ROW] = 1;
                                //else dataArray[i][BRAKE_ROW] = 0;
                                //i++;
                            //}
                        //Row 10(K) - speed
                            //while(cellIterator.hasNext()) {
                                //dataArray[i][ACCEL_ROW] = (cell[refRow + i] - refVal)/60
                                //i++;
                            //}
                    

                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void createFiles() {
        //Times to brake
        try{
            PrintWriter writer = new PrintWriter(this.fileName + "BRAKE" + "txt", "UTF-8");
            for (int i = 0; i < this.rowSize; i++)
                writer.println(this.dataArray[i][TIME_COL] + " " + this.dataArray[i][BRAKE_ROW]);
            writer.close();
        } catch (IOException e) {
           // do something
        }
        
        
        //Times to brake
        for( : )
        
        
        //Speed
        
        //Accel
        
    }
    
    //Getters and setters
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public double getAvgSpeed() {
        return avgSpeed;
    }

    public void setAvgSpeed(double avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    public double getBrakeDur() {
        return brakeDur;
    }

    public void setBrakeDur(double brakeDur) {
        this.brakeDur = brakeDur;
    }

    public int getNumberOfBrakes() {
        return numberOfBrakes;
    }

    public void setNumberOfBrakes(int numberOfBrakes) {
        this.numberOfBrakes = numberOfBrakes;
    }
    
}
