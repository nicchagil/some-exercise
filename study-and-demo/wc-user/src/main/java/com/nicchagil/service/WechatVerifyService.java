package com.nicchagil.service;

import java.util.List;

import org.apache.shiro.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.nicchagil.constant.WcConstants;
import com.nicchagil.util.DictOrderUtils;
import com.nicchagil.util.Sha1Utils;
import com.nicchagil.vo.WecharVerifyVo;

@Service
public class WechatVerifyService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 验证微信服务器（不通过返回空字符串）
	 */
	public String verify(WecharVerifyVo wecharVerifyVo) {
		this.logger.info("wecharVerifyVo : {}", wecharVerifyVo);
		Assert.notNull(wecharVerifyVo, "入参不能为空");
		Assert.hasText(wecharVerifyVo.getNonce(), "随机数不能为空");
		Assert.hasText(wecharVerifyVo.getTimestamp(), "时间戳不能为空");
		Assert.hasText(wecharVerifyVo.getSignature(), "签名不能为空");
		Assert.hasText(wecharVerifyVo.getEchostr(), "响应字符串不能为空");
		
		/* 排字典序 */
		String token = WcConstants.VERIFY_SERVER_TOKEN;
		List<String> sourceList = Lists.newArrayList(token, wecharVerifyVo.getNonce(), wecharVerifyVo.getTimestamp());
		sourceList = DictOrderUtils.dictOrder(sourceList);
		
		/* 源字符 */
		String source = Joiner.on(StringUtils.EMPTY_STRING).join(sourceList);
		this.logger.info("source : {}", source);
		
		/* 摘要信息 */
		String sha1Result = Sha1Utils.encode(source);
		this.logger.info("sha1Result : {}", sha1Result);
		
		if (sha1Result.equals(wecharVerifyVo.getSignature())) {
			this.logger.info("match : {}", true);
			return wecharVerifyVo.getEchostr();
		} else {
			this.logger.info("match : {}", false);
			return StringUtils.EMPTY_STRING;
		}
	}

}
