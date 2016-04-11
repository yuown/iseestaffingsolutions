package yuown.isee.rest.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import yuown.isee.business.services.SkillService;
import yuown.isee.entity.Skill;
import yuown.isee.jpa.repository.SkillRepository;

@RestController
@RequestMapping(value = "/skills", produces = { MediaType.APPLICATION_JSON_VALUE })
public class SkillResourceImpl extends AbstractResourceImpl<Integer, Skill, SkillRepository, SkillService> {

	@Autowired
	private SkillService skillService;

	@Override
	public SkillService getService() {
		return skillService;
	}
}
