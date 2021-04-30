package com.elearning.in.Elearning.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.elearning.in.Elearning.model.Available_Courses;
import com.elearning.in.Elearning.model.PaymentHistory;
import com.elearning.in.Elearning.model.Time_Slot;
import com.elearning.in.Elearning.model.User;

@Component
public class UserRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	KeyHolder saveUserRoleKeyHolder = new GeneratedKeyHolder();
	KeyHolder keyHolderUserApp = new GeneratedKeyHolder();

	public int saveUser(User user) {
		long startTime = System.currentTimeMillis();
		List<User> user1 = findUser1(user);
		System.out.println("Total no.of Objects are " + user1.size());
		System.out.println("Time taken to read the records from user table " + (System.currentTimeMillis() - startTime)
				+ " milliseconds");

		for (User u : user1) {
			// System.out.println("inside loop");
			String firstName = u.getFirstname();
			String lastName = u.getlastname();
			if ((user.getFirstname().equalsIgnoreCase(firstName)) && (user.getlastname().equalsIgnoreCase(lastName))) {
				System.out.println("user already exists ");
				return 0;
			}
		}
		return jdbcTemplate.update(
				"insert into user (first_name,last_name,email,phonenumber,dob,password) values (?,?,?,?,?,?)",
				new Object[] { user.getFirstname(), user.getlastname(), user.getEmail(), user.getphonenumber(),
						user.getDob(), user.getPassword() });
		// keyHolderUser.getKey().intValue();
	}

	public List<User> findUser1(User user) {

		String QUERY_UserValid = "select first_name,last_name from user ";
		List<User> client = jdbcTemplate.query(QUERY_UserValid, new RowMapper<User>() {

			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				System.out.println(rs.getFetchSize());
				User userData = new User();
				userData.setFirstname((rs.getString("first_name")));
				userData.setLastame((rs.getString("last_name")));

				return userData;
			}

		});
		return client;
	}

	/*
	 * public List<User> findUser(User user) { String sql =
	 * "select first_name,last_name from user where first_name = '" +
	 * user.getFirstname() + "'and last_name='" + user.getlastname() + "'";
	 * 
	 * List<User> client = jdbcTemplate.query(sql, new RowMapper<User>() {
	 * 
	 * public User mapRow(ResultSet rs, int rowNum) throws SQLException {
	 * System.out.println(rs.getFetchSize());
	 * 
	 * User userData = new User();
	 * userData.setFirstname((rs.getString("first_name")));
	 * userData.setLastame((rs.getString("last_name")));
	 * 
	 * return userData; }
	 * 
	 * });
	 * 
	 * return client; }
	 */
	public List<Time_Slot> timeSlot(String courseName) {
		String sql = "Select * from timetable where course_name= ?";
		List<Time_Slot> Time_Slot = jdbcTemplate.query(sql, new Object[] { courseName }, new RowMapper<Time_Slot>() {
			public Time_Slot mapRow(ResultSet rs, int rowNum) throws SQLException {
				Time_Slot ts = new Time_Slot();
				ts.setTime_slot(rs.getString("time_slot"));
				ts.setCourse_name(rs.getString("course_name"));
				ts.setTrainer_name(rs.getString("trainer_name"));

				return ts;
			}

		});
		return Time_Slot;
	}

	public Long saveUserAppDetails(final User user) {
		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement statement = con.prepareStatement(
						"insert into app_user (user_name,ENCRYTED_PASSWORD,ENABLED) values(?,?,?)",
						Statement.RETURN_GENERATED_KEYS);
				statement.setString(1, user.getFirstname());
				statement.setString(2, user.getPassword());
				statement.setBoolean(3, user.isEnabled());
				return statement;
			}
		}, keyHolderUserApp);

		Long returnValue = keyHolderUserApp.getKey().longValue();

		return returnValue;
	}

	public Long saveUserRole(final int user_id, final int role_id) {
		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				// TODO Auto-generated method stub
				PreparedStatement statement1 = con.prepareStatement(
						"insert into user_role (user_id,role_id) values(?,?)", Statement.RETURN_GENERATED_KEYS);
				statement1.setInt(1, user_id);
				statement1.setInt(2, role_id);
				return statement1;
			}
		}, saveUserRoleKeyHolder);

		return saveUserRoleKeyHolder.getKey().longValue();
	}
	
	public List<PaymentHistory> paymentHistory() {
		String sql = "Select * from payment_history";
		List<PaymentHistory> PaymentHistory = jdbcTemplate.query(sql, new RowMapper<PaymentHistory>() {
			public PaymentHistory mapRow(ResultSet rs, int rowNum) throws SQLException {
				PaymentHistory ph = new PaymentHistory();
				ph.setUsername(rs.getString("username"));
				ph.setCourses(rs.getString("courses"));
				return ph;
			}
		});
		return PaymentHistory;
	}
	
	public List<Available_Courses> available_courses() {
		String sql = "Select * from available_courses";
		List<Available_Courses> Available_Courses = jdbcTemplate.query(sql, new RowMapper<Available_Courses>() {
			public Available_Courses mapRow(ResultSet rs, int rowNum) throws SQLException {
				Available_Courses ac = new Available_Courses();
				ac.setCoursenames(rs.getString("coursenames"));
				ac.setTrainername(rs.getString("trainername"));
				ac.setTime_slot(rs.getString("time_slot"));
				return ac;
			}
		});
		return Available_Courses;
}

}
