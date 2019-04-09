package com.mmt.management.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="sys_role")
public class SysRole {
	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private Long id;
	private String roleCode;
	private String roleName;
	@OneToMany(cascade = CascadeType.ALL)
	private List<SysPermission> permissionList;
	
	public SysRole(String roleCode, String roleName) {
        this.roleCode = roleCode;
        this.roleName = roleName;
    }

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
	 * @return the roleCode
	 */
	public String getRoleCode() {
		return roleCode;
	}

	/**
	 * @param roleCode the roleCode to set
	 */
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * @return the permissionList
	 */
	public List<SysPermission> getPermissionList() {
		return permissionList;
	}

	/**
	 * @param permissionList the permissionList to set
	 */
	public void setPermissionList(List<SysPermission> permissionList) {
		this.permissionList = permissionList;
	}
}
