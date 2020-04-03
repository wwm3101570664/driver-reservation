package com.briup.apps.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.service.IPrivilegeService;
import com.briup.apps.utils.Message;
import com.briup.apps.utils.MessageUtil;

@RestController
@RequestMapping("/privilege")
public class PrivilegeController {
	@Autowired
	private IPrivilegeService privilegeService;
	
	@GetMapping("insert")
	public Message insert(String path, String description, String type, int roleId) {
		privilegeService.insert(path, description, type, roleId);
		return MessageUtil.success("插入成功");
	}
}
