package yuown.isee.rest.impl;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import yuown.isee.business.services.JobService;
import yuown.isee.entity.Job;
import yuown.isee.jpa.repository.JobRepository;
import yuown.isee.model.UserModel;
import yuown.isee.security.YuownTokenAuthenticationService;

@RestController
@RequestMapping(value = "/jobs", produces = { MediaType.APPLICATION_JSON_VALUE })
public class JobResourceImpl extends AbstractResourceImpl<Integer, Job, JobRepository, JobService> {

	@Autowired
	private JobService jobService;
	
	@Autowired
	private YuownTokenAuthenticationService yuownTokenAuthenticationService;

	@Override
	public JobService getService() {
		return jobService;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/apply/{id}")
	@ResponseBody
	public ResponseEntity<String> apply(@PathVariable("id") Integer id, @Context HttpServletRequest request) {
		Job item = getService().findById(id);
		HttpHeaders headers = new HttpHeaders();
		if (null == item) {
			headers.add("errorMessage", "Job with ID " + id + " Not Found");
			return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
		} else {
			try {
				UserModel userModel = yuownTokenAuthenticationService.getUserObject(request);
				getService().apply(item, userModel);
				return new ResponseEntity<String>(headers, HttpStatus.OK);
			} catch (Exception e) {
				headers.add("errorMessage", "Job with ID " + id + " cannot be Deleted");
				return new ResponseEntity<String>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}
}
