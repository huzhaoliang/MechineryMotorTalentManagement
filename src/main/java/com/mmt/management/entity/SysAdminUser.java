package com.mmt.management.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * 管理员
 * @author hp
 *
 */
@Entity
@Table(name="admin_user")
public class SysAdminUser {
	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private Long id;
	private String name;
	private String password;
	private Date LastLoginTime;
	@ManyToMany(cascade = {
			CascadeType.PERSIST,
	        CascadeType.MERGE
	    })
    @JoinTable(name = "admin_role",
    	joinColumns = @JoinColumn(name = "admin_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
	private List<SysRole> roleList;
	
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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the lastLoginTime
	 */
	public Date getLastLoginTime() {
		return LastLoginTime;
	}
	/**
	 * @param lastLoginTime the lastLoginTime to set
	 */
	public void setLastLoginTime(Date lastLoginTime) {
		LastLoginTime = lastLoginTime;
	}
	/**
	 * @return the roleList
	 */
	public List<SysRole> getRoleList() {
		return roleList;
	}
	/**
	 * @param roleList the roleList to set
	 */
	public void setRoleList(List<SysRole> roleList) {
		this.roleList = roleList;
	}
}