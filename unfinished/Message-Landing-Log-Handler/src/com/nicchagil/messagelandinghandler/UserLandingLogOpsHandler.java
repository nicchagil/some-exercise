package com.nicchagil.messagelandinghandler;

import com.nicchagil.messagelandinghandler.framework.AbstractRemoteOpsHandler;

/**
 * 更新落地日志
 */
public class UserLandingLogOpsHandler extends AbstractRemoteOpsHandler<UserLandingLog> {
	
	private UserLandingLogDAO userLandingLogDAO = new UserLandingLogDAO();

	/**
	 * 更新完成状态
	 */
	@Override
	public UserLandingLog doOps(UserLandingLog t) {
		this.userLandingLogDAO.updateById(t);
		return t;
	}

	/**
	 * 如果更新异常，打印本地日志
	 */
	@Override
	public String getPrintMsgIfRemoteOpsException() {
		return "UserLandingLog message landing exception(Update) : {}";
	}

	@Override
	public String getPrintMsgIfRemoteOpsComplete() {
		return "UserLandingLog message landing complete(Update) : {}";
	}
	
}
