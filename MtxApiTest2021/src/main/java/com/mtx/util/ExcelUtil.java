package com.mtx.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelUtil {
    private static Logger logger = Logger.getLogger(ExcelUtil.class);
    public XSSFWorkbook excelWorkbook;
    public XSSFSheet xssfSheet;
    public Row row;
    public Cell cell;
    public ExcelUtil(String excelPath) throws IOException {
    	FileInputStream fileInputStream = new FileInputStream(new File(excelPath));
    	this.excelWorkbook = new XSSFWorkbook(fileInputStream);
    }
    
    public String getCellData(String sheetName,int rownum,int colnum) {
    	String cellValue = null;
    	try {
    		xssfSheet = excelWorkbook.getSheet(sheetName);
        	row = xssfSheet.getRow(rownum);
        	cell = row.getCell(colnum);
        	if (cell.getCellType() == XSSFCell.CELL_TYPE_BOOLEAN) {
        		cellValue = String.valueOf(cell.getBooleanCellValue());
        	}else if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
        		cellValue = cell.getStringCellValue();
        	}else if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
    			short format = cell.getCellStyle().getDataFormat();
    			System.out.println("******************"+format);
    			if(format==178) {
    				SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
    				Date date = DateUtil.getJavaDate(cell.getNumericCellValue());
    				cellValue = formater.format(date);
    			}else if (format == 177) {
    				//时间
    				DateFormat formater = new SimpleDateFormat("HH:mm");
    				Date date = DateUtil.getJavaDate(cell.getNumericCellValue());
    				cellValue = formater.format(date);
    			}else {
    				DecimalFormat df = new DecimalFormat("0");
    				cellValue = df.format(cell.getNumericCellValue());
    			}
    		}else if (cell.getCellType() == XSSFCell.CELL_TYPE_BLANK) {
				cellValue = "";
			}else {
    			cellValue = cell.getStringCellValue();
    		}
        	logger.info("读取【"+sheetName+"]的第"+rownum+"行第"+colnum+"列的指是："+cellValue);
 
		} catch (Exception e) {
			logger.error(e);
			new RuntimeException(e);
		}
    	return cellValue;
    }
    
    public void close() {
    	try {
			excelWorkbook.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
    
    public Object[][] getSheetData(String sheetName) {
    	int rowNum = excelWorkbook.getSheet(sheetName).getLastRowNum();//最后一行行号
    	int colNum = excelWorkbook.getSheet(sheetName).getRow(0).getLastCellNum();
//    	System.out.println(rowNum);
//    	System.out.println(colNum);
    	Object[][] data = new Object[rowNum][colNum];
    	for(int i=1; i<=rowNum; i++) {
    		for(int j = 0; j<colNum; j++) {
    			String cellData = getCellData(sheetName, i, j);
    			data[i-1][j] = cellData;
    		}
    	}
    	return data;
    }
    
    public static void main(String[] args) throws IOException {
		ExcelUtil excelUtil = new ExcelUtil("src/main/resources/crmparams/crmdata.xlsx");
//		excelUtil.getCellData("新建产品", 3, 2);
		excelUtil.getSheetData("新建产品");
	}
    
    
}
