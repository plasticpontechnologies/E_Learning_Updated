package com.elearning.in.Elearning.model;

public class Time_Slot {
    private String time_slot;
    private String course_name;
    private String trainer_name;
	public String getTime_slot() {
		return time_slot;
	}
	public void setTime_slot(String time_slot) {
		this.time_slot = time_slot;
	}
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	public String getTrainer_name() {
		return trainer_name;
	}
	public void setTrainer_name(String trainer_name) {
		this.trainer_name = trainer_name;
	}
	@Override
	public String toString() {
		return "Time_Slot [time_slot=" + time_slot + ", course_name=" + course_name + ", trainer_name=" + trainer_name
				+ "]";
	}
	
}
