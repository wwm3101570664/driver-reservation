package com.briup.apps.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.bean.ArrangeTime;
import com.briup.apps.bean.User;
import com.briup.apps.service.ICoachService;
import com.briup.apps.utils.Message;
import com.briup.apps.utils.MessageUtil;

@RestController
@RequestMapping("/coach")
public class CoachController {
	@Autowired
	private ICoachService coachService;
	
	@GetMapping("register")
	public Message register(String name, int age, String gender, int charges, String password, String carNum,String carType) {
		coachService.insert(name, age, gender, charges, password, carNum, carType);
		return MessageUtil.success("提交成功，等待管理员审核!!!");
	}
	
	@GetMapping("saveOrUpdate")
	public Message saveOrUpdate(int coachId,int age,int charges,String password,String carNum,String carType) {
		coachService.saveOrUpdate(coachId, age, charges, password, carNum, carType);
		return MessageUtil.success("修改成功!!!");
	}
	
	@GetMapping("findAllArrange")
	public Message findAllArrange(int coachId) {
		List<ArrangeTime> list = coachService.findAllArrange(coachId);
		return MessageUtil.success(list);
	}
	
	@GetMapping("findAllUsers")
	public Message findAllUsers(int coachId) {
		List<User> list = coachService.findAllUsers(coachId);
		return MessageUtil.success(list);
	}
	
}
