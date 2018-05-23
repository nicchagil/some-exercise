package com.nicchagil.scheduledjob.standalone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.nicchagil.service.AccessTokenService;

@Component
public class ScheduledJob {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AccessTokenService accessTokenService;
	
	@Scheduled(fixedDelay = 5000)
    public void fixTimeJob() {
        this.logger.info("定时获取WC访问令牌：{}", this.accessTokenService.getAccessToken());
    }

}
