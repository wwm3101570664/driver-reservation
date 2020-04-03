package com.briup.apps.bean.extend;

import java.util.List;

import com.briup.apps.bean.Manager;
import com.briup.apps.bean.Role;

public class ManagerExtend extends Manager{
	private List<Role> roles;

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}
