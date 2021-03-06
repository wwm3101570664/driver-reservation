package com.briup.apps.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.bean.Announcement;
import com.briup.apps.bean.Coach;
import com.briup.apps.bean.extend.ArrangeTimeExtend;
import com.briup.apps.bean.extend.CarExtend;
import com.briup.apps.service.IManagerService;
import com.briup.apps.service.IUserService;
import com.briup.apps.utils.Excel;
import com.briup.apps.utils.ExcelUtils;
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
	@Autowired
	private IUserService userService;
	
	@GetMapping("check")
	public Message check(int coachId) {
		managerService.check(coachId);
		return MessageUtil.success("审核完成!!!");
	}
	
	//查询所有的预约安排（查看教练工作安排）
	@GetMapping("findAllArrange")
	public Message findAllArrange() {
		List<ArrangeTimeExtend> list = managerService.findAllArrange();
		return MessageUtil.success(list);
	}
	
	@ApiOperation(value = "添加教练")
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
	
	@GetMapping("findAllCoaches")
	public Message findAllCoaches() {
		List<CarExtend> findAll = userService.findAll();
		return MessageUtil.success(findAll);
	}
	
	 @ApiOperation(value="将文章导入到Excel中",notes="注意！测试的时候请将地址粘贴到浏览器地址栏测试",produces="application/octet-stream")
	 @GetMapping("download")
	 public Message download(HttpServletResponse response,HttpServletRequest request) throws Exception{
		 String excelName="Arrange";
		 String[] headList  = {"学员姓名","练车时间","车牌号","车辆类型"};
		 String[] fieldList = {"userName","date","carNum","carType"};
		 List<Map<String, Object>> dataList = new ArrayList<>();
			//从token中获取用户id
		 List<ArrangeTimeExtend> arrange = managerService.findAllArrange();
		 for (ArrangeTimeExtend arrangeTimeExtend : arrange) {
			Map<String,Object> map = new HashMap<>();
			map.put("userName", arrangeTimeExtend.getUser().getName());
			map.put("date", arrangeTimeExtend.getDate());
			map.put("carNum", arrangeTimeExtend.getCar().getCarNumber());
			map.put("carNum", arrangeTimeExtend.getCar().getType());
			dataList.add(map);
		}
		 //导出Excel
		Excel.createExcel(dataList, fieldList, headList);
		return MessageUtil.success("下载成功,请返回桌面查看");
	 }
	 
	 @PostMapping("pushAnnouncement")
	 public Message pushAnnouncement(String content) {
		 managerService.pushAnnouncement(content);
		 return MessageUtil.success("发布公告成功!!!");
	 }
	 
	 @GetMapping("findAllAnnouncement")
	 public Message findAllAnnouncement() {
		 List<Announcement> list = managerService.findAllAnnouncement();
		 return MessageUtil.success(list);
	 }
	
	
}
