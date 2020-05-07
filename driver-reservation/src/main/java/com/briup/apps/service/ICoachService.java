package com.briup.apps.service;

import java.util.List;

import com.briup.apps.bean.Coach_Accept;
import com.briup.apps.bean.User;
import com.briup.apps.bean.extend.ArrangeTimeExtend;
import com.briup.apps.bean.extend.CarExtend;
import com.briup.apps.bean.extend.Coach_AcceptExtend;

public interface ICoachService {
	void insert(String name,int age,String gender,int charges,String password,String carNum,String carType);
	void saveOrUpdate(int coachId,int age,int charges,String password,String carNum,String carType);
	List<ArrangeTimeExtend> findAllArrangeByCoachId(int coachId);
	List<User> findAllUsers(int coachId);
	Coach_Accept findByCoachId(int coachId);
	Coach_Accept login(String name,String password);
	Coach_AcceptExtend findById(int coachId);
	List<CarExtend> findCoachById(int coachId);
	void SendMessages(String phoneNum,String dateTime);
}
