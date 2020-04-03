package com.briup.apps.dao.extend;

import java.util.List;

import com.briup.apps.bean.Privilege;

public interface PrivilegeExtendMapper {
	List<Privilege> slelectByRoleId(int roleId);
}
