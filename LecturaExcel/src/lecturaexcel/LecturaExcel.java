/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lecturaexcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFShape;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFSimpleShape;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Cristhofer Moya
 */
public class LecturaExcel {

   //Metodo para leer documento excel
    private void readExcelFile(String fileName) {
        /**
         * Create a new instance for cellDataList
         */
        List cellDataList = new ArrayList();
        try {
            /**
             * Create a new instance for FileInputStream class
             */
            FileInputStream fileInputStream = new FileInputStream(fileName);
            /**
             * Create a new instance for POIFSFileSystem class
             */
            POIFSFileSystem fsFileSystem = new POIFSFileSystem(fileInputStream);
            /*
             * Create a new instance for HSSFWorkBook Class
             */
            HSSFWorkbook workBook = new HSSFWorkbook(fsFileSystem);
            HSSFSheet hssfSheet = workBook.getSheetAt(0);
            HSSFPatriarch pat = hssfSheet.getDrawingPatriarch();

            List children = pat.getChildren();
            Iterator it = children.iterator(); 
            int cont=0;
    while(it.hasNext()) {    
        HSSFShape shape = (HSSFShape)it.next();
       /* System.out.println(shape.getAnchor().getDx1());
        System.out.println(shape.getAnchor().getDy1());
        System.out.println(shape.getAnchor().getDx2());
        System.out.println(shape.getAnchor().getDy2());*/
        System.out.println("Figura #"+cont+" "+(shape.getFillColor()==0?"X":"O") );
        cont++;
       
    }  
            /**
             * Iterate the rows and cells of the spreadsheet to get all the
             * datas.
             */
            Iterator rowIterator = hssfSheet.rowIterator();
            while (rowIterator.hasNext()) {
                HSSFRow hssfRow = (HSSFRow) rowIterator.next();
                Iterator iterator = hssfRow.cellIterator();
                List cellTempList = new ArrayList();
                while (iterator.hasNext()) {
                    HSSFCell hssfCell = (HSSFCell) iterator.next();
                    cellTempList.add(hssfCell);
                }
                cellDataList.add(cellTempList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        /**
         * Call the printToConsole method to print the cell data in the console.
         */
        printToConsole(cellDataList);
    }

    /**
     * This method is used to print the cell data to the console.
     *
     * @param cellDataList - List of the data's in the spreadsheet.
     */
    private void printToConsole(List cellDataList) {
        for (int i = 0; i < cellDataList.size(); i++) {
            List cellTempList = (List) cellDataList.get(i);
            for (int j = 0; j < cellTempList.size(); j++) {
                HSSFCell hssfCell = (HSSFCell) cellTempList.get(j);
               String stringCellValue = hssfCell.toString();
               System.out.print(hssfCell.toString());
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        String dir = "I:\\Entradas\\URyS\\Oscar René González Estrada (cubre a Julio Quevedo).xls";
        new LecturaExcel().readExcelFile(dir);
    }
}
