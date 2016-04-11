package yuown.isee.business.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yuown.isee.entity.Employee;
import yuown.isee.jpa.repository.EmployeeRepository;

@Service
public class EmployeeService extends AbstractServiceImpl<Integer, Employee, EmployeeRepository> {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public EmployeeRepository repository() {
		return employeeRepository;
	}
}