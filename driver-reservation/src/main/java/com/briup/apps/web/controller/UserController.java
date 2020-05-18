package com.briup.apps.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.bean.Coach_Accept;
import com.briup.apps.bean.Manager;
import com.briup.apps.bean.User;
import com.briup.apps.bean.extend.Coach_AcceptExtend;
import com.briup.apps.bean.extend.ManagerExtend;
import com.briup.apps.bean.extend.UserExtend;
import com.briup.apps.utils.JwtTokenUtil;
import com.briup.apps.service.ICoachService;
import com.briup.apps.service.IManagerService;
import com.briup.apps.service.IUserService;
import com.briup.apps.utils.Message;
import com.briup.apps.utils.MessageUtil;
import com.briup.apps.vm.UserVM;

import io.swagger.annotations.ApiOperation;

//这个控制器主要用来处理登录、获取用户权限、登出业务
@Validated
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private IUserService userService;
	@Autowired
	private ICoachService coachService;
	@Autowired
	private IManagerService managerService;
	
	@ApiOperation(value = "登录")
	@PostMapping("login")
	public Message login(@RequestBody UserVM userVM) {
		//1.验证用户身份
		int roleId = userVM.getRoleId();
		//每个角色中都要添加一个登陆模块
		if(roleId==1) {
			Manager manager = managerService.login(userVM.getUsername(), userVM.getPassword());
			//2.产生token,缓存起来
			String token = JwtTokenUtil.createJWT((long)(manager.getId()), manager.getName(),roleId);
	    	System.out.println(token);
	    	 // 3. 如果登录失败
	        Map<String,String> map = new HashMap<>();
//	        map.put("token","admin-token");
	        map.put("token", token);
	        return MessageUtil.success(map);
		}else if(roleId==2) {
			User user = userService.login(userVM.getUsername(), userVM.getPassword());
			String token = JwtTokenUtil.createJWT((long)(user.getId()), user.getName(),roleId);
	    	System.out.println(token);
	    	Map<String,String> map = new HashMap<>();
//	        map.put("token","admin-token");
	        map.put("token", token);
	        return MessageUtil.success(map);
		}else if(roleId==3) {
			Coach_Accept coach_Accept = coachService.login(userVM.getUsername(), userVM.getPassword());
			String token = JwtTokenUtil.createJWT((long)(coach_Accept.getId()), coach_Accept.getName(),roleId);
	    	System.out.println(token);
	    	Map<String,String> map = new HashMap<>();
//	        map.put("token","admin-token");
	        map.put("token", token);
	        return MessageUtil.success(map);
		}else {
			return MessageUtil.error("登录失败!!!");
		}
		
		//3.返回
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("token", "admin-tkoen");
//		return MessageUtil.success(map);
	}
	
	@ApiOperation(value = "退出登录")
	@PostMapping("logout")
	public Message logout() {
		//1.将缓存中的token移除
		//2.其他
		return MessageUtil.success("success");
	}
	
	@ApiOperation(value = "通过token获取用户信息")
	@GetMapping("info")
	public Message info(String token) {
		 // 1. 通过token获取用户信息  {id,use,gender,roles:[]}
//      UserExtend baseUserExtend = baseUserService.findById(1l);
		long id = Long.parseLong(JwtTokenUtil.getUserId(token,JwtTokenUtil.base64Secret));
		System.out.println("---------"+id);
		long roleId = Long.parseLong(JwtTokenUtil.getRoleId(token,JwtTokenUtil.base64Secret));
		System.out.println("========"+roleId);
		if(roleId==1) {
			ManagerExtend managerExtend = managerService.findManagerById((int)id);
			return MessageUtil.success(managerExtend);
		}else if(roleId==2) {
			UserExtend userExtend = userService.findUserById((int)id);
			return MessageUtil.success(userExtend);
		}else if(roleId==3) {
			Coach_AcceptExtend coach_AcceptExtend = coachService.findById((int)id);
			return MessageUtil.success(coach_AcceptExtend); 
		}
		return MessageUtil.success("用户不存在!!!"); 
	}
}
