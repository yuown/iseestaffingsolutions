package yuown.isee.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import yuown.isee.entity.Configuration;

@Repository
public interface ConfigurationRepository extends BaseRepository<Configuration, Integer> {

	public Configuration findByName(String name);

	public Page<Configuration> findAllByNameLikeOrderByIdDesc(String string, Pageable pageRequest);

	public Page<Configuration> findAllByNameLike(String string, Pageable pageRequest);

	public List<Configuration> findByAutoLoad(boolean autoLoad);

}