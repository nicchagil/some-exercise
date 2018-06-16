package com.nicchagil.util.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtils {
	
	private static Logger logger = LoggerFactory.getLogger(FileUtils.class);
	
	/**
	 * 将输入流的内容持久化到磁盘中
	 */
	public static void makeToDisk(InputStream is, String folderPath, String fileName) {
		/* 创建目录 */
		FileUtils.createFolderIfNotExist(folderPath);
		
		File file = new File(folderPath + fileName);
		
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			FileUtils.copy(is, fos, 1024);
			
			logger.info("success store to disk : {}{}", folderPath, fileName);
		} catch (IOException e) {
			throw new RuntimeException("转储文件异常", e);
		} finally {
			IOUtils.closeQuietly(is);
			IOUtils.closeQuietly(fos);
		}
	}
	
	/**
	 * 创建文件夹（支持级联创建，比如在D盘创建d:/test_workspace/2018/06/16）
	 */
	public static void createFolderIfNotExist(String folderPath) {
		File folder = new File(folderPath);
		
		if (!folder.exists()) {
			folder.mkdirs();
		}
	}
	
	/**
	 * 将输入流的内容写入到输出流
	 * 注意：1、IOException会抛出，请自行处理；2、输入流、输出流的关闭请自行处理
	 */
	public static void copy(InputStream is, OutputStream os, Integer bufferByteSize) throws IOException {
		byte[] bytes = null;
		if (bufferByteSize== null || bufferByteSize == 0) {
			bytes = new byte[1024];
		} else {
			bytes = new byte[bufferByteSize];
		}
		
		int readByteSize = 0;
		while ((readByteSize = is.read(bytes)) > 0) {
			os.write(bytes, 0, readByteSize);
		}
		
		os.flush();
	}
	
	@Test
	public void makeFolderTest() {
		FileUtils.createFolderIfNotExist("d:/test_workspace/2018/06/16");
	}

}
