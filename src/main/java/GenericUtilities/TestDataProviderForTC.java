package GenericUtilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestDataProviderForTC 
{
	static XSSFWorkbook wb;
	
	public static List<Map<String, String>> getDataFromExcel(String TCName, String excelName, String sheetName)
	{
		try {
			
			wb = new XSSFWorkbook(new File(System.getProperty("user.dir")+"/TestData/" + excelName + ".xlsx"));
		} 
		catch (InvalidFormatException e) {
			System.out.println("File format exception. Message - " + e.getMessage());
		} 
		catch (IOException e) {
			System.out.println("File input output exception. Message - "+ e.getMessage());
		}
		
		int row = wb.getSheet(sheetName).getPhysicalNumberOfRows();
		int column = wb.getSheet(sheetName).getRow(0).getPhysicalNumberOfCells();
		
		//Object[][] arr = new Object[row][column];
		List<Map<String,String>> lst = new ArrayList<Map<String,String>>();
		
		for(int i=1;i<row; i++)
		{
			if(GetCellData(sheetName,i,0).equalsIgnoreCase(TCName))
			{
				Map<String,String> map = new LinkedHashMap<String,String>();
				
				for(int j=0; j<column; j++)
				{
					map.put(GetCellData(sheetName,0,j),GetCellData(sheetName,i,j));
				}
				lst.add(map);
			}
			
		}
		
		return lst;
		
		
		
		
	}
	
	public static String GetCellData(String sheetName, int row, int column)
	{
		String data = null;
		DataFormatter formatter = new DataFormatter();		//
	    
		 XSSFCell cell = wb.getSheet(sheetName).getRow(row).getCell(column);
		 data = formatter.formatCellValue(cell);
		 
		 /*
		 if(cell.getCellType() == CellType.STRING)
		 {
			 data = formatter.formatCellValue(cell);			//cell.getStringCellValue();
		 }
		 else if(cell.getCellType() == CellType.NUMERIC)
		 {
			data = formatter.formatCellValue(cell);		//String.valueOf(cell.getNumericCellValue()); 
		 }
		 
		 
		 else if(cell.getCellType() == CellType.BLANK)
		 {
			 data = "";
		 }
		 */
		 
		 
		 
		 return data;
	}
	
	public static synchronized void SetDataToAColumnOfExcel(String TCName, String excelName, String sheetName, List<String> lst,String columnName) 
	{	
		FileInputStream file = null;
		
		try {
			file = new FileInputStream(new File(System.getProperty("user.dir")+"/TestData/" + excelName + ".xlsx"));
			wb = new XSSFWorkbook(file);
		} 
		catch (Exception e) {
			System.out.println("Log-INFO : File input output exception. Message - "+ e.getMessage());
		}
		
		
		int row = wb.getSheet(sheetName).getPhysicalNumberOfRows();
		int column = wb.getSheet(sheetName).getRow(0).getPhysicalNumberOfCells();
		int k = 0;
		for(int i=1;i<row; i++)
		{
			if(GetCellData(sheetName,i,0).contains(TCName))		// got the TC
			{
				for(int j=0; j<column; j++)
				{
					if(GetCellData(sheetName,0,j).contains(columnName))	// got the column
					{
						//XSSFCell cell = wb.getSheet(sheetName).getRow(i).getCell(j);
						Cell cell = wb.getSheet(sheetName).getRow(i).createCell(j);
						cell.setCellValue(lst.get(k).toString());
						k = k+1;
					}
				}
			}
			
		}
		
		
		try {
			FileOutputStream outFile =new FileOutputStream(new File(System.getProperty("user.dir")+"/TestData/" + excelName + ".xlsx"));
			file.close();
			wb.write(outFile);
			outFile.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		
		
		System.out.println("Log-INFO : End of Writing the excel");
	}
}