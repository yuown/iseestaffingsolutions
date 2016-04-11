package yuown.isee.entity;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "USERS", uniqueConstraints = @UniqueConstraint(columnNames = { "id" }))
@AttributeOverrides(value = {
		@AttributeOverride(name = "id", column = @Column(name = "ID", insertable = false, updatable = false)),
		@AttributeOverride(name = "username", column = @Column(name = "username")),
		@AttributeOverride(name = "password", column = @Column(name = "PASSWORD")),
		@AttributeOverride(name = "fullName", column = @Column(name = "FULL_NAME"))
	})
public class User extends BaseEntity<Integer> implements Serializable {

	private static final long serialVersionUID = 4289151143888117381L;

	private String username;

	private String password;

	private String fullName;
	
	private Employee employee;
	
	private Employer employer;
	
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	@Id
	public Integer getId() {
		return this.id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	@OneToOne(optional = true)
	public Employee getEmployee() {
		return employee;
	}

	@OneToOne(optional = true)
	public Employer getEmployer() {
		return employer;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public void setEmployer(Employer employer) {
		this.employer = employer;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public int hashCode() {
		return (new HashCodeBuilder()).append(this.id).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User rhs = (User) obj;
		return (new EqualsBuilder()).append(this.id, rhs.id).isEquals();
	}
}
