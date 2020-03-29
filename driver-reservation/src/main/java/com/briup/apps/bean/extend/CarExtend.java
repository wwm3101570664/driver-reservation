package com.briup.apps.bean.extend;

import com.briup.apps.bean.Car;
import com.briup.apps.bean.Coach_Accept;

public class CarExtend extends Car{
	private Coach_Accept coachAccept;

	public Coach_Accept getCoachAccept() {
		return coachAccept;
	}

	public void setCoachAccept(Coach_Accept coachAccept) {
		this.coachAccept = coachAccept;
	}
	
}
