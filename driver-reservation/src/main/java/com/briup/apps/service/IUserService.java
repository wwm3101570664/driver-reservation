package com.briup.apps.service;

import java.util.List;

import com.briup.apps.bean.Comment;
import com.briup.apps.bean.extend.CarExtend;

public interface IUserService {
	void insert(String name,int age,String gender,String password);
	void selectCoach(int id,int coachId);
	List<CarExtend> findAll();
	void binding(int id,String phoneNum,String email);
	void Booking(int userId);
	void deleteBooking(int userId);
	CarExtend findMessages(int coachId);
	void makeComments(int userId,String comment);
	List<Comment> findComments(int coachId);
}
