package com.mmt.management.entity;

import java.sql.Clob;
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
 * 招聘会
 * @author hp
 *
 */
@Entity
@Table(name="job_fair")
public class JobFair {
	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private Long id;
	private String title;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
	private City city;
	private String locate;
	@Column(columnDefinition="TEXT")
	private String content;
	private Date startDate;
	private Date endDate;
	private Date publishTime;
	private Date status;// 0 未审核 1 审核通过
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
	private AdminUser user;
	private String logo;
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
	 * @return the locate
	 */
	public String getLocate() {
		return locate;
	}
	/**
	 * @param locate the locate to set
	 */
	public void setLocate(String locate) {
		this.locate = locate;
	}

	public String getContent(){return content;}

	public void setContent(String content){this.content = content;}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return the publishTime
	 */
	public Date getPublishTime() {
		return publishTime;
	}
	/**
	 * @param publishTime the publishTime to set
	 */
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	/**
	 * @return the status
	 */
	public Date getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(Date status) {
		this.status = status;
	}
	/**
	 * @return the logo
	 */
	public String getLogo() {
		return logo;
	}
	/**
	 * @param logo the logo to set
	 */
	public void setLogo(String logo) {
		this.logo = logo;
	}
	/**
	 * @return the city
	 */
	public City getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(City city) {
		this.city = city;
	}
	/**
	 * @return the user
	 */
	public AdminUser getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(AdminUser user) {
		this.user = user;
	}
}
