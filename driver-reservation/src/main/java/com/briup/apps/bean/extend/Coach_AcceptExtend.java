package com.briup.apps.bean.extend;

import java.util.List;

import com.briup.apps.bean.Coach_Accept;
import com.briup.apps.bean.Role;

public class Coach_AcceptExtend extends Coach_Accept{
	private List<Role> roles;

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
}
