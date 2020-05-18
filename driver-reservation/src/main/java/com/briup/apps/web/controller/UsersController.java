package com.briup.apps.web.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.bean.Comment;
import com.briup.apps.bean.User;
import com.briup.apps.bean.extend.ArrangeTimeExtend;
import com.briup.apps.bean.extend.CarExtend;
import com.briup.apps.bean.extend.CommentExtend;
import com.briup.apps.service.IUserService;
import com.briup.apps.utils.JwtTokenUtil;
import com.briup.apps.utils.Message;
import com.briup.apps.utils.MessageUtil;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/users")
public class UsersController {
	@Autowired
	private IUserService userService;
	
	@ApiOperation(value = "学员注册")
	//paramType=form适用于表单提交和post
	@ApiImplicitParams({
		//@ApiImplicitParam(name="id",value = "用户id",paramType = "query",required = true ,dataType = "int"),
		@ApiImplicitParam(name="name",value = "姓名",paramType = "query",required = true,dataType = "string"),
		@ApiImplicitParam(name="age",value = "年龄",paramType = "query",required = true,dataType = "int"),
		@ApiImplicitParam(name="gender",value = "性别",paramType = "query",required = true,dataType = "string"),
		@ApiImplicitParam(name="password",value = "密码",paramType = "query",required = true,dataType = "string"),
	})
	@PostMapping("register")
	public Message register(String name,int age,String gender,String password) {
		userService.insert(name, age, gender, password);
		return MessageUtil.success("注册成功!!!");
	}
	
	@PostMapping("saveOrUpdate")
	public Message binding(HttpServletRequest request,String phonenum,String email) {
		final String token = request.getHeader("X-Token");
		//从token中获取用户id
		int id = (int) Long.parseLong(JwtTokenUtil.getUserId(token,JwtTokenUtil.base64Secret));
		userService.saveOrUpdate(id,phonenum, email);
		return MessageUtil.success("绑定信息成功!!!");
	}
	
	@ApiOperation(value = "选择教练")
	//paramType=form适用于表单提交和post
	@ApiImplicitParams({
		//@ApiImplicitParam(name="id",value = "用户id",paramType = "query",required = true ,dataType = "int"),
		@ApiImplicitParam(name="coachId",value = "教练id",paramType = "query",required = true,dataType = "int")
	})
	@GetMapping("selectCoach")
	public Message selectCoach(int coachId,HttpServletRequest request) {
		final String token = request.getHeader("X-Token");
		//从token中获取用户id
		int id = (int) Long.parseLong(JwtTokenUtil.getUserId(token,JwtTokenUtil.base64Secret));
		userService.selectCoach(id,coachId);
		return MessageUtil.success("选择教练成功");
	}
	
	@ApiOperation(value = "选择预约练车时间")
	@ApiImplicitParams({
		@ApiImplicitParam(name="date",value = "练车时间",paramType = "query",required = true,dataType = "Date")
	})
	@PostMapping("booking")
	public Message booking(String date,HttpServletRequest request) {
		System.out.println("练车日期选择："+date);
		final String token = request.getHeader("X-Token");
		//从token中获取用户id
		int id = (int) Long.parseLong(JwtTokenUtil.getUserId(token,JwtTokenUtil.base64Secret));
		userService.Booking(id,date);
		System.out.println("预约时间"+date);
		return MessageUtil.success("预约成功!!!");
	}
	
	@GetMapping("deleteBooking")
	public Message deleteBooking(HttpServletRequest request) {
		final String token = request.getHeader("X-Token");
		//从token中获取用户id
		int id = (int) Long.parseLong(JwtTokenUtil.getUserId(token,JwtTokenUtil.base64Secret));
		userService.deleteBooking(id);
		return MessageUtil.success("删除预约成功!!!");
	}
	
	@GetMapping("findMessages")
	public Message findMessages(HttpServletRequest request) {
		final String token = request.getHeader("X-Token");
		//从token中获取用户id
		int id = (int) Long.parseLong(JwtTokenUtil.getUserId(token,JwtTokenUtil.base64Secret));
		CarExtend carExtend = userService.findMessages(id);
		return MessageUtil.success(carExtend);
	}
	
	@PostMapping("makeComment")
	public Message makeComment(String content,HttpServletRequest request) {
		final String token = request.getHeader("X-Token");
		//从token中获取用户id
		int id = (int) Long.parseLong(JwtTokenUtil.getUserId(token,JwtTokenUtil.base64Secret));
		userService.makeComments(id, content);
		return MessageUtil.success("评论成功!!!");
	}
	
	@GetMapping("findComment")
	public Message findComment(int coachId) {
		List<CommentExtend> comments = userService.findComments(coachId);
		return MessageUtil.success(comments);
	}
	
	@GetMapping("findAll")
	public Message findAll() {
		List<CarExtend> list = userService.findAll();
		return MessageUtil.success(list);
	}
	
	@GetMapping("finduser")
	public Message selectById(HttpServletRequest request) {
		final String token = request.getHeader("X-Token");
		//从token中获取用户id
		int id = (int) Long.parseLong(JwtTokenUtil.getUserId(token,JwtTokenUtil.base64Secret));
		User user = userService.selectById(id);
		return MessageUtil.success(user);
	}
	
	@GetMapping("findArrange")
	public Message findArrangeById(HttpServletRequest request) {
		final String token = request.getHeader("X-Token");
		//从token中获取用户id
		int id = (int) Long.parseLong(JwtTokenUtil.getUserId(token,JwtTokenUtil.base64Secret));
		List<ArrangeTimeExtend> list = userService.findArrange(id);
		return MessageUtil.success(list);
		
	}
	
}
