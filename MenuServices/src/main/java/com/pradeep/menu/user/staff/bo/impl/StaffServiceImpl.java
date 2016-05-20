package com.pradeep.menu.user.staff.bo.impl;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pradeep.menu.bean.orm.User;
import com.pradeep.menu.bean.to.StaffTO;
import com.pradeep.menu.dao.impl.UserDAOImpl;
import com.pradeep.menu.dao.impl.UserTypeDAOImpl;
import com.pradeep.menu.user.staff.bo.StaffService;

@Component("staffServiceImpl")
public class StaffServiceImpl implements StaffService {

	final Logger log = LoggerFactory.getLogger(StaffServiceImpl.class);
	
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
		log.debug("StaffServiceImpl : SAVE");
		User user = this.getPopulatedUser(staff);
		boolean flag = userDAO.save(user);
		return flag;
	}

	@Override
	public boolean update(StaffTO staff) {

		log.debug("StaffServiceImpl : UPDATE");

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
		log.debug("StaffServiceImpl : getPopulatedStaffTO");

		if (user != null) {
			staff = new StaffTO();
			staff.setId(user.getId());
			staff.setFirstName(user.getFirstName());
			staff.setLastName(user.getLastName());
			staff.setMiddleName(user.getMiddleName());
			staff.setMobileNumber(user.getMobileNumber());
			staff.setSex(user.getSex());
			staff.setDob(user.getDob());
			staff.setCreatedDate(user.getCreatedDate());
			staff.setUserType(user.getUserType().getType());
		}

		return staff;
	}

	private User getPopulatedUser(StaffTO staff) {
		User user = null;

		log.debug("StaffServiceImpl : getPopulatedUser");

		if (staff != null) {
			user = new User();
			user.setId(staff.getId());
			user.setFirstName(staff.getFirstName());
			user.setLastName(staff.getLastName());
			user.setMiddleName(staff.getMiddleName());
			user.setMobileNumber(staff.getMobileNumber());
			user.setSex(staff.getSex());
			user.setDob(staff.getDob());
			user.setCreatedDate(staff.getCreatedDate());
			user.setUserType(userTypeDAO.getUserType(staff.getUserType()));
		}

		return user;
	}

	@Override
	public List<StaffTO> fetchAllStaff() {
		log.debug("StaffServiceImpl : fetchAllStaff");
		final List<User> users = userDAO.getAllUsers();

		List<StaffTO> staffs = new LinkedList<StaffTO>(); 
		for(User user : users){
			StaffTO staff = getPopulatedStaffTO(user);
			staffs.add(staff);
		}
		
		
		log.debug("StaffServiceImpl : fetchAllStaff : LIST ["+staffs+"]");
 		
		return staffs;
	}

}
