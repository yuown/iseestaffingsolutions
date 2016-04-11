package yuown.isee.business.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yuown.isee.entity.Job;
import yuown.isee.entity.User;
import yuown.isee.jpa.repository.JobRepository;
import yuown.isee.jpa.repository.UserRepository;
import yuown.isee.model.UserModel;

@Service
public class JobService extends AbstractServiceImpl<Integer, Job, JobRepository> {

	@Autowired
	private JobRepository jobRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public JobRepository repository() {
		return jobRepository;
	}

	public void apply(Job item, UserModel userModel) {
		User u = userRepository.findByUsername(userModel.getUsername());
	}
}