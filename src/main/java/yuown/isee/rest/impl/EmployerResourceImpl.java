package yuown.isee.rest.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import yuown.isee.business.services.EmployerService;
import yuown.isee.entity.Employer;
import yuown.isee.jpa.repository.EmployerRepository;

@RestController
@RequestMapping(value = "/employers", produces = { MediaType.APPLICATION_JSON_VALUE })
public class EmployerResourceImpl extends AbstractResourceImpl<Integer, Employer, EmployerRepository, EmployerService> {

	@Autowired
	private EmployerService employerService;

	@Override
	public EmployerService getService() {
		return employerService;
	}
}
