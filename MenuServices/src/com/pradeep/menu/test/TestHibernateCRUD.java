package com.pradeep.menu.test;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pradeep.menu.bean.orm.User;
import com.pradeep.menu.bean.orm.UserType;
import com.pradeep.menu.bean.to.StaffTO;
import com.pradeep.menu.dao.CommonUtils;
import com.pradeep.menu.user.staff.bo.StaffService;
import com.pradeep.menu.user.staff.bo.impl.StaffServiceImpl;
import com.pradeep.menu.util.hibernate.HibernateUtil;

public class TestHibernateCRUD {

	public static void main(String... args) {

		new TestHibernateCRUD().serviceCallToSaveUser();
	}

	private static void hibernateCallToSaveUser() {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();

		// UserType user = new UserType("STAFF", "Staff only", true, new
		// Date());
		final UserType userType2 = session.get(UserType.class, new Long(1));
		User user = new User("Pradeep", "Raveendra", "", CommonUtils.getDate("1984/09/20"), "0449 774 702", true, 'M',
				userType2);
		session.saveOrUpdate(user);
		session.flush();
		System.out.println("Saved User [" + user.getId() + "]");
		// System.out.println(CommonUtils.getDate("1984/09/20"));
	}

	private  void serviceCallToSaveUser() {
		
    	ApplicationContext appContext = 
  	    	  new ClassPathXmlApplicationContext("resource/BeanLocations.xml");

		final StaffTO to = new StaffTO("Devika", "Krishnan", "", CommonUtils.getDate("1984/08/16"), "0449 774 702", true, 'M',
				"CUSTOMER");
    	StaffService staffService= (StaffService)appContext.getBean("staffService");

		System.out.println(staffService); 
		staffService.save(to);
		System.out.println("SAVED [");
				
	}
}
