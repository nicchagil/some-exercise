package com.nicchagil.util.easypoi;

import java.io.File;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.util.PoiPublicUtil;

public class Call {

	public static void main(String[] args) {
		ImportParams importParams = new ImportParams();
		importParams.setTitleRows(0);
		importParams.setHeadRows(1);

		List<User> userList = ExcelImportUtil.importExcel(new File(PoiPublicUtil.getWebRootPath("import/123.xlsx")),
				User.class, importParams);

		System.out.println("userList : " + userList);
		System.out.println("ReflectionToStringBuilder.toString : " + ReflectionToStringBuilder.toString(userList));
	}

}
