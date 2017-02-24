package aon.demo.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="policy")
public class Policy implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="criteria", nullable=false)
	private String criteria;
	@Column(name="stringvalue")
	private String ctrieriaStringValue;
	@Column(name="intvalue")
	private Integer criteriaIntValue;

	@ManyToMany(fetch=FetchType.LAZY) 
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinTable(name = "insurer_policy", joinColumns=@JoinColumn(name="policy_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name="insurer_id", referencedColumnName="id"))
	private List<Insurer> insurers;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCriteria() {
		return criteria;
	}
	public void setCriteria(String criteria) {
		this.criteria = criteria;
	}
	public String getCtrieriaStringValue() {
		return ctrieriaStringValue;
	}
	public void setCtrieriaStringValue(String ctrieriaStringValue) {
		this.ctrieriaStringValue = ctrieriaStringValue;
	}
	public Integer getCriteriaIntValue() {
		return criteriaIntValue;
	}
	public void setCriteriaIntValue(Integer criteriaIntValue) {
		this.criteriaIntValue = criteriaIntValue;
	}
	public List<Insurer> getInsurers() {
		return insurers;
	}
	public void setInsurers(List<Insurer> insurers) {
		this.insurers = insurers;
	}
}
