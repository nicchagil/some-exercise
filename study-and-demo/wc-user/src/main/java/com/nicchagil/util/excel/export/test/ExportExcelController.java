package com.nicchagil.util.excel.export.test;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.nicchagil.util.excel.export.ExcelExportConfigVo;
import com.nicchagil.util.excel.export.ExcelExportConfigVo.CellIndex;
import com.nicchagil.util.excel.export.PoiUtils;
import com.nicchagil.util.excel.export.WorkBookExportUtils;

@RestController
@RequestMapping("/test/excel")
public class ExportExcelController {
	
	@GetMapping("/map")
    public String test(HttpServletResponse response, String username, String password) {
    	List<User> list = Lists.newArrayList(new User(1, "Nick Huang"), new User(2, "Hello Kitty"));
    	
    	ExcelExportConfigVo configVo = new ExcelExportConfigVo();
    	configVo.setBatchDataColumnKey(new String[] {"id", "name"});
    	configVo.setBatchDataList(list);
    	configVo.setBatchDataListStartCellIndex(new CellIndex(3, 0));
    	
    	Workbook workbook = PoiUtils.loadWorkbookFromClasspath("/com/nicchagil/util/excel/export/TEMPLATE.XLS");
    	PoiUtils.writeWorkbookByConfigVo(workbook, configVo);
    	
    	WorkBookExportUtils.exportForBrowserByTraditionalWay(workbook, "用户信息表.xls", response);
        return "成功";
    }
	
	public static class User {
		private Integer id;
		private String name;
		public User(Integer id, String name) {
			super();
			this.id = id;
			this.name = name;
		}
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		
	}

}
