package com.briup.apps.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.bean.extend.ArrangeTimeExtend;
import com.briup.apps.service.IManagerService;
import com.briup.apps.utils.Message;
import com.briup.apps.utils.MessageUtil;

@RestController
@RequestMapping("/manager")
public class ManagerController {
	@Autowired
	private IManagerService managerService;
	
	@GetMapping("check")
	public Message check(int coachId) {
		managerService.check(coachId);
		return MessageUtil.success("审核完成!!!");
	}
	
	@GetMapping("findAllArrange")
	public Message findAllArrange() {
		List<ArrangeTimeExtend> list = managerService.findAllArrange();
		return MessageUtil.success(list);
	}
	
	@GetMapping("addCoachAndCar")
	public Message addCoachAndCar(String name,int age,String gender,int charges,String password,String carNum,String carType) {
		managerService.addCoachAndCar(name, age, gender, charges, password, carNum, carType);
		return MessageUtil.success("添加教练成功!!!");
	}
	
}
