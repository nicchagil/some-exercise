package com.nicchagil.exercise.wcuser.thirdpartyframework.jackson.swagger.json;

import java.io.IOException;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nicchagil.util.swagger.json.ParseSwaggerJsonUtils;
import com.nicchagil.util.swagger.json.ParseSwaggerJsonUtils.ApiVo;

public class ParseSwaggerJsonTest {
	
	private static Logger logger = LoggerFactory.getLogger(ParseSwaggerJsonTest.class);
	
	@Test
	public void testx1() throws JsonProcessingException, IOException {
		String jsonStr = "{\"swagger\":\"2.0\",\"info\":{\"description\":\"The document desc the api of the project.\",\"version\":\"1.0\",\"title\":\"WC-USER API Document\"},\"host\":\"127.0.0.1\",\"basePath\":\"/\",\"tags\":[{\"name\":\"export-excel-controller\",\"description\":\"Export Excel Controller\"},{\"name\":\"import-parse-controller\",\"description\":\"Import Parse Controller\"},{\"name\":\"upload-file-controller\",\"description\":\"Upload File Controller\"},{\"name\":\"wechat-msg-controller\",\"description\":\"Wechat Msg Controller\"}],\"paths\":{\"/demo/fileUpload\":{\"post\":{\"tags\":[\"upload-file-controller\"],\"summary\":\"fileUpload\",\"operationId\":\"fileUploadUsingPOST_1\",\"consumes\":[\"multipart/form-data\"],\"produces\":[\"*/*\"],\"parameters\":[{\"name\":\"file\",\"in\":\"formData\",\"description\":\"file\",\"required\":true,\"type\":\"file\"}],\"responses\":{\"200\":{\"description\":\"OK\"},\"201\":{\"description\":\"Created\"},\"401\":{\"description\":\"Unauthorized\"},\"403\":{\"description\":\"Forbidden\"},\"404\":{\"description\":\"Not Found\"}}}},\"/test/excel/import\":{\"post\":{\"tags\":[\"import-parse-controller\"],\"summary\":\"fileUpload\",\"operationId\":\"fileUploadUsingPOST\",\"consumes\":[\"multipart/form-data\"],\"produces\":[\"*/*\"],\"parameters\":[{\"name\":\"file\",\"in\":\"formData\",\"description\":\"file\",\"required\":true,\"type\":\"file\"}],\"responses\":{\"200\":{\"description\":\"OK\"},\"201\":{\"description\":\"Created\"},\"401\":{\"description\":\"Unauthorized\"},\"403\":{\"description\":\"Forbidden\"},\"404\":{\"description\":\"Not Found\"}}}},\"/test/excel/map\":{\"get\":{\"tags\":[\"export-excel-controller\"],\"summary\":\"test\",\"operationId\":\"testUsingGET\",\"produces\":[\"*/*\"],\"parameters\":[{\"name\":\"username\",\"in\":\"query\",\"description\":\"username\",\"required\":false,\"type\":\"string\"},{\"name\":\"password\",\"in\":\"query\",\"description\":\"password\",\"required\":false,\"type\":\"string\"}],\"responses\":{\"200\":{\"description\":\"OK\",\"schema\":{\"type\":\"string\"}},\"401\":{\"description\":\"Unauthorized\"},\"403\":{\"description\":\"Forbidden\"},\"404\":{\"description\":\"Not Found\"}}}},\"/wechat/handle\":{\"get\":{\"tags\":[\"wechat-msg-controller\"],\"summary\":\"verify\",\"operationId\":\"verifyUsingGET\",\"produces\":[\"*/*\"],\"parameters\":[{\"name\":\"signature\",\"in\":\"query\",\"required\":false,\"type\":\"string\"},{\"name\":\"timestamp\",\"in\":\"query\",\"required\":false,\"type\":\"string\"},{\"name\":\"nonce\",\"in\":\"query\",\"required\":false,\"type\":\"string\"},{\"name\":\"echostr\",\"in\":\"query\",\"required\":false,\"type\":\"string\"},{\"name\":\"token\",\"in\":\"query\",\"required\":false,\"type\":\"string\"}],\"responses\":{\"200\":{\"description\":\"OK\",\"schema\":{\"type\":\"string\"}},\"401\":{\"description\":\"Unauthorized\"},\"403\":{\"description\":\"Forbidden\"},\"404\":{\"description\":\"Not Found\"}}},\"post\":{\"tags\":[\"wechat-msg-controller\"],\"summary\":\"receiveAndHandleMsg\",\"operationId\":\"receiveAndHandleMsgUsingPOST\",\"consumes\":[\"application/json\"],\"produces\":[\"*/*\"],\"responses\":{\"200\":{\"description\":\"OK\",\"schema\":{\"type\":\"string\"}},\"201\":{\"description\":\"Created\"},\"401\":{\"description\":\"Unauthorized\"},\"403\":{\"description\":\"Forbidden\"},\"404\":{\"description\":\"Not Found\"}}}}}}";
		
		List<ApiVo> apiVoList = ParseSwaggerJsonUtils.getHttpApiBySwaggerJson(jsonStr);
		if (CollectionUtils.isNotEmpty(apiVoList)) {
			for (ApiVo apiVo : apiVoList) {
				logger.info("apiVo : {}", apiVo);
			}
		}
	}
	
}
