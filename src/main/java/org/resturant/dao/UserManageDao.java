package org.resturant.dao;

import java.util.List;

import org.resturant.po.User;

public interface UserManageDao {
	List<User> queryUserInfo();
}
