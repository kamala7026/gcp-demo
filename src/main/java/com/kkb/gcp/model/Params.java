package com.kkb.gcp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "params")
public class Params {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private long id;
	 private String carId;
	 private String unoId;
	 private String bnetId;
	 private String unoQueryId;
	 private String bnetQueryId;
	 
	 
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCarId() {
		return carId;
	}
	public void setCarId(String carId) {
		this.carId = carId;
	}
	public String getUnoId() {
		return unoId;
	}
	public void setUnoId(String unoId) {
		this.unoId = unoId;
	}
	public String getBnetId() {
		return bnetId;
	}
	public void setBnetId(String bnetId) {
		this.bnetId = bnetId;
	}
	public String getUnoQueryId() {
		return unoQueryId;
	}
	public void setUnoQueryId(String unoQueryId) {
		this.unoQueryId = unoQueryId;
	}
	public String getBnetQueryId() {
		return bnetQueryId;
	}
	public void setBnetQueryId(String bnetQueryId) {
		this.bnetQueryId = bnetQueryId;
	}
	 

}
