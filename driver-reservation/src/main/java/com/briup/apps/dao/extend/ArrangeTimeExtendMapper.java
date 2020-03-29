package com.briup.apps.dao.extend;

import java.util.List;

import com.briup.apps.bean.ArrangeTime;
import com.briup.apps.bean.extend.ArrangeTimeExtend;

public interface ArrangeTimeExtendMapper {
	List<ArrangeTime> findAllArrange(int coachId);
	List<ArrangeTimeExtend> findAll();
}
