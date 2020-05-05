package com.briup.apps.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.briup.apps.bean.Announcement;
import com.briup.apps.bean.Car;
import com.briup.apps.bean.CarExample;
import com.briup.apps.bean.Coach;
import com.briup.apps.bean.Coach_Accept;
import com.briup.apps.bean.Coach_AcceptExample;
import com.briup.apps.bean.Manager;
import com.briup.apps.bean.ManagerExample;
import com.briup.apps.bean.extend.ArrangeTimeExtend;
import com.briup.apps.bean.extend.ManagerExtend;
import com.briup.apps.config.CustomerException;
import com.briup.apps.dao.AnnouncementMapper;
import com.briup.apps.dao.CarMapper;
import com.briup.apps.dao.CoachMapper;
import com.briup.apps.dao.Coach_AcceptMapper;
import com.briup.apps.dao.ManagerMapper;
import com.briup.apps.dao.extend.AnnouncementExtendMapper;
import com.briup.apps.dao.extend.ArrangeTimeExtendMapper;
import com.briup.apps.dao.extend.CoachExtendMapper;
import com.briup.apps.dao.extend.ManagerExtendMapper;
import com.briup.apps.service.IManagerService;

@Service
public class ManagerServiceImpl implements IManagerService{
	@Resource
	private CoachMapper coachMapper;
	@Resource
	private CoachExtendMapper coachExtendMapper;
	@Resource
	private Coach_AcceptMapper coachAcceptMapper;
	@Resource
	private CarMapper carMapper;
	@Resource
	private ArrangeTimeExtendMapper arrangeExtendMapper;
	@Resource
	private ManagerMapper managerMapper;
	@Resource
	private ManagerExtendMapper managerExtendMapper;
	@Resource
	private AnnouncementExtendMapper announcementExtendMapper;
	@Resource
	private AnnouncementMapper announcementMapper;
	
	
	//审核教练的注册信息
	@Override
	public void check(int coachId) {
		Coach coach = coachMapper.selectByPrimaryKey(coachId);
		if(!coach.getStatus().equals("已审核")) {
			Coach_AcceptExample example = new Coach_AcceptExample();
			example.createCriteria().andNameEqualTo(coach.getName());
			List<Coach_Accept> list = coachAcceptMapper.selectByExample(example);
			if(list.size()<=0) {
					Coach_Accept coachAccept = new Coach_Accept();
					Car car = new Car();
					coachAccept.setName(coach.getName());
					coachAccept.setAge(coach.getAge());
					coachAccept.setGender(coach.getGender());
					coachAccept.setCharges(coach.getCharges());
					coachAccept.setPassword(coach.getPassword());
					coachAccept.setRoleId(3);
					coachAcceptMapper.insert(coachAccept);
					
					//把通过审核的数据重新查出来相应的id
					Coach_AcceptExample example2 = new Coach_AcceptExample();
					example2.createCriteria().andNameEqualTo(coachAccept.getName());
					//System.out.println("-------------"+coachAccept.getCharges());
					List<Coach_Accept> list2 = coachAcceptMapper.selectByExample(example2);
					//System.out.println("--------------------"+list2.get(0).getId());
					car.setCarNumber(coach.getCarNumber());
					car.setCoachId(list2.get(0).getId());
					car.setType(coach.getCarType());
					carMapper.insert(car);
					
					coach.setStatus("已审核");
					coachMapper.updateByPrimaryKey(coach);
				}else {
					throw new CustomerException("信息重复!!!");
					}
			}else {
				throw new CustomerException("审核已完成，请不要重复操作!!!");
				
			}
		
	}

	//查看所有教练训练计划
	@Override
	public List<ArrangeTimeExtend> findAllArrange() {
		List<ArrangeTimeExtend> list = arrangeExtendMapper.findAll();
		return list;
	}
	
	@Override
	public void addCoachAndCar(String name, Integer age, String gender, Integer charges, String password, String carNum,
			String carType) {
		Coach_AcceptExample example = new Coach_AcceptExample();
		example.createCriteria().andNameEqualTo(name);
		List<Coach_Accept> list1 = coachAcceptMapper.selectByExample(example);
		
		CarExample example2 = new CarExample();
		example2.createCriteria().andCarNumberEqualTo(carNum);
		List<Car> list2 = carMapper.selectByExample(example2);
		if(list1.size()>0 || list2.size()>0) {
			throw new CustomerException("信息重复!!!");
		}else {
		
		Coach_Accept coachAccept = new Coach_Accept();
		coachAccept.setName(name);
		coachAccept.setAge(age);
		coachAccept.setGender(gender);
		coachAccept.setCharges(charges);
		coachAccept.setPassword(password);
		coachAccept.setRoleId(3);
		coachAcceptMapper.insert(coachAccept);
		
		Coach_AcceptExample coachAcceptExample = new Coach_AcceptExample();
		coachAcceptExample.createCriteria().andNameEqualTo(name);
		List<Coach_Accept> coaches = coachAcceptMapper.selectByExample(coachAcceptExample);
		
		Car car = new Car();
		car.setCarNumber(carNum);
		car.setType(carType);
		car.setCoachId(coaches.get(0).getId());
		carMapper.insert(car);
		
		}
	}
	
	//查看所有教练的注册待审核信息
	@Override
	public List<Coach> findAll() {
		List<Coach> list = coachExtendMapper.findAll();
		return list;
	}

	@Override
	public Manager findByManagerId(int managerId) {
		Manager manager = managerMapper.selectByPrimaryKey(managerId);
		return manager;
	}
	
	//管理员登录
	@Override
	public Manager login(String name, String password) {
		ManagerExample example = new ManagerExample();
		example.createCriteria().andNameEqualTo(name).andPasswordEqualTo(password);
		List<Manager> list = managerMapper.selectByExample(example);
		if(list.size()<=0) {
			throw new CustomerException("管理员不存在!!!");
		}else {
			
			return list.get(0);
		}
	}
	
	//通过管理员Id查找管理员
	@Override
	public ManagerExtend findManagerById(int managerId) {
		ManagerExtend managerExtend = managerExtendMapper.findById(managerId);
		return managerExtend;
	}
	
	//发布公告
	@Override
	public void pushAnnouncement(String content) {
		Announcement ann = new Announcement();
		long currentTimeMillis = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = sdf.format(new Date(currentTimeMillis));
		ann.setContent(content);
		ann.setTime(format);
		announcementMapper.insert(ann);
	}
	
	//查询公告
	@Override
	public List<Announcement> findAllAnnouncement() {
		List<Announcement> list = announcementExtendMapper.findAll();
		return list;
	}
	
	
	

}
