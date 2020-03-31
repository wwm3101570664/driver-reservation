package com.briup.apps.service;

import java.util.List;

import com.briup.apps.bean.Coach;
import com.briup.apps.bean.extend.ArrangeTimeExtend;

public interface IManagerService {
	List<Coach> findAll();
	void check(int coachId);
	List<ArrangeTimeExtend> findAllArrange();
	void addCoachAndCar(String name,Integer age,String gender,Integer charges,String password,String carNum,String carType);
}
