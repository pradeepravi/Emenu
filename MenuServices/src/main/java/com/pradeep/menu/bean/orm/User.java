package com.pradeep.menu.bean.orm;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
public class User {
	/*
	 * `user_id` int(11) NOT NULL, `user_type_id` int(11) NOT NULL, `first_name`
	 * varchar(45) NOT NULL, `last_name` varchar(45) NOT NULL, `middle_name`
	 * varchar(45) DEFAULT NULL, `dob` datetime NOT NULL, `mobile_number`
	 * varchar(15) DEFAULT NULL, `status_flag` char(1) NOT NULL DEFAULT 'Y',
	 * `sex` char(1) NOT NULL,
	 */
	public User() {

	}

	public User(String firstName, String lastName, String middleName, Date dob, String mobileNumber, boolean isActive,
			char sex, UserType usertType) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.dob = dob;
		this.mobileNumber = mobileNumber;
		this.isActive = isActive;
		this.sex = sex;
		this.userType = usertType;
	}

	public User(long userID) {
		this.id = userID;
	}

	private long id;
	private String firstName;
	private String lastName;
	private String middleName;
	private Date dob;
	private Date createdDate;
	private String mobileNumber;
	private boolean isActive;
	private char sex;
	private UserType userType;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "first_name")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "last_name")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "middle_name")
	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	@Column(name = "dob")
	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	@Column(name = "mobile_number")
	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@Column(name = "status_flag")
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Column(name = "sex")
	public char getSex() {
		return sex;
	}

	public void setSex(char sex) {
		this.sex = sex;
	}

	@ManyToOne
	@JoinColumn(name = "user_type_id")
	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
