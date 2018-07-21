package com.nicchagil.util.service;

import org.springframework.stereotype.Service;

import com.nicchagil.orm.entity.User;
import com.nicchagil.orm.entity.UserExample;
import com.nicchagil.orm.mapper.UserMapper;
import com.nicchagil.util.service.base.BaseService;

@Service
public class CommonUserService extends BaseService<User, UserExample, UserMapper> {

}
