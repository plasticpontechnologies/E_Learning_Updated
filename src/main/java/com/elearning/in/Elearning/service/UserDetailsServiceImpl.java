package com.elearning.in.Elearning.service;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.elearning.in.Elearning.dao.AppRoleDao;
import com.elearning.in.Elearning.dao.AppUserDao;
import com.elearning.in.Elearning.model.AppUser;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private AppUserDao appUserDAO;

	@Autowired
	private AppRoleDao appRoleDao;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		if (username.equals("")) {
			System.out.println("====");

		}
		System.out.println(username);
		System.out.println("inside loadUserByUsername method");
		AppUser appUser = this.appUserDAO.findUserAccount(username);

		if (appUser == null) {
			System.out.println("User not found! " + username);
			throw new UsernameNotFoundException("User " + username + " was not found in the database");
		}

		System.out.println("Found User: " + appUser);

		// [ROLE_USER, ROLE_ADMIN,..]
		List<String> roleNames = this.appRoleDao.getRoleNames(appUser.getUserId());

		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		if (roleNames != null) {
			for (String role : roleNames) {
				// ROLE_USER, ROLE_ADMIN,..
				GrantedAuthority authority = new SimpleGrantedAuthority(role);
				grantList.add(authority);
			}
		}

		UserDetails userDetails = (UserDetails) new User(appUser.getUserName(), appUser.getEncrytedPassword(),
				grantList);

		return userDetails;
	}

}
