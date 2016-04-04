package yuown.isee.entity;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "applications", uniqueConstraints = @UniqueConstraint(columnNames = { "id" }))
@AttributeOverrides(value = {
		@AttributeOverride(name = "id", column = @Column(name = "ID", insertable = false, updatable = false)),
		@AttributeOverride(name = "interviewDate", column = @Column(name = "interview_date"))
})
public class Application extends BaseEntity<Integer> implements Serializable {

	private static final long serialVersionUID = 4289151143888117381L;

	private Job appliedFor;

	private Boolean cleared;

	private Date interviewDate;

	private Employee appliedBy;

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

	public Job getAppliedFor() {
		return appliedFor;
	}

	public Boolean getCleared() {
		return cleared;
	}

	public Date getInterviewDate() {
		return interviewDate;
	}

	public void setAppliedFor(Job appliedFor) {
		this.appliedFor = appliedFor;
	}

	public void setCleared(Boolean cleared) {
		this.cleared = cleared;
	}

	public void setInterviewDate(Date interviewDate) {
		this.interviewDate = interviewDate;
	}

	public Employee getAppliedBy() {
		return appliedBy;
	}

	public void setAppliedBy(Employee appliedBy) {
		this.appliedBy = appliedBy;
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
		Application rhs = (Application) obj;
		return (new EqualsBuilder()).append(this.id, rhs.id).isEquals();
	}
}
