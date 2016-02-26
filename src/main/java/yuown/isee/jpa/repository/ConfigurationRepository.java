package yuown.isee.jpa.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import yuown.isee.entity.Configuration;

@Repository
public interface ConfigurationRepository extends BaseRepository<Configuration, Integer> {

	public Configuration findByName(String name);

	public List<Configuration> findByAutoLoad(boolean autoLoad);

}
