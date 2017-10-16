package com.fh.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.fh.util.poi.OptionalUtils;

import net.sf.excelutils.ExcelException;

/**
 * 从EXCEL导入到数据库 创建人：FH 创建时间：2014年12月23日
 * 
 * @version
 */
public class ObjectExcelRead {
	private static final String OFFICE_EXCEL_XLS = ".xls";
	private static final String OFFICE_EXCEL_XLSX = ".xlsx";
	private static final String IS_EXCEL_YES = "YES";
	private static final String IS_EXCEL_NO = "NO";
	private static final String IS_EXCEL_NOFILE = "NOFILE";

	/**
	 * 判断是否为excel文件
	 * 
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static String isExcel(MultipartFile file) throws Exception {
		if (file == null || file.isEmpty()) {
			return IS_EXCEL_NOFILE;
		}
		if (file.getOriginalFilename().endsWith(OFFICE_EXCEL_XLS)
				|| file.getOriginalFilename().endsWith(OFFICE_EXCEL_XLSX))
			return IS_EXCEL_YES;
		else {
			return IS_EXCEL_NO;
		}
	}
	
	public static List<PageData> read(MultipartFile file,int type) throws Exception{
	    /** 判断文件的类型，是2003还是2007 */
        boolean isExcel2003 = true;
        if (file.isEmpty() || !(isExcel2003(file.getOriginalFilename())
                || isExcel2007(file.getOriginalFilename()))) {
            isExcel2003 = false;
        }
        if (isExcel2003) {
            return readXLS(file,type);
        } else {
            return readXLSX(file,type);
        }
	}
	
	/**
	 * <p>Description: 判断excel是否是xls</p>
	 * @param filePath
	 * @return
	 */
    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    /**
     * <p>Description: 判断excel是否是xlsx</p>
     * @param filePath
     * @return
     */
    public static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }

	/**
	 * 读取excel
	 * 
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static List<PageData> readXLSX(MultipartFile file,int type) throws Exception {
		InputStream input = file.getInputStream();
		OPCPackage op = OPCPackage.open(input);
		XSSFWorkbook wb = new XSSFWorkbook(op);
		int sheets = wb.getNumberOfSheets();
		List<PageData> list = new ArrayList<PageData>();

		PageData accountOrder;

		int curSheets;
		int curRows;
		Sheet sheet;

		for (int i = 0; i < sheets; i++) {
			curSheets = i;
			sheet = wb.getSheetAt(i);
			if (sheet == null)
				continue;
			for (Row row : sheet) {
				if (OptionalUtils.isPersent(row)) {
					curRows = row.getRowNum();
					Cell zero = row.getCell(0);
					if ((curSheets == 0 && curRows == 0)){
						if(type==1 &&!"vin".equalsIgnoreCase(zero.getStringCellValue())){
							throw new ExcelException("1001");
						}else if (type==2 &&!"服务站代码".equalsIgnoreCase(zero.getStringCellValue())) {
							throw new ExcelException("1001");
						}
						continue;
					}	
					else if (OptionalUtils.notPersent(zero))
						break;
					accountOrder = new PageData();
					for (Cell cell : row) {
						if (OptionalUtils.isPersent(cell))
							accountOrder =	cell(cell, accountOrder, curSheets, curRows);
						else
							continue;
					}
					list.add(accountOrder);
				} else {
					continue;
				}

			}
		}

		return list;
	}

	/**
	 * 读取excel
	 * 
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static List<PageData> readXLS(MultipartFile file,int type) throws Exception { InputStream input = file.getInputStream();
		POIFSFileSystem fs = new POIFSFileSystem(input);
		HSSFWorkbook wb = new HSSFWorkbook(fs, true);
		int sheets = wb.getNumberOfSheets();
		List<PageData> list = new ArrayList<PageData>();

		PageData accountOrder;

		int curSheets;
		int curRows;
		Sheet sheet;

		for (int i = 0; i < sheets; i++) {
			curSheets = i;
			sheet = wb.getSheetAt(i);
			if (sheet == null)
				continue;
			if( wb.getSheetAt(0).getLastRowNum()==0){
				throw new ExcelException("1002");
			}
			for (Row row : sheet) {
				if (OptionalUtils.isPersent(row)) {
					curRows = row.getRowNum();
					Cell zero = row.getCell(0);
					
					if ((curSheets == 0 && curRows == 0)){
						if(type==1 &&!"vin".equalsIgnoreCase(zero.getStringCellValue())){
							throw new ExcelException("1001");
						}else if (type==2 &&!"服务站代码".equalsIgnoreCase(zero.getStringCellValue())) {
							throw new ExcelException("1001");
						}				
						continue;
					}
					else if (OptionalUtils.notPersent(zero))
						break;
					accountOrder = new PageData();
					for (Cell cell : row) {
						if (OptionalUtils.isPersent(cell))
							cell(cell, accountOrder, curSheets, curRows);
						else
							continue;

					}
					list.add(accountOrder);
				} else {
					continue;
				}

			}
		}

		return list;
	}

	private static PageData cell(Cell cell, PageData accountOrder, int curSheets, int curRows) throws Exception {
		int curCal = cell.getColumnIndex();

		try {
			String str = getCellValue(cell);
			PageData data =  checkSetValue(curCal, str, accountOrder);
			return data;
		} catch (Exception e) {
			// log.error(e.getMessage(), e);
			if (e instanceof IllegalArgumentException || e instanceof Exception)
				throw new Exception("消息错误：" + e.getMessage() + ";" + (curSheets + 1) + "页，" + (curRows + 1) + "行，"
						+ (curCal + 1) + "列");
			else
				throw new Exception("消息错误：" + (curSheets + 1) + "页，" + (curRows + 1) + "行，" + (curCal + 1) + "列");
		}
	}

	/**
	 * 获取单元格的值
	 * 
	 * @param cell
	 * @return
	 */
	private static String getCellValue(Cell cell) {
		Object obj = "";
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			obj = cell.getStringCellValue();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			obj = cell.getNumericCellValue();
			break;
		case Cell.CELL_TYPE_FORMULA:
			obj = cell.getCellFormula();
			break;
		case Cell.CELL_TYPE_ERROR:
			obj = cell.getErrorCellValue();
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			obj = cell.getBooleanCellValue();
			break;
		case Cell.CELL_TYPE_BLANK:
			break;
		}
		return String.valueOf(obj).trim();
	}
    /**
     * 设置值到对象中
     * @param index
     * @param str
     * @param accountOrder
     * @return 
     */
	private static PageData checkSetValue(int index, String str, PageData accountOrder) {
		accountOrder.put("VIN", str);
		return  accountOrder;
		/*switch (index) {
		case 0: // 流水号 // 第一个值
			// MandoAssert.isTrue(StringUtils.isNotEmpty(str), "对账流水号不能为空");
			accountOrder.setTradeCode(str);
			break;
		// case 1: //银通订单号（略）
		// break;
		case 2: // 创建时间
			// MandoAssert.isTrue(StringUtils.isNotEmpty(str), "订单时间不能为空");
			accountOrder.setTradeDate(getDateValue(str));
			break;
		// case 3: //成功时间（略）
		// break;
		case 4:// 交易金额(元)
			// MandoAssert.isTrue(str.matches(NUMBER_REG), "对账金额数据错误 ：" + str);
			accountOrder.setAmount(new BigDecimal(str));
			break;
		// case 5://退款金额(略)
		// break;
		case 6: // 交易状态
			// MandoAssert.isTrue(StringUtils.isNotEmpty(str), "交易状态不能为空");
			accountOrder.setState(str);
			break;
		case 7: // 商品名称
			// MandoAssert.isTrue(StringUtils.isNotEmpty(str), "商品名称不能为空");
			accountOrder.setDescription(str);
			break;

		}*/
	}

	/**
	 *
	 * 字符串转时间
	 * 
	 * @param str
	 *            需要转换的字符串
	 * 
	 * @return Date
	 * @throws Exception
	 */
	private static Date getDateValue(String str) throws Exception {
		String[] pattern = new String[] { "yyyy-MM", "yyyyMM", "yyyy/MM", "yyyyMMdd", "yyyy-MM-dd", "yyyy/MM/dd",
				"yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss" };
		try {
			return DateUtils.parseDateStrictly(str, pattern);
		} catch (Exception e) {
			throw new Exception("时间格式不支持 ：" + str + "，支持格式： " + Arrays.asList(pattern));
		}
	}

	/**
	 * @param filepath
	 *            //文件路径
	 * @param filename
	 *            //文件名
	 * @param startrow
	 *            //开始行号
	 * @param startcol
	 *            //开始列号
	 * @param sheetnum
	 *            //sheet
	 * @return list
	 */
	public static List<Object> readExcel(String filepath, String filename, int startrow, int startcol, int sheetnum) {
		List<Object> varList = new ArrayList<Object>();

		try {
			File target = new File(filepath, filename);
			FileInputStream fi = new FileInputStream(target);
			HSSFWorkbook wb = new HSSFWorkbook(fi);
			HSSFSheet sheet = wb.getSheetAt(sheetnum); // sheet 从0开始
			int rowNum = sheet.getLastRowNum() + 1; // 取得最后一行的行号

			for (int i = startrow; i < rowNum; i++) { // 行循环开始

				PageData varpd = new PageData();
				HSSFRow row = sheet.getRow(i); // 行
				int cellNum = row.getLastCellNum(); // 每行的最后一个单元格位置

				for (int j = startcol; j < cellNum; j++) { // 列循环开始

					HSSFCell cell = row.getCell(Short.parseShort(j + ""));
					String cellValue = null;
					if (null != cell) {
						switch (cell.getCellType()) { // 判断excel单元格内容的格式，并对其进行转换，以便插入数据库
						case 0:
							cellValue = String.valueOf((int) cell.getNumericCellValue());
							break;
						case 1:
							cellValue = cell.getStringCellValue();
							break;
						case 2:
							cellValue = cell.getNumericCellValue() + "";
							// cellValue =
							// String.valueOf(cell.getDateCellValue());
							break;
						case 3:
							cellValue = "";
							break;
						case 4:
							cellValue = String.valueOf(cell.getBooleanCellValue());
							break;
						case 5:
							cellValue = String.valueOf(cell.getErrorCellValue());
							break;
						}
					} else {
						cellValue = "";
					}

					varpd.put("var" + j, cellValue);

				}
				varList.add(varpd);
			}

		} catch (Exception e) {
			System.out.println(e);
		}

		return varList;
	}
	
	
	
	
	/**
	 * @param filepath
	 *            //文件路径
	 * @param filename
	 *            //文件名
	 * @param startrow
	 *            //开始行号
	 * @param startcol
	 *            //开始列号
	 * @param sheetnum
	 *            //sheet
	 * @return list
	 */
	public static List<PageData> readExceltoPageDate(String filepath, String filename, int startrow, int startcol, int sheetnum) {
		List<PageData> varList = new ArrayList<PageData>();

		try {
			File target = new File(filepath, filename);
			FileInputStream fi = new FileInputStream(target);
			HSSFWorkbook wb = new HSSFWorkbook(fi);
			HSSFSheet sheet = wb.getSheetAt(sheetnum); // sheet 从0开始
			int rowNum = sheet.getLastRowNum() + 1; // 取得最后一行的行号

			for (int i = startrow; i < rowNum; i++) { // 行循环开始

				PageData varpd = new PageData();
				HSSFRow row = sheet.getRow(i); // 行
				int cellNum = row.getLastCellNum(); // 每行的最后一个单元格位置

				for (int j = startcol; j < cellNum; j++) { // 列循环开始

					HSSFCell cell = row.getCell(Short.parseShort(j + ""));
					String cellValue = null;
					if (null != cell) {
						switch (cell.getCellType()) { // 判断excel单元格内容的格式，并对其进行转换，以便插入数据库
						case 0:
							cellValue = String.valueOf((int) cell.getNumericCellValue());
							break;
						case 1:
							cellValue = cell.getStringCellValue();
							break;
						case 2:
							cellValue = cell.getNumericCellValue() + "";
							// cellValue =
							// String.valueOf(cell.getDateCellValue());
							break;
						case 3:
							cellValue = "";
							break;
						case 4:
							cellValue = String.valueOf(cell.getBooleanCellValue());
							break;
						case 5:
							cellValue = String.valueOf(cell.getErrorCellValue());
							break;
						}
					} else {
						cellValue = "";
					}

					varpd.put("VALUE_CODE", cellValue);

				}
				varList.add(varpd);
			}

		} catch (Exception e) {
			System.out.println(e);
		}

		return varList;
	}
}
