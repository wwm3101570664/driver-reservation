package com.briup.apps.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.bean.User;
import com.briup.apps.bean.extend.ArrangeTimeExtend;
import com.briup.apps.bean.extend.CarExtend;
import com.briup.apps.bean.extend.Coach_AcceptExtend;
import com.briup.apps.service.ICoachService;
import com.briup.apps.utils.ExcelUtils;
import com.briup.apps.utils.JwtTokenUtil;
import com.briup.apps.utils.Message;
import com.briup.apps.utils.MessageUtil;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/coach")
public class CoachController {
	@Autowired
	private ICoachService coachService;
	
	@PostMapping("register")
	public Message register(String name, int age, String gender, int charges, String password, String carNum,String carType) {
		coachService.insert(name, age, gender, charges, password, carNum, carType);
		return MessageUtil.success("提交成功，等待管理员审核!!!");
	}
	
	@PostMapping("saveOrUpdate")
	public Message saveOrUpdate(HttpServletRequest request,int age,int charges,String password,String carNum,String carType) {
		final String token = request.getHeader("X-Token");
		//从token中获取用户id
		int id = (int) Long.parseLong(JwtTokenUtil.getUserId(token,JwtTokenUtil.base64Secret));
		coachService.saveOrUpdate(id, age, charges, password, carNum, carType);
		return MessageUtil.success("修改成功!!!");
	}
	
	@GetMapping("findAllArrangeByCoachId")
	public Message findAllArrange(HttpServletRequest request) {
		final String token = request.getHeader("X-Token");
		//从token中获取用户id
		int id = (int) Long.parseLong(JwtTokenUtil.getUserId(token,JwtTokenUtil.base64Secret));
		List<ArrangeTimeExtend> list = coachService.findAllArrangeByCoachId(id);
		return MessageUtil.success(list);
	}
	
	 @ApiOperation(value="将文章导入到Excel中",notes="注意！测试的时候请将地址粘贴到浏览器地址栏测试",produces="application/octet-stream")
	 @GetMapping("download")
	 public void download(HttpServletResponse response,HttpServletRequest request) throws Exception{
		 String excelName="Arrange";
		 String[] headList  = {"学员姓名","开始时间","结束时间"};
		 String[] fieldList = {"userName","starttime","endtime","carNum","carType"};
		 List<Map<String, Object>> dataList = new ArrayList<>();
		 final String token = request.getHeader("X-Token");
			//从token中获取用户id
			int id = (int) Long.parseLong(JwtTokenUtil.getUserId(token,JwtTokenUtil.base64Secret));
		 List<ArrangeTimeExtend> arrange = coachService.findAllArrangeByCoachId(id);
		 for (ArrangeTimeExtend arrangeTimeExtend : arrange) {
			Map<String,Object> map = new HashMap<>();
			map.put("userName", arrangeTimeExtend.getUser().getName());
			map.put("date", arrangeTimeExtend.getDate());
			map.put("carNum", arrangeTimeExtend.getCar().getCarNumber());
			map.put("carNum", arrangeTimeExtend.getCar().getType());
			dataList.add(map);
		}
		 //导出Excel
		 ExcelUtils.createExcel(response, excelName, headList, fieldList, dataList);
	 }
	
	@GetMapping("findAllUsers")
	public Message findAllUsers(HttpServletRequest request) {
		final String token = request.getHeader("X-Token");
		//从token中获取用户id
		int id = (int) Long.parseLong(JwtTokenUtil.getUserId(token,JwtTokenUtil.base64Secret));
		List<User> list = coachService.findAllUsers(id);
		return MessageUtil.success(list);
	}
	
	@GetMapping("findCoach")
	public Message findCoach(HttpServletRequest request) {
		final String token = request.getHeader("X-Token");
		//从token中获取用户id
		int id = (int) Long.parseLong(JwtTokenUtil.getUserId(token,JwtTokenUtil.base64Secret));
		List<CarExtend> list = coachService.findCoachById(id);
		return MessageUtil.success(list);
	}
	
}
