package yuown.isee.rest.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import yuown.isee.business.services.DeploymentService;
import yuown.isee.entity.Deployment;
import yuown.isee.jpa.repository.DeploymentRepository;

@RestController
@RequestMapping(value = "/deployments", produces = { MediaType.APPLICATION_JSON_VALUE })
public class DeploymentResourceImpl extends AbstractResourceImpl<Integer, Deployment, DeploymentRepository, DeploymentService> {

	@Autowired
	private DeploymentService deploymentService;

	@Override
	public DeploymentService getService() {
		return deploymentService;
	}
}
