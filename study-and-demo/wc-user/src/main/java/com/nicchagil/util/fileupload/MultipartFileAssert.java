package com.nicchagil.util.fileupload;

import org.apache.shiro.util.Assert;
import org.springframework.web.multipart.MultipartFile;

public class MultipartFileAssert {
	
	/**
	 * 非空断言
	 */
	public static void notEmpty(MultipartFile multipartFile) {
		Assert.notNull(multipartFile, "上传文件不能为空");
		Assert.isTrue(!multipartFile.isEmpty(), "上传文件不能为空");
	}
	
	/**
	 * 控制上传文件大小
	 */
	public static void sizeLimit(MultipartFile multipartFile, long sizeLimit, String msg) {
		long byteSize = multipartFile.getSize();
		Assert.isTrue(byteSize < sizeLimit, msg);
	}

}
