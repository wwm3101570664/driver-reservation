package com.briup.apps.dao.extend;

import java.util.List;

import com.briup.apps.bean.Coach_Accept;
import com.briup.apps.bean.extend.Coach_AcceptExtend;

public interface Coach_AcceptExtendMapper {
	List<Coach_Accept> findAll();
	Coach_AcceptExtend findById(int id);
}
