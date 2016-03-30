package com.pradeep.menu.user.staff.bo;

import com.pradeep.menu.bean.to.StaffTO;
import com.pradeep.menu.bean.to.UserTO;

public interface StaffService {
	public UserTO findStaffByID(long staffID);
	public boolean save(StaffTO user);
	public boolean update(StaffTO user);

	public boolean delete(StaffTO user);

}
