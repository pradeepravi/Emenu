package com.pradeep.menu.dao.impl;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.pradeep.menu.bean.orm.User;
import com.pradeep.menu.dao.UserDAO;
import com.pradeep.menu.util.hibernate.HibernateUtil;

@Component("userDAOImpl")
public class UserDAOImpl implements UserDAO {

	@Override
	public boolean save(User user) {
 
		Session session = null;
		boolean isSuccess = false;
		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();
			session.saveOrUpdate(user);
			session.flush();
			isSuccess = true;
			System.out.println("Saved [" + user.getId() + "]");
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			System.out.println("IS SUCCESS [" + isSuccess + "]");
		}
		return isSuccess;
	}

	@Override
	public boolean update(User user) {

		Session session = null;
		boolean isSuccess = false;
		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();
			session.update(user);
			session.flush();
			isSuccess = true;
			System.out.println("Update [" + user.getId() + "]");
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			System.out.println("IS SUCCESS [" + isSuccess + "]");
		}
		return isSuccess;
	}

	@Override
	public boolean delete(User user) {
		user.setActive(false);
		boolean isSucces = update(user);
		return isSucces;
	}

	@Override
	public List<User> getUsers(List<User> users) {
		List<User> usersList = new LinkedList<User>();

		for (User user : users) {
			usersList.add(getUser(user));
		}

		System.out.println("USERS [" + usersList.size() + "]");
		return usersList;
	}

	@Override
	public User getUser(User user) {

		User populatedUser = null;
		if (user != null && user.getId() > 0) {
			Session session = null;
			try {

				session = HibernateUtil.getSession();
				populatedUser =  (User)session.get(User.class, user.getId());
				if (populatedUser != null) {
					System.out.println("Found User [" + populatedUser.getFirstName() + "]");
				}
			} catch (HibernateException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				session.close();
			}
		}
		return populatedUser;
	}

}
