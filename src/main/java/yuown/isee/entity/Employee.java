package yuown.isee.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "employees", uniqueConstraints = @UniqueConstraint(columnNames = { "id" }))
@AttributeOverrides(value = { @AttributeOverride(name = "id", column = @Column(name = "ID", insertable = false, updatable = false)),
		@AttributeOverride(name = "firstName", column = @Column(name = "first_name")),
		@AttributeOverride(name = "lastName", column = @Column(name = "last_name")),
		@AttributeOverride(name = "dob", column = @Column(name = "date_of_birth")),
		@AttributeOverride(name = "comments", column = @Column(name = "comments")),
		@AttributeOverride(name = "address", column = @Column(name = "address")),
		@AttributeOverride(name = "mobile", column = @Column(name = "mobile"))
})
public class Employee extends BaseEntity<Integer> implements Serializable {

	private static final long serialVersionUID = 4289151143888117381L;

	private String firstName;

	private String lastName;

	private Date dob;

	private String comments;

	private String address;

	private String mobile;
	
	private User user;
	
	private List<Education> educations;
	
	@Override
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return this.id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Date getDob() {
		return dob;
	}

	public String getComments() {
		return comments;
	}

	public String getAddress() {
		return address;
	}

	public String getMobile() {
		return mobile;
	}

	public User getUser() {
		return user;
	}

	public List<Education> getEducations() {
		return educations;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setEducations(List<Education> educations) {
		this.educations = educations;
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
		Employee rhs = (Employee) obj;
		return (new EqualsBuilder()).append(this.id, rhs.id).isEquals();
	}
}
