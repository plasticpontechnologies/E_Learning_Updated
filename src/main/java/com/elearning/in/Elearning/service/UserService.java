package com.elearning.in.Elearning.service;

import java.util.List;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.elearning.in.Elearning.model.AppUser;
import com.elearning.in.Elearning.model.Available_Courses;
import com.elearning.in.Elearning.model.PaymentHistory;
import com.elearning.in.Elearning.model.Time_Slot;
import com.elearning.in.Elearning.model.User;
import com.elearning.in.Elearning.repository.UserRepository;
import com.elearrning.in.Elearning.utils.EncrytedPasswordUtils;



@Component
public class UserService {

	@Autowired
	private UserRepository userRepository;

	
	@Autowired
	private AppUser appUserDao;

	int savedUniqueId = 0;

	public int saveUser(User user) {
		int savedUser = userRepository.saveUser(user);
		// savedUniqueId = savedUser;
		updateUserApp(user, savedUser);
		return savedUser;
	}

	public Long updateUserApp(User user, int uniqueId) {
		Long appUniqueId = (long) 0;
		if (uniqueId > 0) {
			String encodedPwd = getEncodedPassword(user.getPassword());
			user.setPassword(encodedPwd);
			user.setEnabled(true);
			appUniqueId = userRepository.saveUserAppDetails(user);
		}
		if (appUniqueId > 0) {
			Long uniqueUserId = updateUserRole(appUniqueId);
		}
		return appUniqueId;
	}

	public String getEncodedPassword(String pwd) {
		return EncrytedPasswordUtils.encrytePassword(pwd);
	}

	public Long updateUserRole(Long appUniqueId) {
		int longValue = appUniqueId.intValue();
		int roleId = 2;
		Long uniqueUserId = userRepository.saveUserRole(longValue, roleId);
		 return uniqueUserId;
	}

	

	
	public List<Time_Slot> timeTable(String courseName) {
		List<Time_Slot> li = userRepository.timeSlot(courseName);
		return li;
	}
	
	public  List<PaymentHistory> payment_history() {
		List<PaymentHistory> li = userRepository.paymentHistory();
		return li;
	}
	public  List<Available_Courses> available_courses() {
		List<Available_Courses> li = userRepository.available_courses();
		return li;
}
}
