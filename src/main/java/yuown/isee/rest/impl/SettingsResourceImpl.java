package yuown.isee.rest.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import yuown.isee.business.services.ConfigurationService;
import yuown.isee.entity.Configuration;
import yuown.isee.jpa.repository.ConfigurationRepository;

@RestController
@RequestMapping(value = "/settings", produces = { MediaType.APPLICATION_JSON_VALUE })
public class SettingsResourceImpl extends AbstractResourceImpl<Integer, Configuration, ConfigurationRepository, ConfigurationService> {

	@Autowired
	private ConfigurationService configurationService;

	@Override
	public ConfigurationService getService() {
		return configurationService;
	}

	@RequestMapping(value = "/all", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public List<Configuration> save(@RequestBody ArrayList<Configuration> entities) {
		List<Configuration> saved = new ArrayList<Configuration>();
		for (Configuration configuration : entities) {
			try {
				configuration.setAutoLoad(true);
				configuration.setDeletable(false);
				Configuration afterSave = getService().save(configuration, null);
				saved.add(afterSave);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return saved;
	}
}