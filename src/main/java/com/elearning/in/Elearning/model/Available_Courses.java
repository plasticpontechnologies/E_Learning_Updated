package com.elearning.in.Elearning.model;

public class Available_Courses {
private String coursenames;
private String trainername;
private String time_slot;

public String getCoursenames() {
	return coursenames;
}
public void setCoursenames(String coursenames) {
	this.coursenames = coursenames;
}
public String getTrainername() {
	return trainername;
}
public void setTrainername(String trainername) {
	this.trainername = trainername;
}
public String getTime_slot() {
	return time_slot;
}
public void setTime_slot(String time_slot) {
	this.time_slot = time_slot;
}
@Override
public String toString() {
	return "Available_Courses [coursenames=" + coursenames + ", trainername=" + trainername + ", time_slot=" + time_slot
			+ "]";
}


}
