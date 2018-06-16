package com.nicchagil.util.fileupload.controller.demo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nicchagil.util.file.FileUtils;
import com.nicchagil.util.fileupload.MultipartFileAssert;

@RestController
@RequestMapping("/demo")
public class UploadFileController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());	
	
	@PostMapping("/fileUpload")
    public void fileUpload(@RequestParam("file") MultipartFile multipartFile) {
		MultipartFileAssert.notEmpty(multipartFile);
		MultipartFileAssert.sizeLimit(multipartFile, 5 * 1024 * 1024 * 1024, "请上传小于5M的文件");
		
		InputStream is = null;
		try {
			is = multipartFile.getInputStream();
			
			Calendar c = Calendar.getInstance();
			String folderPath = "d:/" + c.get(Calendar.YEAR) + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.DAY_OF_MONTH) + "/";
			
			FileUtils.makeToDisk(is, folderPath, multipartFile.getOriginalFilename() + "-" + c.getTimeInMillis());
		} catch (IOException e) {
			throw new RuntimeException("读取文件流异常");
		}
		
		logger.info("Upload file name : {}", multipartFile.getOriginalFilename());
    }
	
}
