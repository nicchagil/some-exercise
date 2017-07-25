package com.nicchagil.messagelandinghandler;

import com.nicchagil.messagelandinghandler.framework.AbstractRemoteOpsHandler;

public class UserLandingLogOpsHandler extends AbstractRemoteOpsHandler<UserLandingLog> {
	
	private UserLandingLogDAO userLandingLogDAO = new UserLandingLogDAO();

	@Override
	public UserLandingLog doOps(UserLandingLog t) {
		this.userLandingLogDAO.updateById(t);
		return t;
	}

	@Override
	public String getPrintMsgIfRemoteOpsException() {
		return "UserLandingLog message landing exception(Update) : {}";
	}

}
