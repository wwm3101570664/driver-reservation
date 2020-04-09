package com.briup.apps.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.bean.Comment;
import com.briup.apps.bean.User;
import com.briup.apps.bean.extend.CarExtend;
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
	public Message findMessages(int coachId) {
		CarExtend carExtend = userService.findMessages(coachId);
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
		List<CarExtend> list = userService.findAll();
		return MessageUtil.success(list);
	}
	
	@GetMapping("finduser")
	public Message selectById(int id) {
		User user = userService.selectById(id);
		return MessageUtil.success(user);
	}
	
}
