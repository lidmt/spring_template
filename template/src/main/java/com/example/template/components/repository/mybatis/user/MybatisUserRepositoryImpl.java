package com.example.template.components.repository.mybatis.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.template.components.repository.UserRepository;
import com.example.template.components.repository.mybatis.mapper.user.UserMapper;
import com.example.template.dto.user.User;

@Repository
public class MybatisUserRepositoryImpl implements UserRepository{
	@Autowired UserMapper userMapper;
	
	public MybatisUserRepositoryImpl() {
		System.out.println("mybatisUserRepositoryImpl created");
	}
	
	@Override
	public User getUserById(String id) {
		return userMapper.getUserById(id);
	}
}
