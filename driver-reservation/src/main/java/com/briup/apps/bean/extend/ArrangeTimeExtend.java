package com.briup.apps.bean.extend;

import com.briup.apps.bean.ArrangeTime;
import com.briup.apps.bean.Car;
import com.briup.apps.bean.Coach_Accept;
import com.briup.apps.bean.User;

public class ArrangeTimeExtend extends ArrangeTime{
	private Coach_Accept coachAccept;
	private User user;
	private Car car;

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Coach_Accept getCoachAccept() {
		return coachAccept;
	}

	public void setCoachAccept(Coach_Accept coachAccept) {
		this.coachAccept = coachAccept;
	}
}
