package com.elearning.in.Elearning.model;

public class PaymentHistory {
	private String username;
	private String courses;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCourses() {
		return courses;
	}
	public void setCourses(String courses) {
		this.courses = courses;
	}
	@Override
	public String toString() {
		return "PaymentHistory [username=" + username + ", courses=" + courses + "]";
	}
	

}
