package com.briup.apps.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.bean.Coach;
import com.briup.apps.bean.extend.ArrangeTimeExtend;
import com.briup.apps.service.IManagerService;
import com.briup.apps.utils.Message;
import com.briup.apps.utils.MessageUtil;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

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
	
	@ApiOperation(value = "选择教练")
	//paramType=form适用于表单提交和post
	@ApiImplicitParams({
		@ApiImplicitParam(name="name",value = "教练姓名",paramType = "query",required = true),
		@ApiImplicitParam(name="age",value = "教练年龄",paramType = "query",required = true,dataType = "int"),
		@ApiImplicitParam(name="gender",value = "性别",paramType = "query",required = true),
		@ApiImplicitParam(name="charges",value = "收费",paramType = "query",required = true),
		@ApiImplicitParam(name="password",value = "密码",paramType = "query",required = true),
		@ApiImplicitParam(name="carNum",value = "车牌号码",paramType = "query",required = true),
		@ApiImplicitParam(name="carType",value = "车辆类型",paramType = "query",required = true)
	})
	@GetMapping("addCoachAndCar")
	public Message addCoachAndCar(String name,Integer age,String gender,Integer charges,String password,String carNum,String carType) {
		managerService.addCoachAndCar(name, age, gender, charges, password, carNum, carType);
		return MessageUtil.success("添加教练成功!!!");
	}
	
	@GetMapping("findAll")
	public Message findAll() {
		List<Coach> list = managerService.findAll();
		return MessageUtil.success(list);
	}
	
}
