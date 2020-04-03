package com.briup.apps.bean.extend;

import java.util.List;

import com.briup.apps.bean.Role;
import com.briup.apps.bean.User;

public class UserExtend extends User{
	private List<Role> roles;

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}
