package yuown.isee.rest.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import yuown.isee.business.services.ProfileService;
import yuown.isee.business.services.SkillService;
import yuown.isee.model.ProfileModel;
import yuown.isee.model.SkillModel;

@RestController
@RequestMapping(value = "/skills", produces = { MediaType.APPLICATION_JSON_VALUE })
public class SkillResourceImpl {

	@Autowired
	private SkillService skillService;
	
	@Autowired
	private ProfileService profileService;

	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public SkillModel save(@RequestBody SkillModel model) {
		return skillService.save(model);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	@ResponseBody
	public SkillModel getById(@PathVariable("id") int id) {
		return skillService.getById(id);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{skillId}/{profileId}")
	public ResponseEntity<String> removeById(@PathVariable("skillId") int skillId, @PathVariable("profileId") int profileId) throws Exception {
		SkillModel skill = skillService.getById(skillId);
		HttpHeaders headers = new HttpHeaders();
		if (null == skill) {
			headers.add("errorMessage", "Skill with ID " + skillId + " Not Found");
			return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
		} else {
			try {
				ProfileModel profile = profileService.getById(profileId);
				if (null == profile) {
					headers.add("errorMessage", "Profile with ID " + profileId + " Not Found");
					return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
				} else {
					skillService.removeById(skillId);
					headers.add("errorMessage", "Skill with ID " + skillId + " Deleted Successfully");
					return new ResponseEntity<String>(headers, HttpStatus.OK);
				}
			} catch (Exception e) {
				headers.add("errorMessage", "Skill with ID " + skillId + " cannot be Deleted");
				return new ResponseEntity<String>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "profile/{id}")
	@ResponseBody
	public List<SkillModel> getProfileSkills(@PathVariable("id") int id) {
		List<SkillModel> skills = new ArrayList<SkillModel>();
		ProfileModel profile = profileService.getById(id);
		if (null != profile) {
			 skills = skillService.getSkills(profile.getId());
		}
		return skills;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "profile/{id}")
	public void saveProfileSkills(@PathVariable("id") int profileId, @RequestBody List<SkillModel> skills) {
		ProfileModel profile = profileService.getById(profileId);
		if (null != profile) {
			 skillService.saveSkills(profileId, skills);
		}
	}
}
