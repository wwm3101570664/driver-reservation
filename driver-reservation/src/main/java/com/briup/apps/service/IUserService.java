package com.briup.apps.service;



import java.util.Date;
import java.util.List;

import com.briup.apps.bean.Comment;
import com.briup.apps.bean.User;
import com.briup.apps.bean.extend.ArrangeTimeExtend;
import com.briup.apps.bean.extend.CarExtend;
import com.briup.apps.bean.extend.CommentExtend;
import com.briup.apps.bean.extend.UserExtend;

public interface IUserService {
	void insert(String name,int age,String gender,String password);
	void selectCoach(int id,int coachId);
	List<CarExtend> findAll();
	void saveOrUpdate(int id,String phoneNum,String email);
	void Booking(int userId,String date);
	void deleteBooking(int userId);
	CarExtend findMessages(int userId);
	void makeComments(int userId,String comment);
	List<CommentExtend> findComments(int coachId);
	User findByUserId(int userId);
	User login(String name,String password);
	UserExtend findUserById(int userId);
	User selectById(int id);
	List<ArrangeTimeExtend> findArrange(int userId);
}
