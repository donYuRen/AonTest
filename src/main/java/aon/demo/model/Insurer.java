/**
 * 
 */
package aon.demo.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author donren
 *
 */
@Entity
@Table(name="insurer")
public class Insurer {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
	@NotEmpty
	@Column(name="name", nullable=false)
	private String name;
	@NotEmpty
	@Column(name="price", nullable=false)
	private BigDecimal price;
    @ManyToMany(mappedBy="insurers",fetch=FetchType.EAGER,cascade=CascadeType.ALL)  
    @JsonIgnore
	private List<Policy> policies;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public List<Policy> getPolicies() {
		return policies;
	}
	public void setPolicies(List<Policy> policies) {
		this.policies = policies;
	}	

}
