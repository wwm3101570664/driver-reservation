package com.briup.apps.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.briup.apps.bean.Car;
import com.briup.apps.bean.CarExample;
import com.briup.apps.bean.Coach;
import com.briup.apps.bean.Coach_Accept;
import com.briup.apps.bean.Coach_AcceptExample;
import com.briup.apps.bean.extend.ArrangeTimeExtend;
import com.briup.apps.config.CustomerException;
import com.briup.apps.dao.CarMapper;
import com.briup.apps.dao.CoachMapper;
import com.briup.apps.dao.Coach_AcceptMapper;
import com.briup.apps.dao.extend.ArrangeTimeExtendMapper;
import com.briup.apps.service.IManagerService;

@Service
public class ManagerServiceImpl implements IManagerService{
	@Resource
	private CoachMapper coachMapper;
	@Resource
	private Coach_AcceptMapper coachAcceptMapper;
	@Resource
	private CarMapper carMapper;
	@Resource
	private ArrangeTimeExtendMapper arrangeExtendMapper;
	
	
	//审核教练的注册信息
	@Override
	public void check(int coachId) {
		Coach coach = coachMapper.selectByPrimaryKey(coachId);
		if(coach.getStatus().equals("已审核")) {
			throw new CustomerException("审核已完成，请不要重复操作!!!");
		}else {
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
				
				
				car.setCarNumber(coach.getCarNumber());
				car.setCoachId(list.get(0).getId());
				car.setType(coach.getCarType());
				carMapper.insert(car);
				
				coach.setStatus("已审核");
				coachMapper.updateByPrimaryKey(coach);
			}else {
				throw new CustomerException("信息重复!!!");
			}
		
		}
		
	}

	//查看所有教练训练计划
	@Override
	public List<ArrangeTimeExtend> findAllArrange() {
		List<ArrangeTimeExtend> list = arrangeExtendMapper.findAll();
		return list;
	}

	@Override
	public void addCoachAndCar(String name, int age, String gender, int charges, String password, String carNum,
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

}
