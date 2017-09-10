package com.nicchagil.ioexercise.filenio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileCopier {
	
	private static Logger logger = LoggerFactory.getLogger(FileCopier.class);

	public static void main(String[] args) throws Exception {
		FileCopier.copy("d:/logs/mylog.log", "d:/logs/mylog.log.bak");
	}
	
	/**
	 * 指定文件拷贝到目标文件
	 */
	public static void copy(String fromPath, String toPath) throws Exception {
		try (RandomAccessFile readRandomAccessFile = new RandomAccessFile(fromPath, "r"); 
				RandomAccessFile writeRandomAccessFile = new RandomAccessFile(toPath, "rw")) { // 自动关闭
			
			FileChannel inputFileChannel = readRandomAccessFile.getChannel(); // 获取通道
			FileChannel outputFileChannel = writeRandomAccessFile.getChannel(); // 获取通道

			ByteBuffer byteBuffer = ByteBuffer.allocate(64); // 分配缓冲区大小
			int readNum = inputFileChannel.read(byteBuffer); // 写入缓冲区

			while (readNum != -1) { // 有数据可读
				byteBuffer.flip(); // 切换为读取缓冲区模式，等于“limit = position; position = 0; mark = -1;”

				while (byteBuffer.hasRemaining()) {
					outputFileChannel.write(byteBuffer);
				}

				byteBuffer.clear(); // 清除缓冲区内存，切换为写入缓冲区模式，等于“position = 0; limit = capacity; mark = -1;”
				readNum = inputFileChannel.read(byteBuffer); // 继续写入缓冲区
			}
			
		}
	}

}
