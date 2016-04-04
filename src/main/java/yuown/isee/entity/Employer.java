package yuown.isee.entity;

import java.io.Serializable;

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
@Table(name = "employers", uniqueConstraints = @UniqueConstraint(columnNames = { "id" }))
@AttributeOverrides(value = { @AttributeOverride(name = "id", column = @Column(name = "ID", insertable = false, updatable = false)),
		@AttributeOverride(name = "name", column = @Column(name = "name")),
		@AttributeOverride(name = "address", column = @Column(name = "address")),
		@AttributeOverride(name = "description", column = @Column(name = "description"))
})
public class Employer extends BaseEntity<Integer> implements Serializable {

	private static final long serialVersionUID = 4289151143888117381L;

	private String name;

	private String address;

	private String description;

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

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getDescription() {
		return description;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setDescription(String description) {
		this.description = description;
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
		Employer rhs = (Employer) obj;
		return (new EqualsBuilder()).append(this.id, rhs.id).isEquals();
	}
}
