package com.briup.apps.service;

import java.util.List;

import com.briup.apps.bean.ArrangeTime;
import com.briup.apps.bean.User;

public interface ICoachService {
	void insert(String name,int age,String gender,int charges,String password,String carNum,String carType);
	void saveOrUpdate(int coachId,int age,int charges,String password,String carNum,String carType);
	List<ArrangeTime> findAllArrange(int coachId);
	List<User> findAllUsers(int coachId);
}
