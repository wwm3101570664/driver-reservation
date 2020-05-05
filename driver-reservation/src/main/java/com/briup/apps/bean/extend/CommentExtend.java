package com.briup.apps.bean.extend;

import com.briup.apps.bean.Comment;
import com.briup.apps.bean.User;

public class CommentExtend extends Comment{
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	

}
