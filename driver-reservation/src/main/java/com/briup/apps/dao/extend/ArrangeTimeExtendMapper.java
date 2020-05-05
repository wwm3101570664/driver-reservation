package com.briup.apps.dao.extend;

import java.util.List;

import com.briup.apps.bean.extend.ArrangeTimeExtend;

public interface ArrangeTimeExtendMapper {
	List<ArrangeTimeExtend> findAllArrangeByCoachId(int coachId);
	List<ArrangeTimeExtend> findAll();
	List<ArrangeTimeExtend> findArrangeByUserId(int userId);
}
