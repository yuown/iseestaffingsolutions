package yuown.isee.business.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yuown.isee.entity.Education;
import yuown.isee.entity.Employee;
import yuown.isee.jpa.repository.EducationRepository;
import yuown.isee.jpa.repository.EmployeeRepository;

@Service
public class EducationService extends AbstractServiceImpl<Integer, Education, EducationRepository> {

	@Autowired
	private EducationRepository educationRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public EducationRepository repository() {
		return educationRepository;
	}

	public void saveEducations(int profileId, List<Education> educations) {
		Employee employee = employeeRepository.findById(profileId);
		if (null != employee) {
			for (Education education : educations) {
				education.setEmployee(employee);
			}
			repository().save(educations);
		}
	}
}