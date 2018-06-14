package com.hellojava.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.hellojava.dao.IUserDao;
import com.hellojava.entity.User;

@Repository("userDao")
public class UserDao extends JdbcDaoSupport implements IUserDao {
	@Autowired
	public void init(DataSource dataSource) {
		setDataSource(dataSource);
	} 
	
	public User loadUser(User user) {
		String sql="select * from user where userName=? and userPwd=?";
		return this.getJdbcTemplate().query(new PreparedStatementCreator() {
			
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement psment=conn.prepareStatement(sql);
				psment.setString(1, user.getUserName());
				psment.setString(2, user.getUserPwd());
				return psment;
			}
		}, new ResultSetExtractor<User>() {

			@Override
			public User extractData(ResultSet res) throws SQLException, DataAccessException {
				User user=null;
				if(res.next()) {
					user=new User();
					user.setUserId(res.getInt("userId"));
					user.setUserName(res.getString("userName"));
					user.setUserPwd(res.getString("userPwd"));
					
				}
				return user;
			}
		});

	}

	

}
