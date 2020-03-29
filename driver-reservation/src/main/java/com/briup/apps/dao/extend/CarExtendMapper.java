package com.briup.apps.dao.extend;

import com.briup.apps.bean.extend.CarExtend;

public interface CarExtendMapper {
	CarExtend cascadeById(int carId);
}
