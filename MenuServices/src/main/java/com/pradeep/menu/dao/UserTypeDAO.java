package com.pradeep.menu.dao;

import java.util.List;

import com.pradeep.menu.bean.orm.UserType;

public interface UserTypeDAO {
	public boolean save(UserType user);

	public boolean update(UserType user);

	public boolean delete(UserType user);

	public List<UserType> getUserTypes(List<UserType> users);

	public UserType getUserType(String staff);
	
}
