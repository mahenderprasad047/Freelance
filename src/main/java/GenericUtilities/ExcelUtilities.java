package GenericUtilities;

import java.io.File;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilities 
{
	
	
	static XSSFWorkbook wb;
	
	public static Object[][] getDataFromExcel(String sheetName)
	{
		try {
			wb = new XSSFWorkbook(new File(System.getProperty("user.dir")+"/TestData/TestData1.xlsx"));
		} 
		catch (InvalidFormatException e) {
			System.out.println("File format exception. Message - " + e.getMessage());
		} 
		catch (IOException e) {
			System.out.println("File input output exception. Message - "+ e.getMessage());
		}
		
		int row = wb.getSheet(sheetName).getPhysicalNumberOfRows();
		int column = wb.getSheet(sheetName).getRow(0).getPhysicalNumberOfCells();
		
		Object[][] arr = new Object[row][column];
		
		for(int i=0;i<row; i++)
		{
			for(int j=0; j<column; j++)
			{
				arr[i][j] = GetCellData(sheetName,i,j);		//wb.getSheet(sheetName).getRow(i).getCell(j).getCellType();
			}
		}
		
		
		return arr;
	}
	
	public static String GetCellData(String sheetName, int row, int column)
	{
		String data = null;
		 XSSFCell cell = wb.getSheet(sheetName).getRow(row).getCell(column);
		 if(cell.getCellType() == CellType.STRING)
		 {
			 data = cell.getStringCellValue();
		 }
		 else if(cell.getCellType() == CellType.NUMERIC)
		 {
			data = String.valueOf(cell.getNumericCellValue()); 
		 }
		 
		 else if(cell.getCellType() == CellType.BLANK)
		 {
			 data = "";
		 }
		 
		 return data;
	}	
	
	
	
}
