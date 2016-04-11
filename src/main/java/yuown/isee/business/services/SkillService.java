package yuown.isee.business.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yuown.isee.entity.Skill;
import yuown.isee.jpa.repository.SkillRepository;

@Service
public class SkillService extends AbstractServiceImpl<Integer, Skill, SkillRepository> {

	@Autowired
	private SkillRepository skillRepository;

	@Override
	public SkillRepository repository() {
		return skillRepository;
	}
}