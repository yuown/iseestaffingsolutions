package yuown.isee.business.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yuown.isee.entity.Employer;
import yuown.isee.jpa.repository.EmployerRepository;

@Service
public class EmployerService extends AbstractServiceImpl<Integer, Employer, EmployerRepository> {

	@Autowired
	private EmployerRepository employerRepository;

	@Override
	public EmployerRepository repository() {
		return employerRepository;
	}
}