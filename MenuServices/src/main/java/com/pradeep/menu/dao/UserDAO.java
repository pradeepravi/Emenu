package com.pradeep.menu.dao;

import java.util.List;

import com.pradeep.menu.bean.orm.User;

public interface UserDAO {
	public boolean save(User user);

	public boolean update(User user);

	public boolean delete(User user);

	public List<User> getUsers(List<User> users);
	
	public List<User> getAllUsers();

	public User getUser(User users);

}
