package yuown.isee.rest.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import yuown.isee.business.services.EducationService;
import yuown.isee.entity.Education;
import yuown.isee.jpa.repository.EducationRepository;

@RestController
@RequestMapping(value = "/educations", produces = { MediaType.APPLICATION_JSON_VALUE })
public class EducationResourceImpl extends AbstractResourceImpl<Integer, Education, EducationRepository, EducationService> {

	@Autowired
	private EducationService educationService;

	@Override
	public EducationService getService() {
		return educationService;
	}
}
