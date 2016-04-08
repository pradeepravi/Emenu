package com.pradeep.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pradeep.menu.bean.to.StaffTO;
import com.pradeep.menu.user.staff.bo.StaffService;
 
@RestController
public class StaffServiceController {
  
    @Autowired
    StaffService staffService;  //Service which will do all data retrieval/manipulation work
 
     
    //-------------------Retrieve Staff for The ID Passed--------------------------------------------------------
     
	@RequestMapping(value = "/staff/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<StaffTO> findStaffByID(@PathVariable("id") long id) {
        StaffTO staff = staffService.findStaffByID(id); 
        if(staff != null){
            return new ResponseEntity<StaffTO>(HttpStatus.NO_CONTENT);
            //You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<StaffTO>(staff , HttpStatus.OK);
    }
 
	@RequestMapping(value = "/greeting", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String sayHi() {
        
        return "Hello";
    }
 
 

 
}