package com.heroku.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author
 */

public class UPay implements Serializable{

	private Integer id;

	private Integer pay;

	private Date time;

	private static final long serialVersionUID = 1L;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPay() {
		return pay;
	}

	public void setPay(Integer pay) {
		this.pay = pay;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
}