package yuown.isee.business.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yuown.isee.entity.Application;
import yuown.isee.jpa.repository.ApplicationRepository;

@Service
public class ApplicationService extends AbstractServiceImpl<Integer, Application, ApplicationRepository> {

	@Autowired
	private ApplicationRepository applicationRepository;

	@Override
	public ApplicationRepository repository() {
		return applicationRepository;
	}
}