package com.nicchagil.util.excel.export;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;

public class WorkBookExportUtils {
	
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
