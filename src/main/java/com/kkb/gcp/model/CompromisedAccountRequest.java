package com.kkb.gcp.model;

public class CompromisedAccountRequest {

	 private String carId;
	 private String unoId;
	 private String bnetId;
	 // Getter Methods 
	 public String getCarId() {
	  return carId;
	 }
	 public String getUnoId() {
	  return unoId;
	 }
	 public String getBnetId() {
	  return bnetId;
	 }
	 // Setter Methods 
	 public void setCarId(String carId) {
	  this.carId = carId;
	 }
	 public void setUnoId(String unoId) {
	  this.unoId = unoId;
	 }
	 public void setBnetId(String bnetId) {
	  this.bnetId = bnetId;
	 }
	@Override
	public String toString() {
		return "CompromisedAccountRequest [carId=" + carId + ", unoId=" + unoId + ", bnetId=" + bnetId + "]";
	}
	 
	 
}
