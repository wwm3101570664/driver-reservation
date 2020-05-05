package com.briup.apps.dao.extend;

import java.util.List;

import com.briup.apps.bean.extend.CommentExtend;

public interface CommentExtendMapper {
	List<CommentExtend> findByCoachId(int coachId);
}
