package com.briup.apps.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.briup.apps.bean.Privilege;
import com.briup.apps.dao.PrivilegeMapper;
import com.briup.apps.dao.extend.PrivilegeExtendMapper;
import com.briup.apps.service.IPrivilegeService;

@Service
public class PrivilegeServiceImpl implements IPrivilegeService{
	@Resource
	private PrivilegeExtendMapper privilegeExtendMapper;
	@Resource
	private PrivilegeMapper privilegeMapper;

	@Override
	public List<Privilege> findByRoleId(int id) {
		List<Privilege> list = privilegeExtendMapper.slelectByRoleId(id);
		return list;
	}

	@Override
	public void insert(String path, String description, String type, int roleId) {
		Privilege p = new Privilege();
		p.setPath(path);
		p.setDescription(description);
		p.setType(type);
		p.setRoleId(roleId);
		privilegeMapper.insert(p);
		
	}

}
