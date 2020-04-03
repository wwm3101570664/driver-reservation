package com.briup.apps.dao.extend;

import java.util.List;

import com.briup.apps.bean.User;
import com.briup.apps.bean.extend.UserExtend;

public interface UserExtendMapper {
	List<User> findAllUsers(int coachId);
	UserExtend findById(int id);
}
