package com.briup.apps.dao.extend;

import java.util.List;

import com.briup.apps.bean.extend.CarExtend;

public interface CarExtendMapper {
	CarExtend cascadeById(int carId);
	List<CarExtend> cascadeFindAll();
}
