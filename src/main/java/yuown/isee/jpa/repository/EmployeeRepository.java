package yuown.isee.jpa.repository;

import org.springframework.stereotype.Repository;

import yuown.isee.entity.Employee;

@Repository
public interface EmployeeRepository extends BaseRepository<Employee, Integer> {

}
