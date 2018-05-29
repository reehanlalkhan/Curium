package com.model.adminexpenses.dto;

// default package
// Generated 24 Mar, 2015 1:00:53 PM by Hibernate Tools 4.0.0

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Adminexpenses generated by hbm2java
 */
@Entity
@Table(name = "adminexpenses")
public class Adminexpenses implements java.io.Serializable {

	private Integer idAdminExpenses;
	private String itemDescription;
	private Integer priceofitem;
	private Date entrydate;
	private Integer quantity;
	private int branchid;

	public Adminexpenses() {
	}

	public Adminexpenses(Date entrydate) {
		this.entrydate = entrydate;
	}

	public Adminexpenses(String itemDescription, Integer priceofitem,
			Date entrydate, Integer quantity, int branchid) {
		this.itemDescription = itemDescription;
		this.priceofitem = priceofitem;
		this.entrydate = entrydate;
		this.quantity = quantity;
		this.branchid = branchid;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idAdminExpenses", unique = true, nullable = false)
	public Integer getIdAdminExpenses() {
		return this.idAdminExpenses;
	}

	public void setIdAdminExpenses(Integer idAdminExpenses) {
		this.idAdminExpenses = idAdminExpenses;
	}

	@Column(name = "item description", length = 500)
	public String getItemDescription() {
		return this.itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	@Column(name = "priceofitem")
	public Integer getPriceofitem() {
		return this.priceofitem;
	}

	public void setPriceofitem(Integer priceofitem) {
		this.priceofitem = priceofitem;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "entrydate", nullable = false, length = 19)
	public Date getEntrydate() {
		return this.entrydate;
	}

	public void setEntrydate(Date entrydate) {
		this.entrydate = entrydate;
	}

	@Column(name = "quantity")
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	@Column(name = "branchid")
	public int getBranchid() {
	return branchid;
	}

	public void setBranchid(int branchid) {
	this.branchid = branchid;
	}
}
