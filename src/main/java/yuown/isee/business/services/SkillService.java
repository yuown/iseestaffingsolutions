package yuown.isee.business.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yuown.isee.entity.Skill;
import yuown.isee.jpa.services.SkillRepositoryService;
import yuown.isee.model.SkillModel;
import yuown.isee.transformer.SkillTransformer;

@Service
public class SkillService extends AbstractServiceImpl<Integer, SkillModel, Skill, SkillRepositoryService, SkillTransformer> {

	@Autowired
	private SkillRepositoryService skillRepositoryService;

	@Autowired
	private SkillTransformer skillTransformer;

	@Override
	protected SkillRepositoryService repoService() {
		return skillRepositoryService;
	}

	@Override
	protected SkillTransformer transformer() {
		return skillTransformer;
	}

	public List<SkillModel> getSkills(final Integer profileId) {
		return transformer().transformTo(repoService().findAllByProfileId(profileId));
	}

	public void saveSkills(int profileId, List<SkillModel> skills) {
		for (SkillModel skillModel : skills) {
			skillModel.setProfileId(profileId);
		}
		repoService().save(transformer().transformFrom(skills));
	}
}
