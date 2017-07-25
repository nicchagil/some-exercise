package com.nicchagil.messagelandinghandler.framework;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nicchagil.messagelandinghandler.UserLandingLog;
import com.nicchagil.messagelandinghandler.UserLandingLogDAO;
import com.nicchagil.util.JSONUtil;

/**
 * 消息落地处理器
 */
public class MessageLandingHandler<T> extends AbstractRemoteOpsHandler<UserLandingLog> {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private UserLandingLogDAO userLandingLogDAO = new UserLandingLogDAO();
	
	public UserLandingLog landing(T t) {
		try {
			UserLandingLog log = new UserLandingLog();
			log.setMessage(JSONUtil.toJSON(t));
			log.setStatus(BusinessStatus.UNDO.getCode());
			log.setCreateTime(new Date());
			log.setUpdateTime(new Date());
			
			return this.ops(log);
		} catch (Exception e) {
			this.logger.error(this.getPrintMsgIfRemoteOpsException(), t);
			throw new RuntimeException(this.getPrintMsgIfRemoteOpsException() + t);
		}
	}

	@Override
	public UserLandingLog doOps(UserLandingLog t) {
		return this.userLandingLogDAO.save(t);
	}

	@Override
	public String getPrintMsgIfRemoteOpsException() {
		return "UserLandingLog message landing exception(Insert) : {}";
	}
	
}
