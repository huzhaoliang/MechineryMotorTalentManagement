package com.mmt.management.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 简历模板
 * @author hp
 *
 */
@Entity
@Table(name="resume_template")
public class ResumeTemplate {
	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private Long id;
	private String title;
	private String path;
	private Date updateTime;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
	private SysAdminUser user;
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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}
	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}
	/**
	 * @return the updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * @return the user
	 */
	public SysAdminUser getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(SysAdminUser user) {
		this.user = user;
	}
}
