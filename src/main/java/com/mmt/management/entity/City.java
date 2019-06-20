package com.mmt.management.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="city")
public class City {
	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private Long id;
	private String name;
	private Integer Flag;//1:地区 2: 省份、直辖市 3:市县
	private Long parentId;
	@OneToMany(cascade = CascadeType.ALL)
	private List<EnterpriseUser> companys;
	@OneToMany(cascade = CascadeType.ALL)
	private List<JobFair> jobFairs;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the flag
	 */
	public Integer getFlag() {
		return Flag;
	}
	/**
	 * @param flag the flag to set
	 */
	public void setFlag(Integer flag) {
		Flag = flag;
	}

	/**
	 * @return the companys
	 */
	public List<EnterpriseUser> getCompanys() {
		return companys;
	}
	/**
	 * @param companys the companys to set
	 */
	public void setCompanys(List<EnterpriseUser> companys) {
		this.companys = companys;
	}
	/**
	 * @return the jobFairs
	 */
	public List<JobFair> getJobFairs() {
		return jobFairs;
	}
	/**
	 * @param jobFairs the jobFairs to set
	 */
	public void setJobFairs(List<JobFair> jobFairs) {
		this.jobFairs = jobFairs;
	}
	/**
	 * @return the parentId
	 */
	public Long getParentId() {
		return parentId;
	}
	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
}
