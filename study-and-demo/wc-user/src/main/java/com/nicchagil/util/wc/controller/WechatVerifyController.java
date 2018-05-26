package com.nicchagil.util.wc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nicchagil.util.wc.service.WechatVerifyService;
import com.nicchagil.vo.WecharVerifyVo;

@RestController
@RequestMapping(value = "/wechat/verify")
public class WechatVerifyController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private WechatVerifyService wechatVerifyService;
	
	@GetMapping
	public String verify(WecharVerifyVo wecharVerifyVo) {
		String echo = this.wechatVerifyService.verify(wecharVerifyVo);
		return echo;
	}

}
