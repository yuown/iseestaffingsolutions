package yuown.isee.rest.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import yuown.isee.business.services.ApplicationService;
import yuown.isee.entity.Application;
import yuown.isee.jpa.repository.ApplicationRepository;

@RestController
@RequestMapping(value = "/applications", produces = { MediaType.APPLICATION_JSON_VALUE })
public class ApplicationResourceImpl extends AbstractResourceImpl<Integer, Application, ApplicationRepository, ApplicationService> {

	@Autowired
	private ApplicationService applicationService;

	@Override
	public ApplicationService getService() {
		return applicationService;
	}
}
