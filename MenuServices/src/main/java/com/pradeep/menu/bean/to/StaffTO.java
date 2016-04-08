package com.pradeep.menu.bean.to;

import java.util.Date;

public class StaffTO extends UserTO {

	/**
	 * 
	 */
	 
	private static final long serialVersionUID = 1608226908611740972L;

	public StaffTO(){
		super();
	}
	public StaffTO(String firstName, String lastName, String middleName, Date dob, String mobileNumber,
			boolean isActive, char sex, String userType) {
		super( firstName, lastName, middleName, dob, mobileNumber, isActive, sex, userType);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setUserType(String userType) {
		super.setUserType("STAFF");
	}
}
