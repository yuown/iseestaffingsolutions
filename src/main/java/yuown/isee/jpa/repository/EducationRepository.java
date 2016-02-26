package yuown.isee.jpa.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import yuown.isee.entity.Education;

@Repository
public interface EducationRepository extends BaseRepository<Education, Integer> {

	List<Education> findAllByProfileId(Integer profileId);

}
