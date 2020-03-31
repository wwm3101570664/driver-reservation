package com.briup.apps.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.briup.apps.bean.ArrangeTime;
import com.briup.apps.bean.Car;
import com.briup.apps.bean.CarExample;
import com.briup.apps.bean.Coach;
import com.briup.apps.bean.Coach_Accept;
import com.briup.apps.bean.Coach_AcceptExample;
import com.briup.apps.bean.User;
import com.briup.apps.config.CustomerException;
import com.briup.apps.dao.CarMapper;
import com.briup.apps.dao.CoachMapper;
import com.briup.apps.dao.Coach_AcceptMapper;
import com.briup.apps.dao.extend.ArrangeTimeExtendMapper;
import com.briup.apps.dao.extend.UserExtendMapper;
import com.briup.apps.service.ICoachService;

@Service
public class CoachServiceImpl implements ICoachService{
	@Resource
	private CoachMapper coachMapper;
	@Resource
	private Coach_AcceptMapper coachAcceptMapper;
	@Resource
	private CarMapper carMapper;
	@Resource
	private ArrangeTimeExtendMapper arrangeExtendMapper;
	@Resource
	private UserExtendMapper userExtendMapper;
	
	
	//教练注册
	@Override
	public void insert(String name, int age, String gender, int charges, String password, String carNum,String carType) {
		Coach_AcceptExample example = new Coach_AcceptExample();
		example.createCriteria().andNameEqualTo(name);
		List<Coach_Accept> list = coachAcceptMapper.selectByExample(example);
		if(list.size()>0) {
			throw new CustomerException("用户名重复!!!");
		}else {
			Coach coach = new Coach();
			coach.setName(name);
			coach.setAge(age);
			coach.setGender(gender);
			coach.setCharges(charges);
			coach.setPassword(password);
			coach.setCarNumber(carNum);
			coach.setCarType(carType);
			coach.setStatus("待审核");
			coachMapper.insert(coach);
		}
		
	}

	//修改教练信息
	@Override
	public void saveOrUpdate(int coachId,int age,int charges,String password,String carNum,String carType) {
		Coach_Accept coach_Accept = coachAcceptMapper.selectByPrimaryKey(coachId);
		coach_Accept.setAge(age);
		coach_Accept.setCharges(charges);
		coach_Accept.setPassword(password);
		coachAcceptMapper.updateByPrimaryKey(coach_Accept);
		
		CarExample example = new CarExample();
		example.createCriteria().andCoachIdEqualTo(coachId);
		List<Car> list = carMapper.selectByExample(example );
		Car car = list.get(0);
		car.setCarNumber(carNum);
		car.setType(carType);
		carMapper.updateByPrimaryKey(car);
	}
	
	//查看教学安排
	@Override
	public List<ArrangeTime> findAllArrange(int coachId) {
		List<ArrangeTime> list = arrangeExtendMapper.findAllArrange(coachId);
		return list;
	}
	
	//查看所有学员信息
	@Override
	public List<User> findAllUsers(int coachId) {
		List<User> list = userExtendMapper.findAllUsers(coachId);
		return list;
	}

}
