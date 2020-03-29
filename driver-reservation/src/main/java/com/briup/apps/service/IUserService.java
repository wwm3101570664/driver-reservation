package com.briup.apps.service;

public interface IUserService {
	void insert(String name,int age,String gender,String password);
	void selectCoach(int id,int coachId);
	void binding(int id,String phoneNum,String email);
	void Booking(int userId);
	void deleteBooking(int userId);
}
