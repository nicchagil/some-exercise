package com.nicchagil.util.excel.export;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.util.Assert;

import com.nicchagil.util.excel.PoiUtils;

public class WorkBookExportUtils {
	
	/**
	 * 根据配置对象写数据进入Workbook（默认第1个Sheet）
	 */
	public static Workbook writeWorkbookByConfigVo(Workbook wb, ExcelExportConfigVo configVo) {
		return WorkBookExportUtils.writeWorkbookByConfigVo(wb, PoiUtils.DEFAULT_SHEET_INDEX, configVo);
	}
	
	/**
	 * 根据配置对象写数据进入Workbook
	 */
	public static Workbook writeWorkbookByConfigVo(Workbook wb, Integer sheetIndex, ExcelExportConfigVo configVo) {
		Assert.notNull(configVo, "Excel写数配置对象为空");
		
		Sheet sheet = wb.getSheetAt(sheetIndex);
		// 将数据写入零散的单元格
		PoiUtils.writeScatteredCell(sheet, configVo.getScatteredCellList());
		// 写入批量数据
		PoiUtils.writeBatchData(sheet, configVo.getBatchDataListStartCellIndex(), configVo.getBatchDataList(), configVo.getBatchDataColumnKey());
		
		return wb;
	}
	
	/**
     * 将Excel以输出流形式导出到浏览器
     * @param workbook Excel信息
     * @param fileName 文件名
     * @param response HTTP J2EE响应
     */
    public static void exportForBrowserByTraditionalWay(Workbook workbook, String fileName, HttpServletResponse response) {
        /* 文件名编码 */
        try {
			fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("文件名编码转换异常", e);
		}
        
        /* HTTP头 */
        response.setContentType("application/x-msdownload");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        
        try {
	        OutputStream output = response.getOutputStream();
	        
	        /* 输出 */
	        try {
	            workbook.write(output);
	            output.flush();
	        } finally {
	            output.close();
	        }
        } catch (IOException e) {
			throw new RuntimeException("导出文件异常", e);
		}
    }

}
