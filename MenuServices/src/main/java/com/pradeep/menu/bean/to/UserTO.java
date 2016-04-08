package com.pradeep.menu.bean.to;

import java.io.Serializable;
import java.util.Date;

public class UserTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3098012528078340748L;
	
	public UserTO() { 
		super();
		// TODO Auto-generated constructor stub
	}

	private long id;
	private String firstName;
	private String lastName;
	private String middleName;
	private Date dob;
	private String mobileNumber;
	private boolean isActive;
	private char sex;
	private String userType;

	
	
	public UserTO( String firstName, String lastName, String middleName, Date dob, String mobileNumber,
			boolean isActive, char sex, String userType) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.dob = dob;
		this.mobileNumber = mobileNumber;
		this.isActive = isActive;
		this.sex = sex;
		this.userType = userType;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public char getSex() {
		return sex;
	}

	public void setSex(char sex) {
		this.sex = sex;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	

}
