package com.josemiguelhyb.fastbank.mapper;

import com.josemiguelhyb.fastbank.model.User;
import com.josemiguelhyb.fastbank.dto.UserResponse;

public class UserMapper {
	public static UserResponse toResponse(User user) {
		return new UserResponse(user.getId(), user.getName(), user.getEmail());
	}
}
