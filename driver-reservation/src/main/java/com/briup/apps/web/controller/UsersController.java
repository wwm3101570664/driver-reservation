package com.briup.apps.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.bean.Coach_Accept;
import com.briup.apps.bean.Comment;
import com.briup.apps.bean.extend.CarExtend;
import com.briup.apps.service.IUserService;
import com.briup.apps.utils.Message;
import com.briup.apps.utils.MessageUtil;

@RestController
@RequestMapping("/users")
public class UsersController {
	@Autowired
	private IUserService userService;
	
	@GetMapping("register")
	public Message register(String name,int age,String gender,String password) {
		userService.insert(name, age, gender, password);
		return MessageUtil.success("注册成功!!!");
	}
	
	@GetMapping("binding")
	public Message binding(int id,String phoneNum,String email) {
		userService.binding(id, phoneNum, email);
		return MessageUtil.success("绑定信息成功!!!");
	}
	
	@GetMapping("selectCoach")
	public Message selectCoach(int id,int coachId) {
		userService.selectCoach(id,coachId);
		return MessageUtil.success("选择教练成功");
	}
	
	@GetMapping("booking")
	public Message booking(int userId) {
		userService.Booking(userId);
		return MessageUtil.success("预约成功!!!");
	}
	
	@DeleteMapping("deleteBooking")
	public Message deleteBooking(int userId) {
		userService.deleteBooking(userId);
		return MessageUtil.success("删除预约成功!!!");
	}
	
	@GetMapping("findMessages")
	public Message findMessages(int userId) {
		CarExtend carExtend = userService.findMessages(userId);
		return MessageUtil.success(carExtend);
	}
	
	@GetMapping("makeComment")
	public Message makeComment(int userId,String comment) {
		userService.makeComments(userId, comment);
		return MessageUtil.success("评论成功!!!");
	}
	
	@GetMapping("findComment")
	public Message findComment(int coachId) {
		List<Comment> comments = userService.findComments(coachId);
		return MessageUtil.success(comments);
	}
	
	@GetMapping("findAll")
	public Message findAll() {
		List<Coach_Accept> list = userService.findAll();
		System.out.println(list.get(0).getRoleId());
		return MessageUtil.success(list);
	}
	
}
