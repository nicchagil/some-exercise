package com.nicchagil.exercise.quickreturn.syncexecute;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;

import com.nicchagil.exercise.quickreturn.syncexecute.mapper.dao.AsyncTaskMapper;
import com.nicchagil.exercise.quickreturn.syncexecute.mapper.entity.AsyncTask;
import com.nicchagil.exercise.quickreturn.syncexecute.mapper.entity.AsyncTaskExample;
import com.nicchagil.exercise.quickreturn.syncexecute.mapper.entity.User;
import com.nicchagil.exercise.quickreturn.syncexecute.utils.JsonUtils;

public class QuickReturnService extends AbstractQuickReturnService<User, User, AsyncTask> {
	
	@Autowired
	private AsyncTaskMapper asyncTaskMapper;

	@Override
	public List<String> validate(User input) {
		// 暂时无校验逻辑
		return null;
	}

	@Override
	public AsyncTask quickSave(User input) {
		AsyncTask asyncTask = new AsyncTask();
		asyncTask.setType(1);
		asyncTask.setData(JsonUtils.toJson(input));
		asyncTask.setStatus(AsyncTaskStatusEnum.UNDO.ordinal());
		asyncTask.setCreateTime(new Date());
		asyncTask.setUpdateTime(new Date());
		
		/* 插入记录 */
		this.asyncTaskMapper.insert(asyncTask);
		return asyncTask;
	}

	@Override
	public User doAyncExecute(User input, AsyncTask task) {
		/* 模拟业务操作，需时一段时间 */
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public int postUpdate(User input, AsyncTask task) {
		AsyncTask asyncTaskCopy = new AsyncTask();
		task.setStatus(AsyncTaskStatusEnum.UNDO.ordinal());
		
		AsyncTaskExample example = new AsyncTaskExample();
		example.createCriteria().andIdEqualTo(task.getId());
		
		/* 根据ID更新状态 */
		return this.asyncTaskMapper.updateByExampleSelective(asyncTaskCopy, example);
	}
	
}
