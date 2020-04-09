package com.briup.apps.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.briup.apps.bean.ArrangeTime;
import com.briup.apps.bean.ArrangeTimeExample;
import com.briup.apps.bean.Car;
import com.briup.apps.bean.CarExample;
import com.briup.apps.bean.Coach_Accept;
import com.briup.apps.bean.Comment;
import com.briup.apps.bean.CommentExample;
import com.briup.apps.bean.User;
import com.briup.apps.bean.UserExample;
import com.briup.apps.bean.extend.CarExtend;
import com.briup.apps.bean.extend.UserExtend;
import com.briup.apps.config.CustomerException;
import com.briup.apps.dao.ArrangeTimeMapper;
import com.briup.apps.dao.CarMapper;
import com.briup.apps.dao.Coach_AcceptMapper;
import com.briup.apps.dao.CommentMapper;
import com.briup.apps.dao.UserMapper;
import com.briup.apps.dao.extend.CarExtendMapper;
import com.briup.apps.dao.extend.Coach_AcceptExtendMapper;
import com.briup.apps.dao.extend.UserExtendMapper;
import com.briup.apps.service.IUserService;

@Service
public class UserServiceImpl implements IUserService{
	@Resource
	private UserMapper userMapper;
	@Resource
	private Coach_AcceptMapper coach_acceptMapper;
	@Resource
	private ArrangeTimeMapper arrangeMapper;
	@Resource
	private CarMapper carMapper;
	@Resource
	private CarExtendMapper carExtendMapper;
	@Resource
	private CommentMapper commentMapper;
	@Resource
	private Coach_AcceptExtendMapper coach_acceptExtendMapper;
	@Resource
	private UserExtendMapper userExtendMapper;
	
	
	//注册
	@Override
	public void insert(String name, int age, String gender, String password) {
		if(password.length()<6) {
			throw new CustomerException("密码不能少于6位!!!");
		}
		User user = new User();
		UserExample example = new UserExample();
		example.createCriteria().andNameEqualTo(name);
		List<User> list = userMapper.selectByExample(example);
		if(list.size()>0) {
			throw new CustomerException("用户已存在!!!");
		}else {
			user.setName(name);
			user.setAge(age);
			user.setGender(gender);
			user.setPassword(password);
			user.setRoleId(2);
			userMapper.insert(user);
		}
		
	}
	
	//选择教练
	@Override
	public void selectCoach(int id,int coachId) {
		User user = userMapper.selectByPrimaryKey(id);
		if(user.getCoachId()==null) {
			Coach_Accept coach_Accept = coach_acceptMapper.selectByPrimaryKey(coachId);
			System.out.println(coach_Accept);
			user.setCoachId(coach_Accept.getId());
			userMapper.updateByPrimaryKey(user);
		}else {
			throw new CustomerException("您已经有教练不能重复选择!!!");
		}
	}
	
	//绑定个人信息
	@Override
	public void binding(int id,String phoneNum, String email) {
		User user = userMapper.selectByPrimaryKey(id);
		user.setPhonenum(phoneNum);
		user.setEmail(email);
		userMapper.updateByPrimaryKey(user);
	}
	
	//学员预约练车
	@Override
	public void Booking(int userId) {
		
		User user = userMapper.selectByPrimaryKey(userId);
		int coachId = user.getCoachId();
		if(coachId!=0) {
			CarExample carExample = new CarExample();
			carExample.createCriteria().andCoachIdEqualTo(coachId);
			List<Car> carList = carMapper.selectByExample(carExample);
			
			ArrangeTimeExample example = new ArrangeTimeExample();
			example.createCriteria().andUserIdEqualTo(userId);
			List<ArrangeTime> list = arrangeMapper.selectByExample(example);
			if(list.size()>0) {
				throw new CustomerException("您已有预约，如想预约成功，请取消上次预约!!!");
			}else {
				ArrangeTime arrange = new ArrangeTime();
				arrange.setCarId(carList.get(0).getId());
				arrange.setUserId(userId);
				arrange.setCoachId(coachId);
				arrange.setStarttime(new Date().getTime());
				arrange.setEndtime(new Date().getTime());
				arrange.setStatus("已预约!!!");
				arrangeMapper.insert(arrange);
			}
		}
		else {
			throw new CustomerException("你还未选择教练，请选择教练!!!");
		}
		
	}
	
	//删除预约信息
	@Override
	public void deleteBooking(int userId) {
		ArrangeTimeExample example = new ArrangeTimeExample();
		example.createCriteria().andUserIdEqualTo(userId);
		List<ArrangeTime> list = arrangeMapper.selectByExample(example);
		if(list.size()<=0) {
			throw new CustomerException("您还未有任何预约信息!!!");
		}else {
			arrangeMapper.deleteByPrimaryKey(list.get(0).getId());
		}
		
	}
	
	//查看教练信息、车辆信息
	@Override
	public CarExtend findMessages(int coachId) {
		
		CarExample example = new CarExample();
		example.createCriteria().andCoachIdEqualTo(coachId);
		List<Car> cars = carMapper.selectByExample(example );
		CarExtend carExtend = carExtendMapper.cascadeById(cars.get(0).getId());
		return carExtend;
	}
	
	//对教练进行评价
	@Override
	public void makeComments(int userId,String comment) {
		User user = userMapper.selectByPrimaryKey(userId);
		int coachId = user.getCoachId();
		 Comment comments = new Comment();
		 comments.setCoachId(coachId);
		 comments.setCommentTime(new Date().getTime());
		 comments.setUserId(userId);
		 comments.setContent(comment);
		 commentMapper.insert(comments);
		
	}
	
	//查看教练评论信息
	@Override
	public List<Comment> findComments(int coachId) {
		CommentExample example = new CommentExample();
		example.createCriteria().andCoachIdEqualTo(coachId);
		List<Comment> list = commentMapper.selectByExample(example);
		return list;
	}

	@Override
	public List<CarExtend> findAll() {
		List<CarExtend> list = carExtendMapper.cascadeFindAll();
		return list;
	}
	
	//通过id来查找学员
	@Override
	public User findByUserId(int userId) {
		User user = userMapper.selectByPrimaryKey(userId);
		return user;
	}
	
	
	//学员登录
	@Override
	public User login(String name, String password) {
		UserExample example = new UserExample();
		example.createCriteria().andNameEqualTo(name).andPasswordEqualTo(password);
		List<User> list = userMapper.selectByExample(example);
		if(list.size()<=0) {
			throw new CustomerException("学员还未注册!!!!");
		}else {
			return list.get(0);
		}
		
		
	}

	@Override
	public UserExtend findUserById(int userId) {
		UserExtend userExtend = userExtendMapper.findById(userId);
		return userExtend;
	}

	@Override
	public User selectById(int id) {
		User user = userMapper.selectByPrimaryKey(id);
		return user;
	}

}
