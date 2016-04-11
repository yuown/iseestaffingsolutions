package yuown.isee.business.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yuown.isee.entity.Deployment;
import yuown.isee.jpa.repository.DeploymentRepository;

@Service
public class DeploymentService extends AbstractServiceImpl<Integer, Deployment, DeploymentRepository> {

	@Autowired
	private DeploymentRepository deploymentRepository;

	@Override
	public DeploymentRepository repository() {
		return deploymentRepository;
	}
}