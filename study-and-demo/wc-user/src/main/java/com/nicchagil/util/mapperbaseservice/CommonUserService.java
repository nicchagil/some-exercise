package com.nicchagil.util.mapperbaseservice;

import org.springframework.stereotype.Service;

import com.nicchagil.orm.entity.User;
import com.nicchagil.orm.entity.UserExample;
import com.nicchagil.orm.mapper.UserMapper;
import com.nicchagil.util.mapperbaseservice.base.MapperBaseService;

@Service
public class CommonUserService extends MapperBaseService<User, UserExample, UserMapper> {

}
