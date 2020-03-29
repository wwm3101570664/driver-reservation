package com.briup.apps.service;

import java.util.List;

import com.briup.apps.bean.extend.ArrangeTimeExtend;

public interface IManagerService {
	void check(int coachId);
	List<ArrangeTimeExtend> findAllArrange();
	void addCoachAndCar(String name,int age,String gender,int charges,String password,String carNum,String carType);
}
