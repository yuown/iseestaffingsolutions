package yuown.isee.jpa.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import yuown.isee.entity.BaseEntity;

@NoRepositoryBean
public interface BaseRepository<E extends BaseEntity<ID>, ID extends Serializable> extends JpaRepository<E, ID>, JpaSpecificationExecutor<E> {

	public E findById(ID id);
	
	public List<E> findAllByOrderByIdDesc();
	
	public Page<E> findAllByOrderByIdDesc(Pageable pageRequest);
	
	public List<E> findAllByEnabled(Boolean enabled);
}
