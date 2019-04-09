package com.mmt.management.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="sys_permission")
public class SysPermission {
	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private Long id;
	private String name;
	private Long sort;
	private Long level;
	private Long pid;
	private String code;
	private String url;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
	private SysRole role;
	
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
	 * @return the sort
	 */
	public Long getSort() {
		return sort;
	}
	/**
	 * @param sort the sort to set
	 */
	public void setSort(Long sort) {
		this.sort = sort;
	}
	/**
	 * @return the level
	 */
	public Long getLevel() {
		return level;
	}
	/**
	 * @param level the level to set
	 */
	public void setLevel(Long level) {
		this.level = level;
	}
	/**
	 * @return the pid
	 */
	public Long getPid() {
		return pid;
	}
	/**
	 * @param pid the pid to set
	 */
	public void setPid(Long pid) {
		this.pid = pid;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the role
	 */
	public SysRole getRole() {
		return role;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(SysRole role) {
		this.role = role;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
}
