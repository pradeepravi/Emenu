package com.pradeep.menu.dao.impl;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.pradeep.menu.bean.orm.User;
import com.pradeep.menu.bean.orm.UserType;
import com.pradeep.menu.dao.UserDAO;
import com.pradeep.menu.util.hibernate.HibernateUtil;

@Component("userDAOImpl")
public class UserDAOImpl implements UserDAO {

	final Logger log = LoggerFactory.getLogger(UserDAO.class);
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
			log .debug("UserDAOImpl : save : Saved [" + user.getId() + "]");
		} catch (HibernateException e) {
			log .error ("UserDAOImpl : save : Hibernate Exception",e);
			e.printStackTrace();
		} catch (Exception e) {

			log .error ("UserDAOImpl : save : Exception",e);
			e.printStackTrace();
		} finally {
			session.close();
			log .debug("UserDAOImpl : save : IS SUCCESS [" + isSuccess + "]");
		}
		return isSuccess;
	}

	@Override
	public boolean update(User user) {
		log .debug("UserDAOImpl : update : start");
		Session session = null;
		boolean isSuccess = false;
		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();
			session.update(user);
			session.flush();
			isSuccess = true;
			log.debug("UserDAOImpl : update :  Update [" + user.getId() + "]");
		} catch (HibernateException e) {
			log .error ("UserDAOImpl : update : Hibernate Exception",e);
			e.printStackTrace();
		} catch (Exception e) {

			log .error ("UserDAOImpl : update: Exception",e);
			e.printStackTrace();
		} finally {
			session.close();
			log .debug("UserDAOImpl : update : IS SUCCESS [" + isSuccess + "]");
		}
		return isSuccess;
	}

	@Override
	public boolean delete(User user) {
		log .debug("UserDAOImpl : delete : start");

		user.setActive(false);
		boolean isSucces = update(user);

		log .debug("UserDAOImpl : delete ");

		return isSucces;
	}

	@Override
	public List<User> getUsers(List<User> users) {
		log .debug("UserDAOImpl : getUsers : start");
		List<User> usersList = new LinkedList<User>();

		for (User user : users) {
			usersList.add(getUser(user));
		}
		
		log.debug("UserDAOImpl : USERS [" + usersList.size() + "]");
		return usersList;
	}

	@Override
	public List<User> getAllUsers() {
		log .debug("UserDAOImpl : getAllUsers: start");
		
		final String sql = "from User";
		Session session = HibernateUtil.getSession();
		final List <User> users= (List<User>) session.createQuery(sql).list();

		log .debug("UserDAOImpl : getAllUsers : Users ["+users+"]");
		
		return users;

	}

	@Override
	public User getUser(User user) {

		User populatedUser = null;
		if (user != null && user.getId() > 0) {
			Session session = null;
			try {

				session = HibernateUtil.getSession();
				populatedUser = (User) session.get(User.class, user.getId());
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
