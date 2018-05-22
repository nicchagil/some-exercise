package com.nicchagil.exercise.quickreturn.syncexecute.mapper.dao;

import com.nicchagil.exercise.quickreturn.syncexecute.mapper.entity.AsyncTask;
import com.nicchagil.exercise.quickreturn.syncexecute.mapper.entity.AsyncTaskExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AsyncTaskMapper {
    long countByExample(AsyncTaskExample example);

    int deleteByExample(AsyncTaskExample example);

    int insert(AsyncTask record);

    int insertSelective(AsyncTask record);

    List<AsyncTask> selectByExampleWithBLOBs(AsyncTaskExample example);

    List<AsyncTask> selectByExample(AsyncTaskExample example);

    int updateByExampleSelective(@Param("record") AsyncTask record, @Param("example") AsyncTaskExample example);

    int updateByExampleWithBLOBs(@Param("record") AsyncTask record, @Param("example") AsyncTaskExample example);

    int updateByExample(@Param("record") AsyncTask record, @Param("example") AsyncTaskExample example);
}