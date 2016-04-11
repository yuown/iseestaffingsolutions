package yuown.isee.rest.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import yuown.isee.business.services.EmployeeService;
import yuown.isee.entity.Employee;
import yuown.isee.jpa.repository.EmployeeRepository;

@RestController
@RequestMapping(value = "/employees", produces = { MediaType.APPLICATION_JSON_VALUE })
public class EmployeeResourceImpl extends AbstractResourceImpl<Integer, Employee, EmployeeRepository, EmployeeService> {

	@Autowired
	private EmployeeService employeeService;

	@Override
	public EmployeeService getService() {
		return employeeService;
	}
}
