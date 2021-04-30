package com.elearning.in.Elearning.model;

public class User {

	private String firstname;
	private String lastname;
	private String email;
	private String phonenumber;
	private String dob;
	private String password;
	private String gender;

	private boolean enabled;

	public String getlastname() {
		return lastname;
	}

	public void setLastame(String lastname) {
		this.lastname = lastname;
	}

	
	
	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getphonenumber() {
		return phonenumber;
	}

	public void setphonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email_id) {
		this.email = email_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	@Override
	public String toString() {
		return "User [firstname=" + firstname + ", lastname=" + lastname + ", email=" + email + ", phonenumber="
				+ phonenumber + ", dob=" + dob + ", password=" + password + ", gender=" + gender + ", enabled="
				+ enabled + "]";
	}

	

}

