package com.example.template.components.repository;

import com.example.template.dto.user.User;

public interface UserRepository {
	public User getUserById(String id);
}
