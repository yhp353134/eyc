package com.fh.controller.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fh.controller.base.BaseController;
import com.fh.service.common.CommonService;
import com.fh.util.ObjectExcelRead;

/**
 * <p>ClassName: CommonController</p>
 * <p>Description: </p>
 * <p>Author: MEpaper</p>
 * <p>Date: 2017年3月20日</p>
 */
@Controller
@RequestMapping("/common/")
public class CommonController extends BaseController{
    
    @Autowired
    private CommonService commonService;

    /**
     * <p>
     * Description: 解析导入vim车架号，
     * 车架号只有一列，如果多列也只取第一列的数据
     * </p>
     * @param vinFile 车架号excel文件
     * @param busId 业务点
     * @param busPoint 业务点
     * @return json（returnCode为-1表示解析失败，0表示成功，errorMsg失败信息）
     */
    @SuppressWarnings("resource")
	@RequestMapping("impVinExcel")
    @ResponseBody
    public JSONObject impVimExp(
            @RequestParam(value="vinFile",required=false) MultipartFile vinFile, 
            @RequestParam(value="busId",required=false, defaultValue="") String busId,
            @RequestParam(value="busPoint",required=false, defaultValue="0") Integer busPoint
            ) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("returnCode", -1);
        if(vinFile.isEmpty()){
            jsonObject.put("returnCode", -2);
        }
        InputStream is = null;
        try {
            /** 判断文件的类型，是2003还是2007 */
            /** 验证文件是否合法 */
            if (!(ObjectExcelRead.isExcel2003(vinFile.getOriginalFilename())
                    || ObjectExcelRead.isExcel2007(vinFile.getOriginalFilename()))) {
                jsonObject.put("errorMsg", "文件不是excel！");
                return jsonObject;
            }
            boolean isExcel2003 = true;
            if(ObjectExcelRead.isExcel2007(vinFile.getOriginalFilename())){
                isExcel2003 = false;
            }
            
            is = vinFile.getInputStream();
            Workbook wb = null;
            if (isExcel2003) {
                wb = new HSSFWorkbook(is);
            } else {
                wb = new XSSFWorkbook(is);
            }
            // 工作表
            Sheet sheet = wb.getSheetAt(0);
            // 得到Excel的行数 
            int totalRows = sheet.getPhysicalNumberOfRows();
            // 循环Excel的行 
            List<String> vinList = new ArrayList<String>();
            String errorRows = ""; // 数据类型错误行数
            int successRowNum = totalRows;
            for (int r = 0; r < totalRows; r++) {
                Row row = sheet.getRow(r);
                if (row == null) {
                    continue;
                }
                Cell cell = row.getCell(0);
                if (null != cell) {
                    // 以下是判断数据的类型
                    int cellType = cell.getCellType();
                    if(cellType == HSSFCell.CELL_TYPE_STRING || cellType == HSSFCell.CELL_TYPE_NUMERIC){
                        vinList.add(cell.getNumericCellValue() + "");
                    }else{
                        successRowNum--;
                        errorRows += r;
                    }
                }
            }
            jsonObject.put("successRowNum", successRowNum);
            jsonObject.put("errorRows", errorRows);
            jsonObject.put("vinList", vinList); 
        } catch (IOException ioe) {
            throw new Exception("解析excel失败");
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    is = null;
                    e.printStackTrace();
                }
            }
        }
        jsonObject.put("returnCode", 0);
        return jsonObject;
    }
    
    /**
     * <p>Description: 通过从数据库获取的相对路径，保存该路径下的图片文件路径信息到TOOL_SRC_IMG表</p>
     * @return
     * @throws Exception
     */
    @RequestMapping("saveLocalFilePath")
    @ResponseBody
    public JSONObject saveLocalFilePath() throws Exception {
        JSONObject json = new JSONObject();
        int count = this.commonService.saveLocalFilePath();
        json.put("returnCode", 0);
        if(count == -1){
            json.put("msg", "文件夹下没有文件！");
        }
        json.put("saveFilePathCount", count);
        return json;
    }
    
}
