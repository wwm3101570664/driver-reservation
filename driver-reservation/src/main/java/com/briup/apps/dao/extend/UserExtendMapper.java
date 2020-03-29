package com.briup.apps.dao.extend;

import java.util.List;

import com.briup.apps.bean.User;

public interface UserExtendMapper {
	List<User> findAllUsers(int coachId);
}
