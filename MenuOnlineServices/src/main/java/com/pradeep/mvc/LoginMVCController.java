package com.pradeep.mvc;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.pradeep.menu.bean.to.StaffTO;
import com.pradeep.menu.user.staff.bo.StaffService;

@Controller
@RequestMapping("/")
public class StaffServicesMVCController {
	@Autowired
	StaffService staffService;

	final Logger log = LoggerFactory.getLogger(StaffServicesMVCController.class);

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String welcomePage(ModelMap model) {

		log.debug("StaffServicesMVCController : welcomePage : Start  ");
		return "index";
	}

	@RequestMapping(value = { "/springMVCSkillShow" }, method = RequestMethod.GET)
	public String springMVCSkillShow(ModelMap model) {

		log.debug("StaffServicesMVCController : springMVCSkillShow : Start  ");
		return "springMVCSkillShow";
	}

	@RequestMapping(value = { "/angularJSSkillShow" }, method = RequestMethod.GET)
	public String angularJSSkillShow(ModelMap model) {

		log.debug("StaffServicesMVCController : angularJSSkillShow : Start  ");
		return "angularJSSkillShow";
	}

	@RequestMapping(value = { "/list" }, method = RequestMethod.GET)
	public String listUsers(ModelMap model) {

		log.debug("StaffServicesMVCController : listUsers : Start  ");
		final List<StaffTO> users = staffService.fetchAllStaff();

		log.debug("StaffServicesMVCController : listUsers : LIST Retirned [" + users + "]");

		model.addAttribute("staffs", users);
		return "staffsList";
	}

}
