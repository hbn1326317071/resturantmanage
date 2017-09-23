package org.resturant.service.impl;

import java.util.List;

import org.resturant.dao.UserManageDao;
import org.resturant.po.User;
import org.resturant.service.UserManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
@Service
public class UserManageServiceImpl implements UserManageService{
	@Autowired
	private UserManageDao userManageDao;
	@Override
	public List<User> queryUserInfo() {
		
		return userManageDao.queryUserInfo();
	}

}
