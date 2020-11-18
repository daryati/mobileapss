package id.co.asyst.bukopin.mobile.user.web.rest;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.asyst.bukopin.mobile.user.core.service.elastic.ElasticLoginService;
import id.co.asyst.bukopin.mobile.user.core.service.CustomerLoginService;
import id.co.asyst.bukopin.mobile.user.model.entity.CustomerLogin;
import id.co.asyst.bukopin.mobile.user.model.entity.elastic.CustomerLoginElastic;


@RestController
@RequestMapping("/elastic")
public class ElasticCustLoginController {
	
	private final Logger log = LoggerFactory.getLogger(ElasticCustLoginController.class);
	
	@Autowired
	private ElasticLoginService eserv;	
	
	@Autowired
	private CustomerLoginService custLoginService;	
	

	/**
	 * Method to save the employees in the database.
	 * @param myemployees
	 * @return
	 */
	@GetMapping(value= "/storeCustLogin")
	public String storeCustomerLogin() {
		//get alll customer login data's form db
		List<CustomerLogin> custLogins = custLoginService.findAll();
		log.debug("size cus login: "+custLogins.size());
		List<CustomerLoginElastic> custLogElastic = new ArrayList<>();
		for(CustomerLogin cus : custLogins) {
			CustomerLoginElastic newCus = new CustomerLoginElastic();
			newCus.setId(cus.getId().toString());
			newCus.setDateTime(cus.getLoginAt());
			newCus.setUsername(cus.getUsername());
			
			custLogElastic.add(newCus);
			
		}
		// delete old customer login elastic data
		
		//store new customer login elastic data
		eserv.saveCustomerLogin(custLogElastic);
		return "Records saved in the db.";
	}

	/**
	 * Method to fetch all employees from the database.
	 * @return
	 */
	@GetMapping(value= "/getall")
	public Iterable<CustomerLoginElastic> getAllCustomerLogin() {
		log.debug("get all data from elastic");
		return eserv.findAllCustomerLogin();
	}
/*
	*//**
	 * Method to fetch the employee details on the basis of designation.
	 * @param designation
	 * @return
	 *//*
	@GetMapping(value= "/findbydesignation/{employee-designation}")
	public Iterable<Employee> getByDesignation(@PathVariable(name= "employee-designation") String designation) {
		return eserv.findByDesignation(designation);
	}*/

}
