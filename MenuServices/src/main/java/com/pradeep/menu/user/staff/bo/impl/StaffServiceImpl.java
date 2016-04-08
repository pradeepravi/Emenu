package com.pradeep.menu.user.staff.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pradeep.menu.bean.orm.User;
import com.pradeep.menu.bean.to.StaffTO;
import com.pradeep.menu.dao.impl.UserDAOImpl;
import com.pradeep.menu.dao.impl.UserTypeDAOImpl;
import com.pradeep.menu.user.staff.bo.StaffService;

@Component("staffService")
public class StaffServiceImpl implements StaffService {

	@Autowired
	UserDAOImpl userDAO;

	@Autowired
	UserTypeDAOImpl userTypeDAO;

	@Override
	public StaffTO findStaffByID(long staffID) {
		User user = userDAO.getUser(new User(staffID));
		final StaffTO staff = getPopulatedStaffTO(user);
		return staff;
	}

	@Override
	public boolean save(StaffTO staff) {
		System.out.println("StaffServiceImpl : SAVE");
		User user = this.getPopulatedUser(staff);
		boolean flag = userDAO.save(user);
		return flag;
	}

	@Override
	public boolean update(StaffTO staff) {

		System.out.println("StaffServiceImpl : UPDATE");
		
		User user = this.getPopulatedUser(staff);
		boolean flag = userDAO.update(user);
		return flag;

	}

	@Override
	public boolean delete(StaffTO staff) {

		User user = this.getPopulatedUser(staff);
		boolean flag = userDAO.delete(user);
		return flag;

	}

	private StaffTO getPopulatedStaffTO(User user) {
		StaffTO staff = null;
		System.out.println("StaffServiceImpl : getPopulatedStaffTO");

		if (user != null) {
			staff = new StaffTO();
			staff.setId(user.getId());
			staff.setFirstName(user.getFirstName());
			staff.setLastName(user.getLastName());
			staff.setMiddleName(user.getMiddleName());
			staff.setMobileNumber(user.getMobileNumber());
			staff.setSex(user.getSex());
			staff.setDob(user.getDob());
			staff.setUserType(user.getUserType().getType());
		}

		return staff;
	}

	private User getPopulatedUser(StaffTO staff) {
		User user = null;

		System.out.println("StaffServiceImpl : getPopulatedUser");
		
		if (staff != null) {
			user = new User();
			user.setId(staff.getId());
			user.setFirstName(staff.getFirstName());
			user.setLastName(staff.getLastName());
			user.setMiddleName(staff.getMiddleName());
			user.setMobileNumber(staff.getMobileNumber());
			user.setSex(staff.getSex());
			user.setDob(staff.getDob());
			user.setUserType(userTypeDAO.getUserType(staff.getUserType()));
		}

		return user;
	}

}
