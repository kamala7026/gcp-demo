package com.kkb.gcp.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity(name = "job_state")
public class JobState {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private long id;
	 private String state;
	 private Timestamp created_ts;
	 private Timestamp updated_ts;
	 @OneToOne
	 private Params ParamsObject;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public Timestamp getCreated_ts() {
		return created_ts;
	}
	public void setCreated_ts(Timestamp created_ts) {
		this.created_ts = created_ts;
	}
	public Timestamp getUpdated_ts() {
		return updated_ts;
	}
	public void setUpdated_ts(Timestamp updated_ts) {
		this.updated_ts = updated_ts;
	}
	public Params getParamsObject() {
		return ParamsObject;
	}
	public void setParamsObject(Params paramsObject) {
		ParamsObject = paramsObject;
	}
	@Override
	public String toString() {
		return "JobState [id=" + id + ", state=" + state + ", created_ts=" + created_ts + ", updated_ts=" + updated_ts
				+ ", ParamsObject=" + ParamsObject + "]";
	}
	 
	 
	 
	}

