package com.briup.apps.service;

import java.util.List;

import com.briup.apps.bean.Privilege;

public interface IPrivilegeService {
	List<Privilege> findByRoleId(int id);
	void insert(String path,String description,String type,int roleId);
}
